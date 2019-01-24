package by.htp.ticketoffice.dao.mysql;


import by.htp.ticketoffice.entity.Admin;
import by.htp.ticketoffice.exceptions.DAOException;

public interface AdminDAO {
	
	boolean checkAdmin(String login, String password) throws DAOException;
	Admin getAdmin(String login, String password) throws DAOException;
	
}
