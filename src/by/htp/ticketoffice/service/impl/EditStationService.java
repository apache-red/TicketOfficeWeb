package by.htp.ticketoffice.service.impl;

import by.htp.ticketoffice.controller.web.servlet.util.RequestParameterName;
import by.htp.ticketoffice.dao.mysql.StationDAO;
import by.htp.ticketoffice.dao.mysql.factory.DAOFactory;
import by.htp.ticketoffice.entity.Station;
import by.htp.ticketoffice.exceptions.DAOException;
import by.htp.ticketoffice.service.DBService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class EditStationService implements DBService {

    private static final Logger logger = Logger.getLogger(EditStationService.class);

    private static final EditStationService instance = new EditStationService();

    public static EditStationService getInstance() {
        return instance;
    }

    private boolean result;
    private Station station;
    private String idRoute;
    private String idStation;
    private String nameStation;
    private StationDAO stationDAO;

    @Override
    public boolean doService(HttpServletRequest request) {
        result = true;
        station = new Station();
        idRoute = null;
        idStation = null;
        nameStation = null;
        stationDAO = null;

        idRoute = request.getParameter(RequestParameterName.ID_ROUTE);
        station.setIdRoute(Integer.parseInt(idRoute));

        idStation = request.getParameter(RequestParameterName.ID_STATION);
        station.setId(Integer.parseInt(idStation));

        nameStation = request.getParameter(RequestParameterName.NAME_STATION);
        if (nameStation.equals("")) {
            logger.warn("Name of station didn't enter");
            request.getSession(true).setAttribute(RequestParameterName.ERROR_ADD_OR_EDIT_STATION, 1);
            return false;
        }
        station.setName(nameStation);

        if (result) {
            stationDAO = DAOFactory.getInstance().getStationDAO();
            try {
                stationDAO.editStation(station);
            } catch (DAOException e) {
                logger.error("StationDAO didn't edit station. Message: " + e.getMessage());
            }
        }
        return result;
    }
}