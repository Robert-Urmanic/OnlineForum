package cz.vsb.ekf.vis.onlineforum.onlineforum;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "ServletThread", value = "/ServletThread")
public class ServletThread extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("showForum") != null || request.getAttribute("run") != null) {
            List<String> forumText = new ArrayList<>();
            String sendText = "";
            String id = request.getParameter("idThread");
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/forum", "root", "root")) {

                String prikaz = "SELECT text FROM forum.forumtext WHERE threadId = " + id + ";";

                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery(prikaz);

                while (resultSet.next()) {
                    forumText.add(resultSet.getString("text"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("idThread", id);
            request.setAttribute("forumText", forumText);
            request.getRequestDispatcher("forumIndex.jsp").forward(request, response);
        } else if (request.getParameter("deleteThread") != null) {
            String id = request.getParameter("idThread");
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/forum", "root", "root")) {

                String prikaz1 = "DELETE FROM forum.forumtext WHERE threadId = " + id + ";";

                PreparedStatement preparedStatement = connection.prepareStatement(prikaz1);

                preparedStatement.executeUpdate();

                String prikaz2 = "DELETE FROM forum.forumthread WHERE id = " + id + ";";

                preparedStatement = connection.prepareStatement(prikaz2);

                preparedStatement.executeUpdate();


            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("ServletGetThreads").forward(request, response);
        }
    }
}
