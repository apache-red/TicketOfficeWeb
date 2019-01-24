package by.htp.ticketoffice.dao.mysql.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.ticketoffice.dao.mysql.ClientDAO;
import by.htp.ticketoffice.exceptions.DAOException;
import by.htp.ticketoffice.dao.mysql.impl.connectionsPool.ConnectionPool;
import by.htp.ticketoffice.exceptions.ConnectionPoolException;
import by.htp.ticketoffice.entity.Client;
import org.apache.log4j.Logger;



public class SQLClientDAO implements ClientDAO {

	private static final String LOGIN_PASSWORD_CHECK_SQL = "SELECT id_client FROM client WHERE login=? AND password=?";
	private static final String CHECK_CLIENT_IN_BLACKLIST_SQL = "SELECT id_client FROM client WHERE id_client IN (SELECT id_client FROM blacklist) AND login=? AND password=?";
	private static final String GET_CLIENT_DATA_SQL = "SELECT * FROM client WHERE login=? AND password=?";
	private static final String REGISTRATION_SQL = "INSERT INTO client (login, password, surname, name, registrationDate, phone, address, email) VALUES (?,?,?,?,?,?,?,?)";
	private static final String ADD_TO_BLACKLIST_SQL = "INSERT INTO blacklist (id_client, date_of_addition) VALUES(?,?)";
	private static final String REMOVE_FROM_BLACKLIST_SQL = "DELETE FROM blacklist WHERE id_client=?";
	private static final String LOGIN_CHECK_SQL = "SELECT id_client FROM client WHERE login=?";
	private static final String GET_CLIENTS_THAT_ARE_NOT_INCLUDED_IN_BLACKLIST_SQL = "SELECT id_client, login, password, surname, name, registrationDate, phone, address, email FROM client WHERE NOT id_client IN (SELECT id_client FROM blacklist)";
	private static final String GET_BLACKLIST_SQL = "SELECT id_client, login, password, surname, name, registrationDate, phone, address, email FROM client WHERE id_client IN (SELECT id_client FROM blacklist)";
	private static final String GET_ADDRESS_SQL = "SELECT address FROM client WHERE id_client=?";

	Logger logger = Logger.getLogger(SQLClientDAO.class);

	private final static SQLClientDAO instance = new SQLClientDAO();

	public static ClientDAO getInstance() {
		return instance;
	}

