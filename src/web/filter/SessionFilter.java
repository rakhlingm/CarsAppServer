package web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import facade.AdminFacade;
import service.AdminResource;


@WebFilter("/rest/*")
public class SessionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// pre processing
		String url = ((HttpServletRequest) request).getRequestURI();
		HttpSession session = ((HttpServletRequest) request).getSession(false);

		if (url.startsWith("/CarsApp/rest/Admin/")
				|| url.startsWith("/CarsApp/rest/Company/loginCompany") || url.startsWith("/CarsApp/rest/downloadfile")
				|| url.startsWith("/CarsApp/rest/file")) {
			System.out.println("Request was send...");
			chain.doFilter(request, response);
			return;
		} else if (session == null) {
				HttpServletResponse res = (HttpServletResponse) response;
				res.getWriter().println("{\"error\":\"you are not logged in\"}");
				res.setContentType(MediaType.APPLICATION_JSON);
				res.setStatus(500);
				return;
		} else 	if (url.startsWith("/CarsApp/rest/Admin")) {
				// url admin/coompany/cust
				AdminFacade af = ((AdminFacade) session.getAttribute(AdminResource.Admin_Facade));
				if (af != null){
					chain.doFilter(request, response);
					return;
				}
//		} else if (url.startsWith("/Project/rest/Company")) {
//					CompanyFacade cf = (CompanyFacade) session.getAttribute(CompanyResourse.Company_Facade);
//					if (cf != null){
//						chain.doFilter(request, response);
//						return;
//					}
		} else {
				HttpServletResponse res = (HttpServletResponse) response;
				res.getWriter().println("{\"error\":\"you are not logged in\"}");
				res.setContentType(MediaType.APPLICATION_JSON);
				res.setStatus(500);
				return;
				}
		}
	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}