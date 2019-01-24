package by.htp.ticketoffice.controller.web.command.impl;

import by.htp.ticketoffice.exceptions.CommandException;
import by.htp.ticketoffice.controller.web.command.BasicCommand;
import by.htp.ticketoffice.controller.web.servlet.util.JspPageName;
import by.htp.ticketoffice.controller.web.servlet.util.RequestParameterName;

import javax.servlet.http.HttpServletRequest;



public class LanguageCommand implements BasicCommand {

	@Override
	public String performAction(HttpServletRequest request) throws CommandException {
		request.getSession(true).setAttribute(RequestParameterName.LOCAL_NAME, request.getParameter(RequestParameterName.LOCAL_NAME));
		if (request.getSession(true).getAttribute(RequestParameterName.ADMIN) != null) {
			return JspPageName.ADMIN_PAGE;
		} else {
			return JspPageName.MAIN_PAGE;
		}
	}
}
