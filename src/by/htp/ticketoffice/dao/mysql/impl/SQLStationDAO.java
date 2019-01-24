package by.htp.ticketoffice.dao.mysql.impl;

import by.htp.ticketoffice.dao.mysql.StationDAO;
import by.htp.ticketoffice.dao.mysql.impl.connectionsPool.ConnectionPool;
import by.htp.ticketoffice.entity.Route;
import by.htp.ticketoffice.entity.Station;
import by.htp.ticketoffice.exceptions.ConnectionPoolException;
import by.htp.ticketoffice.exceptions.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLStationDAO implements StationDAO {


    private static final String GET_ALL_ROUTES_SQL = "SELECT * FROM route";
    private static final String ADD_NEW_ROUTE_SQL = "INSERT INTO route (name) VALUES (?)";
    private static final String ADD_NEW_STATION_SQL = "INSERT INTO station (id_route, name) VALUES (?,?)";
    private static final String GET_STATION_SQL = "SELECT * FROM station WHERE id_station=?";
    private static final String GET_STATIONS_OF_ROUTE_SQL = "SELECT * FROM station WHERE id_station=?";
    private static final String REMOVE_STATION_SQL = "DELETE FROM station WHERE id_station=?";
    private static final String EDIT_PSTATION_SQL = "UPDATE station SET name=? WHERE id_station=?";


    private static final Logger logger = Logger.getLogger(SQLStationDAO.class);

    private static final SQLStationDAO instance = new SQLStationDAO();

    public static SQLStationDAO getInstance() {
        return instance;
    }

    private Route route;
    private List<Route> routeList;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Station station;
    private List<Station> stationList;


    @Override
    public List<Route> getAllRoutes() throws DAOException {
        routeList = new ArrayList<Route>();
        route = null;
        connection = null;
        preparedStatement = null;
        resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(GET_ALL_ROUTES_SQL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                route = new Route();
                route.setId(resultSet.getInt(1));
                route.setName(resultSet.getString(2));
                routeList.add(route);
            }
        } catch (ConnectionPoolException e) {
            logger.error("ConnectionPool didn't take connection. Message: " + e.getMessage());
        } catch (SQLException e) {
            throw new DAOException("SQLException in SQLStationDAO");
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.error("Statement didn't close. Message: " + e.getMessage());
                }
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error("Connection didn't close. Message: " + e.getMessage());
            }
        }
        return routeList;
    }

    @Override
    public void addNewStation(Station station) throws DAOException {

        connection = null;
        preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(ADD_NEW_STATION_SQL);
            preparedStatement.setInt(1, station.getIdRoute());
            System.out.println("idRoute=" + station.getIdRoute());
            preparedStatement.setString(2, station.getName());
            System.out.println("StationName=" + station.getName());
            preparedStatement.executeUpdate();
        } catch (ConnectionPoolException e) {
            logger.error("ConnectionPool didn't take connection. Message: " + e.getMessage());
        } catch (SQLException e) {
            throw new DAOException("SQLException in SQLStationDAO");
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.error("Statement didn't close. Message: " + e.getMessage());
                }
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error("Connection didn't close. Message: " + e.getMessage());
            }
        }
    }

    @Override
    public Station getStation(int idStation) throws DAOException {
        station = null;
        connection = null;
        preparedStatement = null;
        resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(GET_STATION_SQL);
            preparedStatement.setInt(1, idStation);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                station = new Station();
                station.setId(resultSet.getInt(1));
                station.setIdRoute(resultSet.getInt(2));
                station.setName(resultSet.getString(3));
            }
        } catch (ConnectionPoolException e) {
            logger.error("ConnectionPool didn't take connection. Message: " + e.getMessage());
        } catch (SQLException e) {
            throw new DAOException("SQLException in SQLStationDAO");
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.error("Statement didn't close. Message: " + e.getMessage());
                }
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error("Connection didn't close. Message: " + e.getMessage());
            }
        }
        return station;
    }

    @Override
    public List<Station> getStationOfRoute(int idRoute) throws DAOException {
        stationList = new ArrayList<Station>();
        station = null;
        connection = null;
        preparedStatement = null;
        resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(GET_STATIONS_OF_ROUTE_SQL);
            preparedStatement.setInt(1, idRoute);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                station = new Station();
                station.setId(resultSet.getInt(1));
                station.setIdRoute(resultSet.getInt(2));
                station.setName(resultSet.getString(3));
                stationList.add(station);
            }
        } catch (ConnectionPoolException e) {
            logger.error("ConnectionPool didn't take connection. Message: " + e.getMessage());
        } catch (SQLException e) {
            throw new DAOException("SQLException in SQLStationDAO");
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.error("Statement didn't close. Message: " + e.getMessage());
                }
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error("Connection didn't close. Message: " + e.getMessage());
            }
        }
        return stationList;
    }

    @Override
    public void removeStation(int idStation) throws DAOException {
         connection = null;
         preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(REMOVE_STATION_SQL);
            preparedStatement.setInt(1, idStation);
            preparedStatement.executeUpdate();
        } catch (ConnectionPoolException e) {
            logger.error("ConnectionPool didn't take connection. Message: " + e.getMessage());
        } catch (SQLException e) {
            throw new DAOException("SQLException in SQLStationDAO");
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.error("Statement didn't close. Message: " + e.getMessage());
                }
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error("Connection didn't close. Message: " + e.getMessage());
            }
        }
    }

    @Override
    public void editStation(Station station) throws DAOException {
         connection = null;
         preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(EDIT_PSTATION_SQL);
            preparedStatement.setString(1, station.getName());
            preparedStatement.setInt(2, station.getId());
            preparedStatement.executeUpdate();
        } catch (ConnectionPoolException e) {
            logger.error("ConnectionPool didn't take connection. Message: " + e.getMessage());
        } catch (SQLException e) {
            throw new DAOException("SQLException in SQLStationDAO");
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.error("Statement didn't close. Message: " + e.getMessage());
                }
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error("Connection didn't close. Message: " + e.getMessage());
            }
        }
    }


    @Override
    public void addNewRoute(String nameRoute) throws DAOException {
        connection = null;
        preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(ADD_NEW_ROUTE_SQL);
            preparedStatement.setString(1, nameRoute);
            preparedStatement.executeUpdate();
        } catch (ConnectionPoolException e) {
            logger.error("ConnectionPool didn't take connection. Message: " + e.getMessage());
        } catch (SQLException e) {
            throw new DAOException("SQLException in SQLStationDAO");
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.error("PreparedStatement didn't close. Message: " + e.getMessage());
                }
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error("Connection didn't close. Message: " + e.getMessage());
            }
        }


    }

}
