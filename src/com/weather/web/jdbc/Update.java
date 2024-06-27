package com.weather.web.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Resource(name="jdbc/weather_app")	
	private CityDBUtil cityDBUtil;

	private DataSource dataSource;

    public void init() throws ServletException {
        super.init();
        try {
            cityDBUtil = new CityDBUtil(dataSource);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			getList(request,response);
		}catch (ServletException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String username= request.getParameter("username");
		List<String> citylist= cityDBUtil.getCity(username);
		request.setAttribute("User_City_List",citylist);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/updates.jsp");
		dispatcher.forward(request,response);
	}

}
