package by.htp.ticketoffice.service.impl;

import by.htp.ticketoffice.controller.web.servlet.util.RequestParameterName;
import by.htp.ticketoffice.dao.mysql.StationDAO;
import by.htp.ticketoffice.dao.mysql.factory.DAOFactory;
import by.htp.ticketoffice.exceptions.DAOException;
import by.htp.ticketoffice.service.DBService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddNewRouteService implements DBService {

    private static final Logger logger = Logger.getLogger(AddNewRouteService.class);

    private static final AddNewRouteService instance = new AddNewRouteService();

    public static AddNewRouteService getInstance() {
        return instance;
    }

    @Override
    public boolean doService(HttpServletRequest request) {
        String nameRoute = null;
        boolean result = false;
        StationDAO stationDAO = null;

        nameRoute = request.getParameter(RequestParameterName.NAME_ROUTE);
        if (!nameRoute.isEmpty()) {
            stationDAO = DAOFactory.getInstance().getStationDAO();
            try {
                stationDAO.addNewRoute(nameRoute);
                result = true;
            } catch (DAOException e) {
                logger.error("StationDAO didn't add a new station of route. Message: "+e.getMessage());
            }
        } else {
            logger.warn("Didn't enter the name of route ");
            request.getSession(true).setAttribute(RequestParameterName.ERROR_ADD_ROUTE, 1);
        }
        return result;
    }

}
