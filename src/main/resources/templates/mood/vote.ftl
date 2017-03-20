<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF8">
  <title>Niko Niko</title>
      <link rel="stylesheet" href="/css/bootstrap.css">
      <link rel="stylesheet" href="/css/main.css">
</head>
<body>
    <header>
        <img src="/img/BaniereCGI.jpg">
    </header>

  <h1 align="center">Quel a été votre ressenti pour votre journée ?</h1>

  <div id="main">
    <div class="main-content">
        <div class="body-exercice">
            <form action="/mood/create/do" method="POST">
                <div class = "bon">
                <label for="bon">
                    <input id="bon" type="radio" name="satisfaction" value="1" checked>
                    <img src="/img/niko-vert-fond blanc.png" width="50%">
                </label>

            </br>

                <label for="moyen">
                    <input id="moyen" type="radio" name="satisfaction" value="0">
                    <img src="/img/niko-jaune-fond blanc.png" width="50%">
                </label>

            </br>

                <label for="mauvais">
                    <input id="mauvais" type="radio" name="satisfaction" value="-1">
                    <img src="/img/niko-rouge-fond blanc.png" width="50%">
                </label>
         </div class>
            </br>
            </br>

        <div class="button">
            <a href="mood/list"><input type="button" name="Répondre "value="Je ne veux pas voter"/></a>
        </div>
            </br>

      <textarea name="commentSat" id="message" cols="29" rows="2"  placeholder="Un commentaire ?"></textarea>

        <div class="button">
                <a href="user/login"><input type="submit" name="Répondre "value="Valider"/></a>
        </div>
            <td><input type="hidden" name="date" value="${date?string('yyyy/MM/dd HH:mm:ss')}" required="required"/></td>
      </fieldset>
    </form>


      </div>
    </div>
  </div>

  <footer>
    <img src="/img/basdepage.jpg">
  </footer>
</body>
</html>