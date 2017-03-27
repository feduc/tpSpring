<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF8">

  <title>Niko Smile</title>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <link rel="stylesheet" href="/css/main.css">

</head>
<body>
    <header>
     <img src="/img/BaniereCGI.jpg">
  </header>

  <h1 align="center">Bienvenue sur notre interface Niko Smile</h1>

  <div id="main">

    <form action="/login" method="post">
    <div>
        <label for="login">Login:</label>
        <input type="text" name="username" required="required"/>
    </div>
    <div>
        <label for="mdp">Mot de Passe:</label>
        <input type="password" name="password" required="required"/>
    </div>
    <div>
        <#include "../includable/security/securityToken.ftl">
    </div>
    <div class="button">
    <input type="submit" value="validate"/>
    </div>
</form>

  </div>


  <footer>
    <img src="/img/basdepage.jpg">
  </footer>
</body>
</html>