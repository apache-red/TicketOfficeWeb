package by.htp.ticketoffice.service.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.ticketoffice.controller.web.servlet.util.RequestParameterName;
import by.htp.ticketoffice.dao.mysql.ClientDAO;
import by.htp.ticketoffice.exceptions.DAOException;
import by.htp.ticketoffice.dao.mysql.factory.DAOFactory;
import by.htp.ticketoffice.service.DBService;
import org.apache.log4j.Logger;



public class AddToBlacklistService implements DBService {

	private static final Logger logger = Logger.getLogger(AddToBlacklistService.class);

	private static final AddToBlacklistService instance = new AddToBlacklistService();

	public static AddToBlacklistService getInstance() {
		return instance;
	}

	@Override
	public boolean doService(HttpServletRequest request) {

		int idClient = 0;
		boolean result = false;
		ClientDAO clientDAO;

		idClient = Integer.parseInt(request.getParameter(RequestParameterName.ID_CLIENT));
		clientDAO = DAOFactory.getInstance().getClientDAO();

		try {
			clientDAO.addToBlacklict(idClient);
			result = true;
		} catch (DAOException e) {
			logger.error("ClientDAO didn't client to blacklist. Message: " + e.getMessage());
		}
		return result;
	}
}
