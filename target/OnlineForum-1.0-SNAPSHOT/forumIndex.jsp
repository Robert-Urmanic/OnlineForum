<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
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
        out.println("<input type=\"hidden\" name=\"canDelete\" value=\"" + request.getAttribute("canDelete") + "\">");
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
        if (request.getAttribute("canDelete") != null) {
            if (request.getAttribute("canDelete").equals("true")) {
                out.println("<input type=\"submit\" name=\"deleteMessage\" value=\"Delete\">");
                out.println("<input type=\"hidden\" name=\"idOfText\" value=\"" + idOfText.get(i) + "\">");
                out.println("<input type=\"hidden\" name=\"canDelete\" value=\"true\">");
                out.println("<input type=\"hidden\" name=\"idThread\" value=\"" + request.getAttribute("idThread") + "\">");
            }
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
