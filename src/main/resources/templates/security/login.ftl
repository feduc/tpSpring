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

    <form action="/login" method="POST">
        <input type="text" name="username" required="required"/>
        <input type="password" name="password" required="required"/>
        <#include "../includable/security/securityToken.ftl">
        <input type="submit" value="validate"/>
    </form>


  <footer>
    <img src="/img/basdepage.jpg">
  </footer>
</body>
</html>