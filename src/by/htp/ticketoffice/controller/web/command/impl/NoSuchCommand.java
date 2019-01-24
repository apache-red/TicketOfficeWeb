package by.htp.ticketoffice.controller.web.command.impl;

import by.htp.ticketoffice.exceptions.CommandException;
import by.htp.ticketoffice.controller.web.command.BasicCommand;
import by.htp.ticketoffice.controller.web.servlet.util.JspPageName;

import javax.servlet.http.HttpServletRequest;



public class NoSuchCommand implements BasicCommand {

	@Override
	public String performAction(HttpServletRequest request) throws CommandException {
		return JspPageName.ERROR_PAGE;
	}
}
