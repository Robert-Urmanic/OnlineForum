<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>VSB Forum</title>
    <link rel="shortcut icon" href="safari-pinned-tab.svg" />
</head>
<body>
<%
    List<Integer> numOfThreads = (List<Integer>) request.getAttribute("forumThreads");
    for (Integer tempI: numOfThreads) {
        out.println("<form action=\"ServletThread\" method=\"post\">");
        out.println("<p>Thread no. " + tempI + "</p>");
        out.println("<input type=\"hidden\" name=\"idThread\" value=\"" + tempI + "\">");
        out.println("<input type=\"submit\" name=\"showForum\" value=\"Enter\">");
        if ((boolean)request.getAttribute("canDelete")){
            out.println("<input type=\"submit\" name=\"deleteThread\" value=\"Delete\">");
        }
        out.println("</form>");
    }
%>
<p>${numOfThreads}</p>
</body>
</html>
