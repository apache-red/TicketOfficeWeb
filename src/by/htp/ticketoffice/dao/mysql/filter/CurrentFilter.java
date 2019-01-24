package by.htp.ticketoffice.dao.mysql.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

public class CurrentFilter implements Filter {

	private ServletContext context;

	private static final Logger logger = Logger.getLogger(CurrentFilter.class);

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("Filter initialized");
		logger.info("Filter initialized");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		String encoding = request.getCharacterEncoding();

		if (!"UTF-8".equalsIgnoreCase(encoding)) {
			response.setCharacterEncoding("UTF-8");
		}

		filterChain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		logger.info("Filter destroyed");
	}
}
