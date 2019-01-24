package by.htp.ticketoffice.controller.web.command.impl;

import by.htp.ticketoffice.exceptions.CommandException;
import by.htp.ticketoffice.controller.web.command.BasicCommand;
import by.htp.ticketoffice.controller.web.servlet.util.JspPageName;
import by.htp.ticketoffice.service.impl.RegistrationService;

import javax.servlet.http.HttpServletRequest;



public class RegistrationCommand implements BasicCommand {

	@Override
	public String performAction(HttpServletRequest request) throws CommandException {
		String page = null;
		boolean result = RegistrationService.getInstance().doService(request);

		if (result) {
			page = JspPageName.SUCCESSFUL_REGISTRATION_PAGE;
		} else {
			page = JspPageName.REGISTRATION_PAGE;
		}
		return page;
	}
}
