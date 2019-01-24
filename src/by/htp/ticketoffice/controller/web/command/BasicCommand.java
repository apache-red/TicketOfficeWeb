package by.htp.ticketoffice.controller.web.command;

import by.htp.ticketoffice.exceptions.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface BasicCommand {
	String performAction(HttpServletRequest req) throws CommandException;
}
