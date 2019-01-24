package by.htp.ticketoffice.service.impl;

import by.htp.ticketoffice.controller.web.servlet.util.RequestParameterName;
import by.htp.ticketoffice.dao.mysql.StationDAO;
import by.htp.ticketoffice.dao.mysql.factory.DAOFactory;
import by.htp.ticketoffice.exceptions.DAOException;
import by.htp.ticketoffice.service.DBService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RemoveStationService implements DBService {

    private static final Logger logger = Logger.getLogger(RemoveStationService.class);

    private static final RemoveStationService instance = new RemoveStationService();

    public static RemoveStationService getInstance() {
        return instance;
    }

    @Override
    public boolean doService(HttpServletRequest request) {
        boolean result = false;
        int idStation = 0;
        StationDAO stationDAO = null;

        idStation = Integer.parseInt(request.getParameter(RequestParameterName.ID_STATION));
        stationDAO = DAOFactory.getInstance().getStationDAO();
        try {
            stationDAO.removeStation(idStation);
            result = true;
        } catch (DAOException e) {
            logger.error("StationDAO didn't remove station. Message: " + e.getMessage());
        }
        return result;
    }
}