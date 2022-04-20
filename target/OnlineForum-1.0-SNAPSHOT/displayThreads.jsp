<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>VSB Forum</title>
    <link rel="shortcut icon" href="safari-pinned-tab.svg"/>
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
    List<Integer> numOfThreads = (List<Integer>) request.getAttribute("forumThreads");
    List<String> nameOfThreads = (List<String>) request.getAttribute("forumThreadNames");
    int i = 0;
    for (Integer tempI : numOfThreads) {
        out.println("<form action=\"ServletThread\" method=\"post\">");
        out.println("<p class=\"threads\">" + nameOfThreads.get(i) + ". Thread no. " + tempI);
        out.print("<input type=\"hidden\" name=\"idThread\" value=\"" + tempI + "\">");
        out.print("<input type=\"hidden\" name=\"canDelete\" value=\"" + request.getAttribute("canDelete") + "\">");
        out.print("<input class=\"minibuttons\" type=\"submit\" name=\"showForum\" value=\"Enter\">");
        if ((boolean) request.getAttribute("canDelete")) {
            out.println("<input class=\"minibuttons\" type=\"submit\" name=\"deleteThread\" value=\"Delete\">");
        }
        out.println("</p>");
        out.println("</form>");
        i++;
    }
%>
<br>
<br>
<br>
<br>
<div style="text-align:center;font-family:monospace;position: fixed;bottom: 0; right: 100;">
    <h2 style="font-size:25px;color:wheat">Create new thread</h2>
    <form action="ServletInsertThread" method="post">
        <label class="threads" for="threadName">Jméno: </label>
        <input type="text" id="threadName" name="threadName" minlength="2" maxlength="30" required autofocus>
        <input type="hidden" name="canDelete" value="<%=request.getAttribute("canDelete")%>">
        <input class="minibuttons" type="submit" name="createThread" value="Create new thread">
    </form>
    <p><a href="index.jsp">
        <button class="minibuttons" style="font-size: 18px">HOME</button>
    </a></p>
</div>
</body>
</html>