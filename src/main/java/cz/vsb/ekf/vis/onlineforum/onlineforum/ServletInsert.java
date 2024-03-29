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
        request.setCharacterEncoding("UTF-8");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/forum", "root", "root")) {
            String prikaz = "INSERT INTO forum.forumtext(text, threadId, dateOfText) VALUES (?,?,?);";

            PreparedStatement preparedStatement = connection.prepareStatement(prikaz);

            java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

            preparedStatement.setString(1, request.getParameter("text"));
            preparedStatement.setString(2, request.getParameter("idThread"));
            preparedStatement.setTimestamp(3, date);

            preparedStatement.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        String run = "run";
        request.setAttribute("run", run);
        request.setAttribute("canDelete", request.getParameter("canDelete"));
        request.getRequestDispatcher("ServletThread").forward(request, response);
    }
}