	@Override
	public boolean checkClient(String login, String password) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			statement = connection.prepareStatement(LOGIN_PASSWORD_CHECK_SQL);
			statement.setString(1, login);
			statement.setString(2, password);
			resultSet = statement.executeQuery();
			return resultSet.next();

		} catch (SQLException e) {
			throw new DAOException("SQLException in SQLClientDAO");
		} catch (ConnectionPoolException e) {
			logger.error("ConnectionPool didn't take connection. Message: " + e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
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
		return false;
	}

	@Override
	public boolean thereIsClientOnBlacklist(String login, String password) throws DAOException {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			statement = connection.prepareStatement(CHECK_CLIENT_IN_BLACKLIST_SQL);
			statement.setString(1, login);
			statement.setString(2, password);
			resultSet = statement.executeQuery();
			return resultSet.next();

		} catch (SQLException e) {
			throw new DAOException("SQLException in SQLClientDAO");
		} catch (ConnectionPoolException e) {
			logger.error("ConnectionPool didn't take connection. Message: " + e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
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
		return false;
	}

	@Override
	public Client getClient(String login, String password) throws DAOException {
		Client client = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			statement = connection.prepareStatement(GET_CLIENT_DATA_SQL);
			statement.setString(1, login);
			statement.setString(2, password);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				client = new Client();
				client.setId(resultSet.getInt(1));
				client.setLogin(resultSet.getString(2));
				client.setPassword(resultSet.getString(3));
				client.setSurname(resultSet.getString(4));
				client.setName(resultSet.getString(5));
				client.setRegistrationDate(resultSet.getDate(6));
				client.setPhone(resultSet.getString(7));
				client.setAddress(resultSet.getString(8));
				client.setEmail(resultSet.getString(9));
			}

		} catch (ConnectionPoolException e) {
			logger.error("ConnectionPool didn't take connection. Message: " + e.getMessage());
		} catch (SQLException e) {
			throw new DAOException("SQLException in SQLClientDAO");
		} finally {
			if (statement != null) {
				try {
					statement.close();
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
		return client;
	}

	@Override
	public boolean checkUniquenessOfLogin(String login) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionPool.getInstance().takeConnection();
			statement = connection.prepareStatement(LOGIN_CHECK_SQL);
			statement.setString(1, login);
			resultSet = statement.executeQuery();
			return !resultSet.next();
		} catch (ConnectionPoolException e) {
			logger.error("ConnectionPool didn't take connection. Message: " + e.getMessage());
		} catch (SQLException e) {
			throw new DAOException("SQLException in SQLClientDAO");
		} finally {
			if (statement != null) {
				try {
					statement.close();
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
		return true;
	}

	@Override
	public void registration(String login, String password, String surname, String name, Date registrationDate,
			String phone, String address, String email) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = ConnectionPool.getInstance().takeConnection();
			statement = connection.prepareStatement(REGISTRATION_SQL);
			statement.setString(1, login);
			statement.setString(2, password);
			statement.setString(3, surname);
			statement.setString(4, name);
			statement.setDate(5, registrationDate);
			statement.setString(6, phone);
			statement.setString(7, address);
			statement.setString(8, email);
			statement.executeUpdate();
		} catch (ConnectionPoolException e) {
			logger.error("ConnectionPool didn't take connection. Message: " + e.getMessage());
		} catch (SQLException e) {
			throw new DAOException("SQLException in SQLClientDAO");
		} finally {
			if (statement != null) {
				try {
					statement.close();
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
	public void addToBlacklict(int id_client) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		Date date_of_addition = new Date(System.currentTimeMillis());

		try {
			connection = ConnectionPool.getInstance().takeConnection();
			statement = connection.prepareStatement(ADD_TO_BLACKLIST_SQL);
			statement.setInt(1, id_client);
			statement.setDate(2, date_of_addition);
			statement.executeUpdate();
		} catch (ConnectionPoolException e) {
			logger.error("ConnectionPool didn't take connection. Message: " + e.getMessage());
		} catch (SQLException e) {
			throw new DAOException("SQLException in SQLClientDAO");
		} finally {
			if (statement != null) {
				try {
					statement.close();
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
	public void removeFromBlacklist(int id_client) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = ConnectionPool.getInstance().takeConnection();
			statement = connection.prepareStatement(REMOVE_FROM_BLACKLIST_SQL);
			statement.setInt(1, id_client);
			statement.executeUpdate();
		} catch (ConnectionPoolException e) {
			logger.error("ConnectionPool didn't take connection. Message: " + e.getMessage());
		} catch (SQLException e) {
			throw new DAOException("SQLException in SQLClientDAO");
		} finally {
			if (statement != null) {
				try {
					statement.close();
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
	public List<Client> getClientsThatAreNotIncludedInBlacklist() throws DAOException {
		Client client = null;
		List<Client> clientList = new ArrayList<Client>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			statement = connection.prepareStatement(GET_CLIENTS_THAT_ARE_NOT_INCLUDED_IN_BLACKLIST_SQL);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				client = new Client();
				client.setId(resultSet.getInt(1));
				client.setLogin(resultSet.getString(2));
				client.setPassword(resultSet.getString(3));
				client.setSurname(resultSet.getString(4));
				client.setName(resultSet.getString(5));
				client.setRegistrationDate(resultSet.getDate(6));
				client.setPhone(resultSet.getString(7));
				client.setAddress(resultSet.getString(8));
				client.setEmail(resultSet.getString(9));
				clientList.add(client);
			}
		} catch (ConnectionPoolException e) {
			logger.error("ConnectionPool didn't take connection. Message: " + e.getMessage());
		} catch (SQLException e) {
			throw new DAOException("SQLException in SQLClientDAO");
		} finally {
			if (statement != null) {
				try {
					statement.close();
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
		return clientList;
	}

	@Override
	public List<Client> getBlacklist() throws DAOException {
		Client client = null;
		List<Client> clientList = new ArrayList<Client>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionPool.getInstance().takeConnection();
			statement = connection.prepareStatement(GET_BLACKLIST_SQL);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				client = new Client();
				client.setId(resultSet.getInt(1));
				client.setLogin(resultSet.getString(2));
				client.setPassword(resultSet.getString(3));
				client.setSurname(resultSet.getString(4));
				client.setName(resultSet.getString(5));
				client.setRegistrationDate(resultSet.getDate(6));
				client.setPhone(resultSet.getString(7));
				client.setAddress(resultSet.getString(8));
				client.setEmail(resultSet.getString(9));
				clientList.add(client);
			}
		} catch (ConnectionPoolException e) {
			logger.error("ConnectionPool didn't take connection. Message: " + e.getMessage());
		} catch (SQLException e) {
			throw new DAOException("SQLException in SQLClientDAO");
		} finally {
			if (statement != null) {
				try {
					statement.close();
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
		return clientList;
	}

	@Override
	public String getAddressOfClient(int idClient) throws DAOException {
		String address = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			statement = connection.prepareStatement(GET_ADDRESS_SQL);
			statement.setInt(1, idClient);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				address = resultSet.getString(1);
			}

		} catch (ConnectionPoolException e) {
			logger.error("ConnectionPool didn't take connection. Message: " + e.getMessage());
		} catch (SQLException e) {
			throw new DAOException("SQLException in SQLClientDAO");
		} finally {
			if (statement != null) {
				try {
					statement.close();
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
		return address;
	}
}
