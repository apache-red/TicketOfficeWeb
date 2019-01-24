package by.htp.ticketoffice.controller.web.command.impl;

import by.htp.ticketoffice.exceptions.CommandException;
import by.htp.ticketoffice.controller.web.command.BasicCommand;
import by.htp.ticketoffice.controller.web.servlet.util.JspPageName;
import by.htp.ticketoffice.controller.web.servlet.util.RequestParameterName;

import javax.servlet.http.HttpServletRequest;



public class GoToAdminPageCommand implements BasicCommand {

	@Override
	public String performAction(HttpServletRequest request) throws CommandException {
		String page = null;

		if (request.getSession().getAttribute(RequestParameterName.ADMIN) != null) {
			page = JspPageName.ADMIN_PAGE;
		}
		return page;
	}

}
