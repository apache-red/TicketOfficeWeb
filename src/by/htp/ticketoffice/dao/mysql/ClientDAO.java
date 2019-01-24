package by.htp.ticketoffice.dao.mysql;

import by.htp.ticketoffice.entity.Client;
import by.htp.ticketoffice.exceptions.DAOException;

import java.sql.Date;
import java.util.List;


public interface ClientDAO {

	boolean checkClient(String login, String password) throws DAOException;
	boolean thereIsClientOnBlacklist(String login, String password) throws DAOException;
	Client getClient(String login, String password) throws DAOException;
	boolean checkUniquenessOfLogin(String login) throws DAOException;
	void registration(String login, String password, String surname, String name, Date registrationDate,
                      String phone, String address, String email) throws DAOException;
	List<Client> getClientsThatAreNotIncludedInBlacklist() throws DAOException;
	List<Client> getBlacklist() throws DAOException;
	void addToBlacklict(int idClient) throws DAOException;
	void removeFromBlacklist(int idClient) throws DAOException;
	String getAddressOfClient(int idClient) throws DAOException;
}
