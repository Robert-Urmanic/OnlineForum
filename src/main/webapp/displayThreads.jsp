<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>VSB Forum</title>
    <link rel="shortcut icon" href="safari-pinned-tab.svg"/>
    <meta charset="UTF-8">
</head>
<body>
<%
    List<Integer> numOfThreads = (List<Integer>) request.getAttribute("forumThreads");
    List<String> nameOfThreads = (List<String>) request.getAttribute("forumThreadNames");
    int i = 0;
    for (Integer tempI : numOfThreads) {
        out.println("<form action=\"ServletThread\" method=\"post\">");
        out.println("<p>" + nameOfThreads.get(i) + ". Thread no. " + tempI + "</p>");
        out.println("<input type=\"hidden\" name=\"idThread\" value=\"" + tempI + "\">");
        out.println("<input type=\"hidden\" name=\"canDelete\" value=\"" + request.getAttribute("canDelete") + "\">");
        out.println("<input type=\"submit\" name=\"showForum\" value=\"Enter\">");
        if ((boolean) request.getAttribute("canDelete")) {
            out.println("<input type=\"submit\" name=\"deleteThread\" value=\"Delete\">");
        }
        out.println("</form>");
        i++;
    }
%>
<h2>Create new thread</h2>
<form action="ServletInsertThread" method="post">
    <label for="threadName">Jméno: </label>
    <input type="text" id="threadName" name="threadName" minlength="2" maxlength="30" required autofocus>
    <input type="hidden" name="canDelete" value="<%=request.getAttribute("canDelete")%>">
    <input type="submit" name="createThread" value="Create new thread">
</form>
<p><a href="index.jsp">HOME</a></p>
</body>
</html>