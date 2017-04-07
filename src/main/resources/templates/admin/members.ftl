<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
      <link rel="stylesheet" href="/css/bootstrap.css">
      <link rel="stylesheet" href="/css/main1.css">
</head>
<body>
    <header>
     <img src="/img/NikoSmile-logo.png" align="left" height="80px">
     <img src="/img/LogoCGI.png" align="right" height="80px">
    </header>
    <div align = right>
      <a href =/user/parameters><img src='/img/parametre.png' alt='exemple' width='5%'></a>
      <a href =/logout><img src='/img/logout.jpg' alt='exemple' width='5%'></a>
    </div>
  <#if admin==true>
  <h1 align="center"> Page d'administration</h1>
  <#else>
  <h1 align="center"> Page de modération</h1>
  </#if>

  <div id="main">

<div class="container">
      <div class="row">
      <#include "../includable/adminNav.ftl">
      <div class="col-xs-12 col-sm-8 col-md-8">
         <div class="changeavis">
            <font color = "white"><h1> Choisissez un utilisateur </h1> </font>
                <form action="/admin/${projectId}/members" method="GET" >
                    <font color = "white"> <th>Matricule de l'utilisateur :</th><br/></font>
                    <input type="hidden" name="projectName" value="${projectName}"/>
                    <input type="hidden" name="leaderId" value="${leaderId}"/>
                    <input type="text" name="userRegistration" placeholder="entrez le matricule de l'utilisateur" value="${userRegistration}"/>

               <br/>
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
                            <input type="hidden" name="projectName" value="${projectName}"/>
                            <#include "../includable/security/securityToken.ftl">
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
      <form action="/admin/${projectId}/members/project-leader-update" method ="POST">
        <table class="table">
            <thead>
                <tr>
                    <th>Prénom</th>
                    <th>Nom</th>
                    <th>Matricule</th>
                    <th>Retirer du Projet</th>
                    <th>Chef de projet</th>
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
                            <input type="hidden" name="projectName" value="${projectName}"/>
                            <#include "../includable/security/securityToken.ftl">
                            <input type="submit" value="Retirer"/>
                            </form>
                        </td>
                        <td>
                            <#if leaderId == member["id"]>
                            <input type="radio" name="projectLeader" value="${member["registrationCGI"]}" checked="checked"/>
                            <#else>
                            <input type="radio" name="projectLeader" value="${member["registrationCGI"]}"/>
                            </#if>
                            <#include "../includable/security/securityToken.ftl">
                        </td>
                    </tr>
                </#list>
                </#if>
            </tbody>
        </table>
        <input type="hidden" name="projectName1" value="${projectName}"/>
        <input type="submit" value="Selectionner un Chef de Projet"/>
      </form>
      </div>
        </div>
      </div>
      </div>
 <footer>
     <img src="/img/NikoSmile-logo.png" align="left" height="80px">
     <img src="/img/LogoCGI.png" align="right" height="80px">
  </footer>
</body>