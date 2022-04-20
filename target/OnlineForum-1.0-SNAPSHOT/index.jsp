<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        .buttons {
            background-color:wheat;
            color:black;
            text-align:center;
            display:inline-block;
            font-size:30px;
            font-family:monospace;
            display:block;
            margin-left:auto;
            margin-right:auto
        }
    </style>
</head>
<body style="background-color:#04A297">
<h1 style="text-align:center;font-family:monospace;font-size:60px;">Welcome to VSB forum</h1>
<p><a href="ServletGetThreads"><button class="buttons">Enter the forum</button></a></p>
<!-- I hereby apologise to the poor soul who has to go through this code -->
<br>
<br>
<br>
<br>

<button class="buttons" type="button" onclick="document.getElementById('unhide').style.display='block'">Admin login
</button>
<form id="unhide" method="post" action="ServletLogin" style="display:none;width:400px;height:325px" class="buttons">
    <h1 style="font-variant-caps:all-small-caps;font-family:monospace">Login form</h1>
    <p>
        <label for="loginName">Name: </label>
        <input type="text" id="loginName" name="loginName" minlength="2" maxlength="30"
               required autofocus>
    </p>
    <p>
        <label for="loginPassword">Password: </label>
        <input type="password" id="loginPassword" name="loginPassword" minlength="3" maxlength="30"
               required>
    </p>
    <p>
        <input class="buttons" style="background-color:#04A297" type="submit" name="prihlaseni" value="Log in">
    </p>
</form>
</body>
</html>