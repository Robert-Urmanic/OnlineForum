<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <style>
        .threads {
            font-size: 17px;
            color: wheat;
            font-family: monospace;
        }

        .minibuttons {
            background-color: wheat;
            font-size: 13px;
            font-family: monospace;
        }
    </style>
</head>
<body style="background-color:#04A297">
<%

%>
<form class="threads" method="post" action="ServletInsert">
    <label style="font-weight: bold; font-size: 20px" for="text">Zadejte text:</label>
    <input type="text" id="text" name="text">
    <input class="minibuttons" type="submit" value="Odeslat">
    <%
        out.println("<input type=\"hidden\" name=\"idThread\" value=\"" + request.getAttribute("idThread") + "\">");
        out.println("<input type=\"hidden\" name=\"canDelete\" value=\"" + request.getAttribute("canDelete") + "\">");
    %>
</form>
<div  class="threads">
<%
    List<String> text = (ArrayList<String>) request.getAttribute("forumText");
    List<String> dateOfText = (ArrayList<String>) request.getAttribute("dateOfText");
    List<Integer> idOfText = (ArrayList<Integer>) request.getAttribute("idOfText");
    int i = 0;
    for (String tempS : text) {
        out.println("<form action=\"ServletThread\" method=\"post\">");
        {
            out.println("<p class=\"threads\">" + "<p class=\"threads\" style=\"font-style: oblique;text-decoration: underline;\">" + dateOfText.get(i) + "</p> - " + tempS);
        }
        if (request.getAttribute("canDelete") != null) {
            if (request.getAttribute("canDelete").equals("true")) {
                out.println("<input class=\"minibuttons\" type=\"submit\" name=\"deleteMessage\" value=\"Delete\">");
                out.println("<input type=\"hidden\" name=\"idOfText\" value=\"" + idOfText.get(i) + "\">");
                out.println("<input type=\"hidden\" name=\"canDelete\" value=\"true\">");
                out.println("<input type=\"hidden\" name=\"idThread\" value=\"" + request.getAttribute("idThread") + "\">");
            }
        }
        i++;
        out.println("</form>");
    }

%>
</div>
<br>
<br>
<p><a href="ServletGetThreads"><button style="font-size: 18px" class="minibuttons">HOME</button></a></p>
</body>
</html>
