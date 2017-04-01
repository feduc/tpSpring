<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>
    <script src="/Chart.js-master/src/chart.js"></script>
      <link rel="stylesheet" href="/css/bootstrap.css">
      <link rel="stylesheet" href="/css/main1.css">
  </head>
  <body>
      <header>
      <img src='/img/BaniereCGI.jpg'>
    </header>
    <div align = right>

      <a href =/user/parameters><img src='/img/parametre.png' alt='exemple' width='5%'></a>
      <a href =/logout><img src='/img/logout.jpg' alt='exemple' width='5%'></a>
    </div>
      <div class="col-xs-12 col-sm-8 col-md-12">
         <div class="changeavis">
            <font color = "white"><h1> Modification de votre mot de passe </h1> </font>
                <form action="/user/parameters/do" method="POST" >
                      <div>
                            <#include "../includable/security/securityToken.ftl">
                      </div>
                    <font color = "white"> <th>Ancien Mot de passe ${password}:</th></br></font>
                    <input type="text" name="oldpassword" placeholder="entrez votre ancien mot de passe" value="${oldPassword}"/>
                    </br><font color = "white"> <th>Nouveau Mot de Passe :</th></br></font>
                    <input type="text" name="newpassword1" placeholder="entrez votre nouveau mot de passe" value="${newPassword1}"/>
                    </br><font color = "white"> <th>Confirmer votre Nouveau Mot de Passe :</th></br></font>
                    <input type="text" name="newpassword2" placeholder="entrez votre nouveau mot de passe" value="${newPassword2}"/>
               </br>
                <button type="submit" value="submit">Valider</button>
            </form>
             </div>
        </div>

    <footer>
      <img src='/img/basdepage.jpg'>
    </footer>
  </body>
</html>