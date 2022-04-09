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
    }
}
