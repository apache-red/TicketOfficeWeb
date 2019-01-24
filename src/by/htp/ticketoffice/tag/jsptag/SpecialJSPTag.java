package by.htp.ticketoffice.tag.jsptag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import by.htp.ticketoffice.dao.mysql.StationDAO;
import by.htp.ticketoffice.entity.Route;
import by.htp.ticketoffice.entity.Station;
import by.htp.ticketoffice.exceptions.DAOException;
import by.htp.ticketoffice.dao.mysql.factory.DAOFactory;
import org.apache.log4j.Logger;

public class SpecialJSPTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private Route route;
	
	private static final Logger logger = Logger.getLogger(SpecialJSPTag.class);

	public Route getRoute() {
		return route;
	}
// ?? change&=?
	public void setRoute(Route route) {
		this.route = route;
	}

	@Override
	public int doStartTag() throws JspException {

		JspWriter out = pageContext.getOut();
		//queue?
		List<Station> stationList = null;
		StationDAO stationDAO = DAOFactory.getInstance().getStationDAO();
		try {
			stationList = stationDAO.getStationOfRoute(route.getId());
		} catch (DAOException e) {
			logger.error("StationDAO didn't return station(s) of route. Message: " + e.getMessage());
		}
		try {
			for (Station station : stationList) {
				out.write("<tr>");
				out.write("<td> </td>");
				out.write("<td><font size=\"+1\">");
				out.write("<a href=\"controller?command=show_information_about_station&id_station=" + station.getId() + "\"> " + station.getName() + "</a> <br />");
				out.write("</font></td>");
				out.write("</tr>");
			}
		} catch (IOException e) {
			logger.error("Instance of JspWriter threw IOException. Message: " + e.getMessage());
		}
		return SKIP_BODY;
	}
}