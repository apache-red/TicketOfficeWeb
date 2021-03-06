package by.htp.ticketoffice.service.impl;

import by.htp.ticketoffice.controller.web.servlet.util.RequestParameterName;
import by.htp.ticketoffice.dao.mysql.StationDAO;
import by.htp.ticketoffice.dao.mysql.factory.DAOFactory;
import by.htp.ticketoffice.entity.Station;
import by.htp.ticketoffice.exceptions.DAOException;
import by.htp.ticketoffice.service.DBService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class GoEditStationService implements DBService {

    private static final Logger logger = Logger.getLogger(GoEditStationService.class);

    private static final GoEditStationService instance = new GoEditStationService();

    public static GoEditStationService getInstance() {
        return instance;
    }

    @Override
    public boolean doService(HttpServletRequest request) {
        boolean result = false;
        int idStation = 0;
        Station station = null;
        StationDAO stationDAO = null;

        idStation = Integer.parseInt(request.getParameter(RequestParameterName.ID_STATION));
        stationDAO = DAOFactory.getInstance().getStationDAO();
        try {
            station = stationDAO.getStation(idStation);
        } catch (DAOException e) {
            logger.error("StationDAO didn't return a station. Message: " + e.getMessage());
        }

        if (station != null) {
            request.setAttribute(RequestParameterName.STATION, station);
            result = true;
        }
        return result;
    }
}