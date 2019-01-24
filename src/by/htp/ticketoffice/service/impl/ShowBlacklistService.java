package by.htp.ticketoffice.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.htp.ticketoffice.controller.web.servlet.util.RequestParameterName;
import by.htp.ticketoffice.dao.mysql.ClientDAO;
import by.htp.ticketoffice.exceptions.DAOException;
import by.htp.ticketoffice.dao.mysql.factory.DAOFactory;
import by.htp.ticketoffice.entity.Client;
import by.htp.ticketoffice.service.DBService;
import org.apache.log4j.Logger;



public class ShowBlacklistService implements DBService {

	private static final Logger logger = Logger.getLogger(ShowBlacklistService.class);

	private static final ShowBlacklistService instance = new ShowBlacklistService();

	public static ShowBlacklistService getInstance() {
		return instance;
	}

	@Override
	public boolean doService(HttpServletRequest request) {

		ClientDAO clientDAO = DAOFactory.getInstance().getClientDAO();
		List<Client> clientList = null;

		if (request.getSession(false).getAttribute(RequestParameterName.ADMIN) == null) {
			return false;
		}

		try {
			clientList = clientDAO.getBlacklist();
		} catch (DAOException e) {
			logger.error("ClientDAO didn't return clients which are in the blacklist. Message: " + e.getMessage());
		}

		if (!clientList.isEmpty()) {
			request.getSession(false).setAttribute(RequestParameterName.CLIENTS, clientList);
			return true;
		} else {
			request.getSession(false).setAttribute(RequestParameterName.CLIENTS, null);
			return false;
		}
	}
}
