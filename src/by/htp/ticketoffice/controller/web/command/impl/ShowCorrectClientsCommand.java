package by.htp.ticketoffice.controller.web.command.impl;

import by.htp.ticketoffice.exceptions.CommandException;
import by.htp.ticketoffice.controller.web.command.BasicCommand;
import by.htp.ticketoffice.controller.web.servlet.util.JspPageName;
import by.htp.ticketoffice.service.impl.ShowCorrectClientsService;

import javax.servlet.http.HttpServletRequest;



public class ShowCorrectClientsCommand implements BasicCommand {

	@Override
	public String performAction(HttpServletRequest request) throws CommandException {

		boolean result = false;
		String page = null;

		result = ShowCorrectClientsService.getInstance().doService(request);

		if (result) {
			page = JspPageName.ALL_CLIENTS_PAGE;
		}
		return page;
	}
}
