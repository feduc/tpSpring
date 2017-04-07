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
     <img src="/img/NikoSmile-logo.png" align="left" height="80px">
     <img src="/img/LogoCGI.png" align="right" height="80px">
    </header>
    <div align = right>
      <a href =/user/parameters><img src='/img/parametre.png' alt='exemple' width='5%'></a>
      <a href =/logout><img src='/img/logout.jpg' alt='exemple' width='5%'></a>
    </div>
    <h1 align='center'> Selection de projet</h1>

    <div id='main'>
    <div class="container">
      <div class="row">
      <div class="col-xs-12 col-sm-8 col-md-12">
         <div class="changeavis">
            <font color = "white"><h1> Choisissez un projet </h1> </font>
                <form action="/project/choose" method="GET" >
                    <font color = "white"> <th>Nom du projet :</th></br></font>
                    <input type="text" name="projectName" placeholder="entrez le nom du projet" value="${projectName}"/>
                    <input type='hidden' name='date' value='${date?c}'/>
                    </br>

                <button type="submit" value="submit">Chercher</button>
            </form>
            <form id= 'formid' action='/user/resume/' method='get'>
                  <div style="text-align: center">
                    <button type='submit' style='width:200px' value='submit'>Retour au resumé</button>
                  </div>
       </form>
             </div>
        </div>
      <div class="col-xs-12 col-sm-8 col-md-12">
      <div align="center">
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
                    <#if visu == true>
                    <tr>
                        <td>
                            <form action='/mood/week/change' method ="GET">
                            <input type='hidden' name='date' value='${date?c}'/>
                            <input type='hidden' name='changeWeek'/>
                            <input type="submit" name = "projectName" value="${project["name"]}"/>
                            </form>
                        </td>
                        <td>${project["startDate"]}</td>
                        <#if project["endDate"]??>
                            <td>${project["endDate"]}</td>
                        </#if>
                    </tr>
                    <#else>
                    <#if project["isHidden"] = false>
                      <tr>
                        <td>
                            <form action='/mood/week/change' method ="GET">
                            <input type='hidden' name='date' value='${date?c}'/>
                            <input type='hidden' name='changeWeek'/>
                            <input type="submit" name = "projectName" value="${project["name"]}"/>
                            </form>
                        </td>
                        <td>${project["startDate"]}</td>
                        <#if project["endDate"]??>
                            <td>${project["endDate"]}</td>
                        </#if>
                    </tr>
                    </#if>
                </#if>
                </#list>
                </#if>
            </tbody>
        </table>
        </div>
      </div>
      </div>
    </div>
    <footer class="bottom">
     <img src="/img/NikoSmile-logo.png" align="left" height="80px">
     <img src="/img/LogoCGI.png" align="right" height="80px">
    </footer>
  </body>
</html>