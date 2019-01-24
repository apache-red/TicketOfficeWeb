package by.htp.ticketoffice.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import by.htp.ticketoffice.controller.web.servlet.util.RequestParameterName;
import by.htp.ticketoffice.dao.mysql.StationDAO;
import by.htp.ticketoffice.exceptions.DAOException;
import by.htp.ticketoffice.dao.mysql.factory.DAOFactory;
import by.htp.ticketoffice.dao.mysql.OrderDAO;
import by.htp.ticketoffice.entity.Client;
import by.htp.ticketoffice.entity.Order;
import by.htp.ticketoffice.service.DBService;
import org.apache.log4j.Logger;


public class ShowOrdersOfOneClientService implements DBService {

	private static final Logger logger = Logger.getLogger(ShowOrdersOfOneClientService.class);

	private final static ShowOrdersOfOneClientService instance = new ShowOrdersOfOneClientService();

	public static ShowOrdersOfOneClientService getInstance() {
		return instance;
	}

	@Override
	public boolean doService(HttpServletRequest request) {

		boolean result = false;
		int idClient = 0;
		int idClientInSession = 0;
		OrderDAO orderDAO = null;
		StationDAO stationDAO = null;
		List<Order> orderList = null;
		Map<Integer, List<Object>> dataOfOrdersOfOneClient = null;

		idClient = Integer.parseInt(request.getParameter(RequestParameterName.ID_CLIENT));
		idClientInSession = ((Client) request.getSession().getAttribute(RequestParameterName.CLIENT)).getId();
		System.out.println("1)" + idClient);
		System.out.println("2)" + idClientInSession);

		if (idClient != idClientInSession) {
			return false;
		}

		orderDAO = DAOFactory.getInstance().getOrderDAO();
		stationDAO = DAOFactory.getInstance().getStationDAO();
		try {
			orderList = orderDAO.getAllOrdersOfOneClient(idClient);

			// change
			//dataOfOrdersOfOneClient = stationDAO.getDataOfAllOrdersOfOneClient(orderList);
			request.setAttribute(RequestParameterName.ALL_ORDERS_OF_ONE_CLIENT, dataOfOrdersOfOneClient);
			result = true;
		} catch (DAOException e) {
			logger.error(
					"OrderDAO didn't return all orders of one client or StationDAO didn't return data of orders of one client. Message: "
							+ e.getMessage());
		}
		return result;
	}
}
