package cz.vsb.ekf.vis.onlineforum.onlineforum;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "ServletInsert", value = "/ServletInsert")
public class ServletInsert extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/forum", "root", "root")) {
            String prikaz = "INSERT INTO forum.forumtext(text, threadId) VALUES (?,?);";

            PreparedStatement preparedStatement = connection.prepareStatement(prikaz);

            preparedStatement.setString(1, request.getParameter("text"));
            preparedStatement.setString(2, request.getParameter("idThread"));

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("ServletThread").forward(request, response);
    }
}
