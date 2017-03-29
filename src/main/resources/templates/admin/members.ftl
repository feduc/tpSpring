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
        <img src="/img/BaniereCGI.jpg">
    </header>
<div align = right><a href ="page de vote.html"><img src="/img/parametre.png" alt="exemple" width="5%"></a>
                    <a href =/security/login/><img src="/img/logout.jpg" alt="exemple" width="5%"></a></div>
  <h1 align="center"> Page d'administration</h1>

  <div id="main">

<div class="container">
      <div class="row">
      <#include "../includable/adminNav.ftl">
      <div class="col-xs-12 col-sm-8 col-md-8">
         <div class="changeavis">
            <font color = "white"><h1> Choisissez un projet </h1> </font>
                <form action="/admin/${projectId}/members" method="GET" >
                    <font color = "white"> <th>Nom de l'utilisateur :</th></br></font>
                    <input type="hidden" name="projectName" value="${projectName}"/>
                    <input type="text" name="userRegistration" placeholder="entrez le matricule de l'utilisateur" value="${userRegistration}"/>

               </br>
                <button type="submit" value="submit">Chercher</button>
            </form>
             </div>
        </div>
      <div class="col-xs-12 col-sm-8 col-md-8 pull-right">
        <table class="table">
            <thead>
                <tr>
                    <th>Prénom</th>
                    <th>Nom</th>
                    <th>Matricule</th>
                    <th>Ajouter au Projet</th>
                </tr>
            </thead>
            <tbody>
                <#if users??>
                <#list users as user>
                    <tr>
                        <td>${user["firstName"]}</td>
                        <td>${user["lastName"]}</td>
                        <td>${user["registrationCGI"]}</td>
                        <td>
                            <form action="/admin/${projectId}/members/${user["id"]}/add" method ="POST">
                            <input type="hidden" value="${projectName}"/>
                            <input type="submit" value="Ajouter"/>
                            </form>
                        </td>
                    </tr>
                </#list>
                </#if>
            </tbody>
        </table>
      </div>
      <div class="col-xs-12 col-sm-8 col-md-8 pull-right">
        <table class="table">
            <thead>
                <tr>
                    <th>Prénom</th>
                    <th>Nom</th>
                    <th>Matricule</th>
                    <th>Retirer du Projet</th>
                </tr>
            </thead>
            <tbody>
                <#if members??>
                <#list members as member>
                    <tr>
                        <td>${member["firstName"]}</td>
                        <td>${member["lastName"]}</td>
                        <td>${member["registrationCGI"]}</td>
                        <td>
                            <form action="/admin/${projectId}/members/${member["id"]}/remove" method ="POST">
                            <input type="hidden" value="${projectName}"/>
                            <input type="submit" value="Retirer"/>
                            </form>
                        </td>
                    </tr>
                </#list>
                </#if>
            </tbody>
        </table>
      </div>
        </div>
      </div>
      </div>
 <footer>
    <img src="/img/basdepage.JPG">
  </footer>
</body>