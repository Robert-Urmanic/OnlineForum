<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDateTime" %><%--
  Created by IntelliJ IDEA.
  User: urman
  Date: 03.04.2022
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .test {
            background-color: aqua;
            margin: 10px;
            padding: 5px;
            width: max-content;
            height: max-content;
            text-align: left;
        }
    </style>
</head>
<body>
<%

%>
<form method="post" action="ServletInsert">
    <label for="text">Zadejte text:</label>
    <input type="text" id="text" name="text">
    <input type="submit" value="Odeslat">
    <%
        out.println("<input type=\"hidden\" name=\"idThread\" value=\"" + request.getAttribute("idThread") + "\">");
    %>
</form>

<%
    List<String> text = (ArrayList<String>) request.getAttribute("forumText");
    List<Integer> idOfText = (ArrayList<Integer>) request.getAttribute("idOfText");
    int i = 0;
    for (String tempS : text) {
        out.println("<form action=\"ServletThread\" method=\"post\">");
        {
            out.println("<div class=\"test\"><p>" + tempS + "</div>");
        }
        // make form redirect to new servlet which deletes message
        if (request.getAttribute("canDelete").equals("true")) {
            out.println("<input type=\"submit\" name=\"deleteMessage\" value=\"Delete\">");
            out.println("<input type=\"hidden\" name=\"idOfText\" value=\"" + idOfText.get(i) + "\">");
            out.println("<input type=\"hidden\" name=\"canDelete\" value=\"true\">");
            out.println("<input type=\"hidden\" name=\"idThread\" value=\"" + request.getAttribute("idThread") + "\">");
        }
        i++;
        out.println("</form>");
    }
// </p>
%>
<br>
<br>
<p><a href="ServletGetThreads">HOME</a></p>
</body>
</html>
