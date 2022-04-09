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
    .test{
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
  List<String> text = (ArrayList<String>)request.getAttribute("forumText");
  for (String tempS: text) {
    out.println("<div class=\"chan\"><p>" + tempS + " " +"</p></div>");
  }

%>
<br>
<br>
<p><a href="ServletGetThreads">HOME</a> </p>
</body>
</html>
