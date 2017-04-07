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
            <font color = "white"><h1> Choisissez un projet </h1> </font>
                <form action="/admin/choose" method="GET" >
                    <font color = "white"> <th>Nom du projet :</th><br/></font>
                    <input type="hidden" name = "action" value ="${action}"/>
                    <input type="text" name="projectName" placeholder="entrez le nom du projet" value="${projectName}"/>

               <br/>
                <button type="submit" value="submit">Chercher</button>
            </form>
             </div>
        </div>
      <div class="col-xs-12 col-sm-8 col-md-8">
        <table class="table">
            <thead>
                <tr>
                    <th>Nom du projet</th>
                    <th>Date de début</th>
                    <th>Date de fin</th>
                </tr>
            </thead>
            <tbody>
                <#if projects??>
                <#list projects as project>
                    <tr>
                        <td>
                           <#if action == "members">
                            <form action="/admin/${project["id"]}/members" method ="GET">
                                <input type="submit" name = "projectName" value="${project["name"]}"/>
                                <#if project["projectLeader"]??>
                                    <input type="hidden" name = "leaderId" value="${(project["projectLeader"])["id"]}"/>
                                </#if>
                            </form>
                           <#elseif action == "update">
                             <form action="/project/${project["id"]}/update" method ="GET">
                                <input type="submit" name = "projectName" value="${project["name"]}"/>
                             </form>
                           </#if>
                         </td>
                        <td>${project["startDate"]}</td>
                        <#if project["endDate"]??>
                            <td>${project["endDate"]}</td>
                        <#else>
                            <td></td>
                        </#if>
                    </tr>
                </#list>
                </#if>
            </tbody>
        </table>
      </div>
      </div>
      </div>
 <footer>
     <img src="/img/NikoSmile-logo.png" align="left" height="80px">
     <img src="/img/LogoCGI.png" align="right" height="80px">
  </footer>
</body>