<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF8">
<<<<<<< HEAD
  <title>Niko Niko</title>
=======
  <title>Niko Smile</title>
>>>>>>> c79c90bbed6d0157adab534d48c89dfab99db84a
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <link rel="stylesheet" href="/css/main.css">

</head>
<body>
    <header>
     <img src="/img/BaniereCGI.jpg">
  </header>

  <h1 align="center">Bienvenue sur notre interface Niko Smile</h1>

  <div id="main">

<<<<<<< HEAD
    <form action="/login" method="POST">
        <input type="text" name="username" required="required"/>
        <input type="password" name="password" required="required"/>
        <#include "../includable/security/securityToken.ftl">
        <input type="submit" value="validate"/>
    </form>

=======
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
>>>>>>> c79c90bbed6d0157adab534d48c89dfab99db84a

  <footer>
    <img src="/img/basdepage.jpg">
  </footer>
</body>
</html>