package by.htp.ticketoffice.service.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.ticketoffice.controller.web.servlet.util.RequestParameterName;
import by.htp.ticketoffice.dao.mysql.*;
import by.htp.ticketoffice.dao.mysql.factory.DAOFactory;
import by.htp.ticketoffice.entity.Client;
import by.htp.ticketoffice.entity.Station;
import by.htp.ticketoffice.exceptions.DAOException;
import by.htp.ticketoffice.service.DBService;
import org.apache.log4j.Logger;


public class OrderService implements DBService {

	private static final Logger logger = Logger.getLogger(OrderService.class);

	private static final OrderService instance = new OrderService();

	public static OrderService getInstance() {
		return instance;
	}

	@Override
	public boolean doService(HttpServletRequest request) {
		boolean result = true;
		int idStation = 0;
		Client client = new Client();
		Station station = new Station();
		ClientDAO clientDAO = null;
		OrderDAO orderDAO = null;
		StationDAO stationDAO = null;

		client.setId(Integer.parseInt(request.getParameter(RequestParameterName.ID_CLIENT)));
		idStation = Integer.parseInt(request.getParameter(RequestParameterName.ID_STATION));
		if (result) {
			result = false;
			clientDAO = DAOFactory.getInstance().getClientDAO();
			orderDAO = DAOFactory.getInstance().getOrderDAO();
			stationDAO = DAOFactory.getInstance().getStationDAO();
			try {
				client.setAddress(clientDAO.getAddressOfClient(client.getId()));
				station = stationDAO.getStation(idStation);
				orderDAO.addOrder(client, station);
				result = true;
			} catch (DAOException e) {
				logger.error("StationDAO didn't add order or StationDAO didn't update quantity of stations in the stock. Message: "+ e.getMessage());
			}
		}
		return result;
	}
}
