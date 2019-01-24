package by.htp.ticketoffice.dao.mysql;

import by.htp.ticketoffice.entity.Client;
import by.htp.ticketoffice.entity.Order;
import by.htp.ticketoffice.entity.Station;
import by.htp.ticketoffice.exceptions.DAOException;

import java.util.List;


public interface OrderDAO {
	
	void addOrder(Client client, Station station) throws DAOException;
	List<Order> getAllOrdersOfOneClient(int id_client) throws DAOException;
	void cancelOrder(int idOrder) throws DAOException;
	
}
