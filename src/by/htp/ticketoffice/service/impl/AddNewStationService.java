package by.htp.ticketoffice.service.impl;

import by.htp.ticketoffice.controller.web.servlet.util.RequestParameterName;
import by.htp.ticketoffice.dao.mysql.StationDAO;
import by.htp.ticketoffice.dao.mysql.factory.DAOFactory;
import by.htp.ticketoffice.entity.Station;
import by.htp.ticketoffice.exceptions.DAOException;
import by.htp.ticketoffice.service.DBService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddNewStationService implements DBService {

    private static final Logger logger = Logger.getLogger(AddNewStationService.class);

    private static final AddNewStationService instance = new AddNewStationService();

    public static AddNewStationService getInstance() {
        return instance;
    }

    @Override
    public boolean doService(HttpServletRequest request) {
        boolean result = true;
        Station station = new Station();
        String idRoute = null;
        String nameStation = null;
//        String costOfPStation = null;
//        String quantityOfStation = null;
        StationDAO stationDAO = null;

        idRoute = request.getParameter(RequestParameterName.ID_ROUTE);
        station.setIdRoute(Integer.parseInt(idRoute));

        nameStation = request.getParameter(RequestParameterName.NAME_STATION);
        if (nameStation.equals("")) {
            logger.warn("Didn't enter the name of station ");
            request.setAttribute(RequestParameterName.ERROR_ADD_OR_EDIT_STATION, 1);
            return false;
        }
        station.setName(nameStation);
        if (result) {
            stationDAO = DAOFactory.getInstance().getStationDAO();
            try {
                stationDAO.addNewStation(station);
            } catch (DAOException e) {
                logger.error("StationDAO didn't add a new station. Message: " + e.getMessage());
            }
        }
        return result;
    }
}
