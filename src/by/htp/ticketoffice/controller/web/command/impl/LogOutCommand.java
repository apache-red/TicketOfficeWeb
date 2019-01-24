package by.htp.ticketoffice.controller.web.command.impl;

import by.htp.ticketoffice.exceptions.CommandException;
import by.htp.ticketoffice.controller.web.command.BasicCommand;
import by.htp.ticketoffice.controller.web.servlet.util.JspPageName;
import by.htp.ticketoffice.controller.web.servlet.util.RequestParameterName;

import javax.servlet.http.HttpServletRequest;



public class LogOutCommand implements BasicCommand {

	@Override
	public String performAction(HttpServletRequest request) throws CommandException {
		if ((request.getSession(false).getAttribute(RequestParameterName.CLIENT) != null)
				|| (request.getSession(false).getAttribute(RequestParameterName.ADMIN) != null)) {
			request.getSession(false).invalidate();
		}
		return JspPageName.MAIN_PAGE;
	}
}
