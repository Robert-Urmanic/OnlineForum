<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
<h1>Vítejte na VŠB fóru</h1>
<p><a href="ServletGetThreads">Vstoupit do fóra</a></p>
<!-- I hereby apologise to the poor soul who had to go through this code -->
<br>
<br>
<br>
<br>
<br>
<button type="button" onclick="document.getElementById('unhide').style.display='block'"><p>Přihlášení pro adminy
</button>
<form id="unhide" method="post" action="ServletLogin" style="display:none">
    <h1 style="font-variant-caps:all-small-caps;font-family:monospace">Přihlášení</h1>
    <p>
        <label for="loginName">Jméno: </label>
        <input type="text" id="loginName" name="loginName" minlength="2" maxlength="30"
               required autofocus>
    </p>
    <p>
        <label for="loginPassword">Heslo: </label>
        <input type="password" id="loginPassword" name="loginPassword" minlength="3" maxlength="30"
               required>
    </p>
    <p>
        <input type="submit" name="prihlaseni" value="Přihlásit se">
    </p>
</form>
</body>
</html>