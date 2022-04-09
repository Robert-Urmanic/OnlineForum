package cz.vsb.ekf.vis.onlineforum.onlineforum;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletGetThreads", value = "/ServletGetThreads")
public class ServletGetThreads extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Integer> numOfThreads = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/forum", "root", "root")) {

            String prikaz = "SELECT id FROM forum.forumthread;";

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(prikaz);

            while (resultSet.next()) {
                numOfThreads.add(resultSet.getInt("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("forumThreads", numOfThreads);
        request.getRequestDispatcher("displayThreads.jsp").forward(request, response);
    }
}
