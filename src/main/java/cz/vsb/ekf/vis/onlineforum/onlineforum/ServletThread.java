package cz.vsb.ekf.vis.onlineforum.onlineforum;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
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
        request.setCharacterEncoding("UTF-8");
        /*
        this servlet
        1. displays text of specific thread
        2. deletes chosen thread
        3. deletes chosen message
        attribute "run" was necessary to display text of specific thread when deleting messages or inserting them
         */
        if (request.getParameter("showForum") != null || request.getAttribute("run") != null) {
            List<String> forumText = new ArrayList<>();
            List<Integer> idOfText = new ArrayList<>();
            List<String> dateOfMessage = new ArrayList<>();
            String sendText = "";
            String id = request.getParameter("idThread");
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/forum", "root", "root")) {

                String prikaz = "SELECT * FROM forum.forumtext WHERE threadId = " + id + ";";

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(prikaz);

                while (resultSet.next()) {
                    forumText.add(resultSet.getString("text"));
                    idOfText.add(resultSet.getInt("id"));
                    dateOfMessage.add(resultSet.getString("dateOfText"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("idThread", id);
            request.setAttribute("forumText", forumText);
            request.setAttribute("dateOfText", dateOfMessage);
            request.setAttribute("canDelete", request.getParameter("canDelete"));
            if (request.getParameter("canDelete") == null) {
                request.setAttribute("canDelete", request.getAttribute("canDelete"));
            } else {
                request.setAttribute("canDelete", request.getParameter("canDelete"));
            }
            request.setAttribute("idOfText", idOfText);
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
        } else if (request.getParameter("deleteMessage") != null) {
            String id = request.getParameter("idOfText");
            String idThread = request.getParameter("idThread");
            String canDelete = request.getParameter("canDelete");
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/forum", "root", "root")) {

                String prikaz1 = "DELETE FROM forum.forumtext WHERE id = " + id + ";";

                PreparedStatement preparedStatement = connection.prepareStatement(prikaz1);

                preparedStatement.executeUpdate();


            } catch (SQLException e) {
                e.printStackTrace();
            }
            String run = "run";
            request.setAttribute("run", run);
            request.setAttribute("idThread", idThread);
            request.setAttribute("canDelete", canDelete);
            request.getRequestDispatcher("ServletThread").forward(request, response);
        }
    }
}
