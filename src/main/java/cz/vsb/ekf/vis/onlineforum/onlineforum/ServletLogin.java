package cz.vsb.ekf.vis.onlineforum.onlineforum;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "ServletLogin", value = "/ServletLogin")
public class ServletLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("ServletGetThreads").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean login = false;

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/forum", "root", "root")) {

            String prikaz = "SELECT * FROM forum.adminlogin;";

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(prikaz);

            while (resultSet.next()) {
                if (resultSet.getString("name") == request.getParameter("loginName")
                        && resultSet.getString("password") == request.getParameter("loginPassword")) ;
                {
                    login = true;
                    break;
                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("canLogin", login);
        request.getRequestDispatcher("ServletGetThreads").forward(request, response);
    }
}
