package by.htp.ticketoffice.dao.mysql.factory;


import by.htp.ticketoffice.dao.mysql.*;
import by.htp.ticketoffice.dao.mysql.impl.*;

public class DAOFactory {
	private final static DAOFactory instance = new DAOFactory();

	public static DAOFactory getInstance() {
		return instance;
	}


	public StationDAO getStationDAO(){return SQLStationDAO.getInstance();}

	public AdminDAO getAdminDAO(){
		return SQLAdminDAO.getInstance();
	}

	public ClientDAO getClientDAO(){
		return SQLClientDAO.getInstance();
	}
	
	public OrderDAO getOrderDAO(){
		return SQLOrderDAO.getInstance();
	}
}
