package moten.david.util.google.app.engine.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class LoginFilter implements Filter {

	private UserService userService;

	public LoginFilter() {
		userService = UserServiceFactory.getUserService();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		if (userService.getCurrentUser() != null) {
			chain.doFilter(request, response);
		} else if (response instanceof HttpServletResponse)
			((HttpServletResponse) response).sendRedirect(userService
					.createLoginURL(((HttpServletRequest) request)
							.getRequestURI()));
		else

			throw new ServletException(
					"Unauthorized access, unable to forward to login page");
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
