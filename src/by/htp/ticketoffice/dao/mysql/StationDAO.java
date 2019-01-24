package by.htp.ticketoffice.dao.mysql;

import by.htp.ticketoffice.entity.Route;
import by.htp.ticketoffice.entity.Station;
import by.htp.ticketoffice.exceptions.DAOException;

import java.util.List;

public interface StationDAO {

    void addNewRoute(String nameRoute) throws DAOException;
    List<Route> getAllRoutes() throws DAOException;
    void addNewStation(Station station) throws DAOException;
    Station getStation(int idStation) throws DAOException;
    List<Station> getStationOfRoute(int idRoute) throws DAOException;
    void removeStation(int idStation) throws DAOException;
    void editStation(Station station) throws DAOException;
}
