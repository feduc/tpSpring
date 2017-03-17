<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF8">
  <title>Niko Niko</title>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <link rel="stylesheet" href="assets/css/main.css">    

</head>
<body>
    <header>
     <img src="assets/img/BaniereCGI.jpg">
  </header>
  
  <h1 align="center">Bienvenue sur notre interface Niko Smile</h1>

  <div id="main">

    <form action="/security/login/do" method="get">
    <div>
        <label for="login">Login:</label>
        <input type="text" name="login" required="required"/>
    </div>
    <div>
        <label for="mdp">Mot de Passe:</label>
        <input type="text" name="password" required="required"/>
    </div>
    
    <div class="button">
    <button type="submit" value="submit">Log In</button>
    </div>
</form>

  </div>

  <footer>
    <img src="assets/img/basdepage.jpg">
  </footer>
</body>
</html>