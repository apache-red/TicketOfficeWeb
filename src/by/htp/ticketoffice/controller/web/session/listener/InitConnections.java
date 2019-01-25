package by.htp.ticketoffice.controller.web.session.listener;

import by.htp.ticketoffice.dao.mysql.StationDAO;
import by.htp.ticketoffice.entity.Route;
import by.htp.ticketoffice.exceptions.DAOException;
import by.htp.ticketoffice.dao.mysql.factory.DAOFactory;
import by.htp.ticketoffice.dao.mysql.impl.connectionsPool.ConnectionPool;
import by.htp.ticketoffice.exceptions.ConnectionPoolException;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;



public class InitConnections implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {

		List<Route> routeList = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			connectionPool.initPoolData();
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		}

		StationDAO stationDAO = DAOFactory.getInstance().getStationDAO();
		try {
			routeList = stationDAO.getAllRoutes();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		ServletContext application = contextEvent.getServletContext();
		application.setAttribute("allRoutes", routeList);

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}
}
