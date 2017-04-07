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
                <form action="/admin/chooseUser" method="GET" >
                    <font color = "white"> <th>Matricule de l'utilisateur :</th><br/></font>
                    <input type="hidden" name = "action" value="${action}"/>
                    <input type="text" name="userRegistration" placeholder="entrez le matricule de l'utilisateur" value="${userRegistration}"/>
               <br/>
                <button type="submit" value="submit">Chercher</button>
            </form>
             </div>
        </div>
        <div class="col-xs-12 col-sm-8 col-md-8">
        <table class="table">
            <thead>
                <tr>
                    <th>Matricule</th>
                    <th>Prénom</th>
                    <th>Nom</th>
                </tr>
            </thead>
            <tbody>
                <#if users??>
                <#list users as user>
                    <tr>
                        <td>
                            <#if action == "roles">
                                <form action="/admin/${user["id"]}/roles" method ="GET">
                            <#elseif action == "update">
                                <form action="/user/${user["id"]}/update" method ="GET">
                            </#if>
                            <input type="submit" name = "userRegistration" value="${user["registrationCGI"]}"/>
                            </form>
                        </td>
                        <td>${user["firstName"]}</td>
                        <td>${user["lastName"]}</td>
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
     <img src="/img/NikoSmile-logo.png" align="left" height="80px">
     <img src="/img/LogoCGI.png" align="right" height="80px">
  </footer>
</body>