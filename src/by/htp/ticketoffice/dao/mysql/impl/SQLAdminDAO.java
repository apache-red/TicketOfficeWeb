package by.htp.ticketoffice.dao.mysql.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.htp.ticketoffice.dao.mysql.AdminDAO;
import by.htp.ticketoffice.exceptions.DAOException;
import by.htp.ticketoffice.dao.mysql.impl.connectionsPool.ConnectionPool;
import by.htp.ticketoffice.exceptions.ConnectionPoolException;
import by.htp.ticketoffice.entity.Admin;
import org.apache.log4j.Logger;



public class SQLAdminDAO implements AdminDAO {

	private static final String LOGIN_PASSWORD_CHECK_SQL = "SELECT id_administrator FROM Administrator WHERE login=? AND password=?";
	private static final String GET_ADMIN_DATA_SQL = "SELECT * FROM administrator WHERE login=? AND password=?";

	private static final Logger logger = Logger.getLogger(SQLAdminDAO.class);

	private final static SQLAdminDAO instance = new SQLAdminDAO();

	public static AdminDAO getInstance() {
		return instance;
	}

	@Override
	public boolean checkAdmin(String login, String password) throws DAOException {
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
			throw new DAOException("SQLException in SQLAdminDAO");
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
	public Admin getAdmin(String login, String password) throws DAOException {
		Admin admin = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			statement = connection.prepareStatement(GET_ADMIN_DATA_SQL);
			statement.setString(1, login);
			statement.setString(2, password);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				admin = new Admin();
				admin.setId(resultSet.getInt(1));
				admin.setLogin(resultSet.getString(2));
				admin.setPassword(resultSet.getString(3));
			}

		} catch (SQLException e) {
			throw new DAOException("SQLException in SQLAdminDAO");
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
		return admin;
	}
}
