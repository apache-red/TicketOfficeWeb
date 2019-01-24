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



public class SpecialJSPTagForAdmin extends TagSupport {
	private static final long serialVersionUID = 1L;
	private Route route;
	private String nameedit;
	private String namedelete;
	private String nameadd;
	
	private static final Logger logger = Logger.getLogger(SpecialJSPTagForAdmin.class);

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public String getNameedit() {
		return nameedit;
	}

	public void setNameedit(String nameedit) {
		this.nameedit = nameedit;
	}

	public String getNamedelete() {
		return namedelete;
	}

	public void setNamedelete(String namedelete) {
		this.namedelete = namedelete;
	}
	
	public String getNameadd() {
		return nameadd;
	}

	public void setNameadd(String nameadd) {
		this.nameadd = nameadd;
	}

	@Override
	public int doStartTag() throws JspException {

		JspWriter out = pageContext.getOut();
		List<Station> stationList = null;
		StationDAO stationDAO = DAOFactory.getInstance().getStationDAO();
		try {
			stationList = stationDAO.getStationOfRoute(route.getId());
		} catch (DAOException e) {
			logger.error("StationDAO didn't return stations of route. Message: " + e.getMessage());
		}
		try {
			for (Station station : stationList) {
				out.write("<tr>");
				out.write("<td> </td>");
				out.write("<td><font size=\"+1\">");
				out.write("<a href=\"controller?command=show_information_about_station&id_station=" + station.getId() + "\"> " + station.getName() + "</a> <br />");
				out.write("</font></td>");
				out.write("<td>");
				out.write("<form action=\"controller\" method=\"post\">");
				out.write("<input type=\"hidden\" name=\"command\" value=\"go_edit_station\" />");
				out.write("<input type=\"hidden\" name=\"id_station\" value=\"" + station.getId() + "\" />");
				out.write("<input type=\"submit\" value=\"" + nameedit + "\" class=\"button2\" />");
				out.write("</form>");
				out.write("</td>");
				out.write("<td>");
				out.write("<form action=\"controller\" method=\"post\">");
				out.write("<input type=\"hidden\" name=\"command\" value=\"remove_station\" />");
				out.write("<input type=\"hidden\" name=\"id_station\" value=\"" + station.getId() + "\" />");
				out.write("<input type=\"submit\" value=\"" + namedelete + "\" class=\"button2\"/>");
				out.write("</form>");
				out.write("</td>");
				out.write("</tr>");
			}
			out.write("<tr><td></td>");
			out.write("<td>");
			out.write("<form action=\"controller\" method=\"post\">");
			out.write("<input type=\"hidden\" name=\"command\" value=\"go_add_station\" />");
			out.write("<input type=\"hidden\" name=\"id_route\" value=\"" + route.getId() + "\" />");
			out.write("<small><input type=\"submit\" value=\"" + nameadd + "\" class=\"button2\" /></small>");
			out.write("</form>");
			out.write("</td>");
			out.write("</tr>");
		} catch (IOException e) {
			logger.error("Instance of JspWriter threw IOException. Message: " + e.getMessage());
		}
		return SKIP_BODY;
	}
}
