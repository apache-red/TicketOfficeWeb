package by.htp.ticketoffice.service.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.ticketoffice.controller.web.servlet.util.RequestParameterName;
import by.htp.ticketoffice.dao.mysql.ClientDAO;
import by.htp.ticketoffice.exceptions.DAOException;
import by.htp.ticketoffice.dao.mysql.factory.DAOFactory;
import by.htp.ticketoffice.service.DBService;
import org.apache.log4j.Logger;



public class RemoveFromBlacklistService implements DBService {

	private static final Logger logger = Logger.getLogger(RemoveFromBlacklistService.class);

	private static final RemoveFromBlacklistService instance = new RemoveFromBlacklistService();

	public static RemoveFromBlacklistService getInstance() {
		return instance;
	}

	@Override
	public boolean doService(HttpServletRequest request) {

		int id_client = 0;
		boolean result = false;
		ClientDAO clientdDAO = null;

		id_client = Integer.parseInt(request.getParameter(RequestParameterName.ID_CLIENT));
		clientdDAO = DAOFactory.getInstance().getClientDAO();

		try {
			clientdDAO.removeFromBlacklist(id_client);
			result = true;
		} catch (DAOException e) {
			logger.error("ClientDAO didn't remove client from blacklist. Message: " + e.getMessage());
		}
		return result;
	}
}
