<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF8">
  <title>Niko Niko</title>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <link rel="stylesheet" href="/css/main.css">

</head>
<body>
    <header>
     <img src="/img/BaniereCGI.jpg">
  </header>

  <h1 align="center">Bienvenue sur notre interface Niko Smile</h1>

  <div id="main">

    <form action="login" method="post">
    <div>
        <label for="login">Login:</label>
        <input type="text" name="username" required="required"/>
    </div>
    <div>
        <label for="mdp">Mot de Passe:</label>
        <input type="password" name="password" required="required"/>
    </div>
    <br/>
    <#include "../includable/security/securityToken.ftl">
    <div class="button">
    <button type="submit" value="submit">Log In</button>
    </div>
</form>

  </div>

  <footer>
    <img src="/img/basdepage.jpg">
  </footer>
</body>
</html>