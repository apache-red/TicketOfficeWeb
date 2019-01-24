package by.htp.ticketoffice.service.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.ticketoffice.controller.web.servlet.util.RequestParameterName;
import by.htp.ticketoffice.dao.mysql.StationDAO;
import by.htp.ticketoffice.exceptions.DAOException;
import by.htp.ticketoffice.dao.mysql.factory.DAOFactory;
import by.htp.ticketoffice.dao.mysql.OrderDAO;
import by.htp.ticketoffice.service.DBService;
import org.apache.log4j.Logger;

public class CancelOrderService implements DBService {

    private static final Logger logger = Logger.getLogger(CancelOrderService.class);
    private static final CancelOrderService instance = new CancelOrderService();
    public static CancelOrderService getInstance() {
        return instance;
    }

    @Override
    public boolean doService(HttpServletRequest request) {
        boolean result = false;
        int idOrder = 0;
        int idStation = 0;
        OrderDAO orderDAO = null;
        StationDAO stationDAO = null;

        idOrder = Integer.parseInt(request.getParameter(RequestParameterName.ID_ORDER));
        idStation = Integer.parseInt(request.getParameter(RequestParameterName.ID_STATION));
        orderDAO = DAOFactory.getInstance().getOrderDAO();
        stationDAO = DAOFactory.getInstance().getStationDAO();
        try {
            orderDAO.cancelOrder(idOrder);
            result = true;
        } catch (DAOException e) {
            logger.error("StationDAO didn't cancel order. Message: " + e.getMessage());
        }
        return result;
    }
}
