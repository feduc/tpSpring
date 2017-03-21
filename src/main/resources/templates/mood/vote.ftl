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
     <img src="/image/BaniereCGI.jpg">
  </header>
  
  <h1 align="center">Quel a été votre ressenti pour la journée du ${voteDate?string('yyyy/MM/dd')}?</h1>

  <div id="main">
    <div class="main-content">
    <div class="body-exercice">
    <form id= "formid" action="/mood/create/done" method="post">
    <input type="hidden" name="id" value="${child["id"]}" />
    <input type="hidden" name="agency" value="${child["agency"]}" />
    <input type="hidden" name="firstName" value="${child["firstName"]}" />
    <input type="hidden" name="lastName" value="${child["lastName"]}" />
    <input type="hidden" name="registrationCGI" value="${child["registrationCGI"]}" />
    <input type="hidden" name="verticale" value="${child["verticale"]}" />

        <div class = "bon">
        <label for="bon">
          <input id="bon" type="radio" name="satisfaction" value="1" checked>
          <img src="/image/niko-vert-fond blanc.png" width="50%">
        </label>
          
</br>


        <label for="moyen">
          <input id="moyen" type="radio" name="satisfaction" value="0">
          <img src="/image/niko-jaune-fond blanc.png" width="50%">
        </label>

</br>


      <label for="mauvais">
          <input id="mauvais" type="radio" name="satisfaction" value="-1">
          <img src="/image/niko-rouge-fond blanc.png" width="50%">
        </label>
         </div class>
</br>
</br>

        <div class="button">
            <a href="/../ResumeProjet"><input type="button" name="Répondre "value="Je ne veux pas voter"/></a>
        </div>
        </br>

      <textarea form = "formid" name="commentSat" id="message" cols="29" rows="2"  placeholder="Un commentaire ?"></textarea>
        <input type="hidden" name="logDate" value="${date?string('yyyy/MM/dd HH:mm:ss')}" />
        <input type="hidden" name="voteDate" value="${voteDate?string('yyyy/MM/dd HH:mm:ss')}" />
        <input type="hidden" name="MoodID" value="${MoodId}" />
        <div class="button">
            <button type="submit" value="submit">Voter</button>
        </div>
        
      </fieldset>

    </form>
    
      </div>
    </div>
  </div>

  <footer>
    <img src="/image/basdepage.jpg">

  </footer>
</body>
</html>