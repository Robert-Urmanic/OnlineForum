package cz.vsb.ekf.vis.onlineforum.onlineforum;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "ServletInsertThread", value = "/ServletInsertThread")
public class ServletInsertThread extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/forum", "root", "root")) {
            String prikaz = "INSERT INTO forum.forumthread(threadName) VALUES (?);";

            PreparedStatement preparedStatement = connection.prepareStatement(prikaz);

            preparedStatement.setString(1, request.getParameter("threadName"));

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        String run = "run";
        request.setAttribute("run",run);
        request.getRequestDispatcher("ServletGetThreads").forward(request, response);
    }
}
