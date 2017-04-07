<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
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
                    <font color = "white"> <th>Ancien Mot de passe:</th></br></font>
                    <input type="password" name="oldPassword" placeholder="ancien mot de passe"/>
                    </br><font color = "white"> <th>Nouveau Mot de Passe :</th></br></font>
                    <input type="password" name="newPassword1" placeholder="nouveau mot de passe" />
                    </br><font color = "white"> <th>Confirmer votre Nouveau Mot de Passe :</th></br></font>
                    <input type="password" name="newPassword2" placeholder="confirmer"/>
               <br/>
               <br/>
                <button type="submit" value="submit">Valider</button>
            </form>
            <#if changePass??>
            <#if changePass == true>
                <p class = "alertMessage">Changement de mot de passe pris en compte</p>
            <#else>
                <p class = "alertMessage">Changement de mot de passe non pris en compte</p>
            </#if>
            </#if>
            <form id= "formid" action="/user/resume/" method="get">
                <div>
                    <#include "../includable/security/securityToken.ftl">
                </div>
                <hr style= "width:75%"/>
            <input style="width:20%" type="submit" value="Retour page perso"/>
            </form>
             </div>
        </div>

    <footer>
      <img src='/img/basdepage.jpg'>
    </footer>
  </body>
</html>