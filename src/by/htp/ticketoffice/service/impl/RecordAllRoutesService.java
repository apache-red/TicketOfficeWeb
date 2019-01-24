package by.htp.ticketoffice.service.impl;

import by.htp.ticketoffice.controller.web.servlet.util.RequestParameterName;
import by.htp.ticketoffice.dao.mysql.StationDAO;
import by.htp.ticketoffice.dao.mysql.factory.DAOFactory;
import by.htp.ticketoffice.entity.Route;
import by.htp.ticketoffice.exceptions.DAOException;
import by.htp.ticketoffice.service.DBService;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class RecordAllRoutesService implements DBService {

    private static final Logger logger = Logger.getLogger(RecordAllRoutesService.class);

    private static final RecordAllRoutesService instance = new RecordAllRoutesService();

    public static RecordAllRoutesService getInstance() {
        return instance;
    }

    @Override
    public boolean doService(HttpServletRequest request) {
        boolean result = false;
        List<Route> routeList = null;

        StationDAO stationDAO = DAOFactory.getInstance().getStationDAO();
        try {
            routeList = stationDAO.getAllRoutes();
            result = true;
        } catch (DAOException e) {
            logger.error("StationDAO didn't return all routes. Message: " + e.getMessage());
        }
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute(RequestParameterName.ALL_ROUTES, routeList);
        return result;
    }

}
