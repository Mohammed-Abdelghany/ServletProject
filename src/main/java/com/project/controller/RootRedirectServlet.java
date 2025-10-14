package com.project.controller;

import jakarta.servlet.http.*;
import java.io.IOException;

public class RootRedirectServlet extends HttpServlet {
	

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.sendRedirect(request.getContextPath() + "/CrudItemsProject/login.jsp");

    }
}
