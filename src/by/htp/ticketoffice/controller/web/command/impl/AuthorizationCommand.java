package by.htp.ticketoffice.controller.web.command.impl;

import by.htp.ticketoffice.exceptions.CommandException;
import by.htp.ticketoffice.controller.web.command.BasicCommand;
import by.htp.ticketoffice.controller.web.servlet.util.JspPageName;
import by.htp.ticketoffice.controller.web.servlet.util.RequestParameterName;
import by.htp.ticketoffice.service.impl.AuthorizationService;

import javax.servlet.http.HttpServletRequest;



public class AuthorizationCommand implements BasicCommand {

	@Override
	public String performAction(HttpServletRequest request) throws CommandException {

		boolean result = false;
		String page = null;
		String login = request.getParameter(RequestParameterName.LOGIN);
		String password = request.getParameter(RequestParameterName.PASSWORD);

		if ((login != null) & (password != null)) {
			AuthorizationService authorization = AuthorizationService.getInstance();
			result = authorization.doService(request);
			if (result) {
				if (request.getSession(true).getAttribute(RequestParameterName.ADMIN) != null) {
					page = JspPageName.ADMIN_PAGE;
				} else if (request.getSession(true).getAttribute(RequestParameterName.CLIENT) != null) {
					page = JspPageName.MAIN_PAGE;
				}
			} else {
				page = JspPageName.AUTHORIZATION_PAGE;
			}
		}
		return page;
	}
}
