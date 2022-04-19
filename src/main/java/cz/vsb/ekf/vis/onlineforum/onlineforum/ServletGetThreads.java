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
    boolean canDelete = false;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getAttribute("canLogin") != null) {
            canDelete = (boolean) request.getAttribute("canLogin");
        }
        List<Integer> numOfThreads = new ArrayList<>();
        List<String> nameOfThreads = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/forum", "root", "root")) {

            String prikaz = "SELECT * FROM forum.forumthread;";

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(prikaz);

            while (resultSet.next()) {
                numOfThreads.add(resultSet.getInt("id"));
                nameOfThreads.add(resultSet.getString("threadName"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("canDelete", canDelete);
        request.setAttribute("forumThreads", numOfThreads);
        request.setAttribute("forumThreadNames", nameOfThreads);
        request.getRequestDispatcher("displayThreads.jsp").forward(request, response);
    }
}
