<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF8">
  <title>Niko Niko</title>
      <link rel="stylesheet" href="assets/css/bootstrap.css">
      <link rel="stylesheet" href="assets/css/main.css">    
</head>
<body>
    <header>
     <img src="assets/img/BaniereCGI.jpg">
  </header>
  
  <h1 align="center">Quel a été votre ressenti pour votre journée ?</h1>

  <div id="main">
    <div class="main-content">
    <div class="body-exercice">
    <form action="/ma-page-de-traitement" method="post">

        <div class = "bon">
        <label for="bon">
          <input id="bon" type="radio" name="avis" value="Très bon" checked>
          <img src="assets/img/niko-vert-fond blanc.png" width="50%">
        </label>
          
</br>


        <label for="moyen">
          <input id="moyen" type="radio" name="avis" value="Moyen">
          <img src="assets/img/niko-jaune-fond blanc.png" width="50%">
        </label>

</br>


      <label for="mauvais">
          <input id="mauvais" type="radio" name="avis" value="Moyen">
          <img src="assets/img/niko-rouge-fond blanc.png" width="50%">
        </label>
         </div class>
</br>
</br>

        <div class="button">
            <a href="resumeprojetemploye.html"><input type="button" name="Répondre "value="Je ne veux pas voter"/></a>
        </div>
        </br>

      <textarea name="message" id="message" cols="29" rows="2"  placeholder="Un commentaire ?"></textarea>

        <div class="button">
        <a href="resumeprojetemploye.html"><input type="button" name="Répondre "value="Valider"/></a>
        </div>
        
      </fieldset>
      
      
      
     
    </form>

 
      </div>
    </div>
  </div>

  <footer>
    <img src="assets/img/basdepage.jpg">
  </footer>
</body>
</html>