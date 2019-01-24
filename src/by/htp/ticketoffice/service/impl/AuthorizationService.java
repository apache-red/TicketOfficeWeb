package by.htp.ticketoffice.service.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.ticketoffice.controller.web.servlet.util.RequestParameterName;
import by.htp.ticketoffice.dao.mysql.AdminDAO;
import by.htp.ticketoffice.dao.mysql.ClientDAO;
import by.htp.ticketoffice.exceptions.DAOException;
import by.htp.ticketoffice.dao.mysql.factory.DAOFactory;
import by.htp.ticketoffice.entity.Admin;
import by.htp.ticketoffice.entity.Client;
import by.htp.ticketoffice.service.DBService;
import org.apache.log4j.Logger;



public class AuthorizationService implements DBService {

	private static final Logger logger = Logger.getLogger(AuthorizationService.class);

	private final static AuthorizationService instance = new AuthorizationService();

	public static AuthorizationService getInstance() {
		return instance;
	}


	// ADD cokies !
	@Override
	public boolean doService(HttpServletRequest request) {

		AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
		ClientDAO clientDAO = DAOFactory.getInstance().getClientDAO();
		String login = request.getParameter(RequestParameterName.LOGIN);
		String password = request.getParameter(RequestParameterName.PASSWORD);

		try {
			if (adminDAO.checkAdmin(login, password)) {
				Admin admin = adminDAO.getAdmin(login, password);
				request.getSession(true).setAttribute(RequestParameterName.ADMIN, admin);
				logger.info("AdminDAO checked an administrator");
				return true;
			} else if (clientDAO.checkClient(login, password)) {
				if (clientDAO.thereIsClientOnBlacklist(login, password)) {
					request.getSession(true).setAttribute(RequestParameterName.ERROR_AUTHORIZATION, 2);
					logger.warn("Client can't log in. He is on the blacklist");
					return false;
				} else {
					Client client = clientDAO.getClient(login, password);
					request.getSession(true).setAttribute(RequestParameterName.CLIENT, client);
					logger.info("ClientDAO checked a client");
					return true;
				}
			} else {
				request.getSession(true).setAttribute(RequestParameterName.ERROR_AUTHORIZATION, 1);
				logger.warn("Client made mistake when entering login or password");
				return false;
			}
		} catch (DAOException e) {
			logger.error("AdminDAO or ClientDAO didn't check administrator or client respectively. Message: "
					+ e.getMessage());
		}
		return false;
	}
}
