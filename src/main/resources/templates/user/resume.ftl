<!DOCTYPE html>
<html>
  <head>
    <meta charset='UTF8'>
    <title>Niko Niko</title>
    <link rel='stylesheet' href='/css/bootstrap.css'>
    <link rel='stylesheet' href='/css/main1.css'>
    <script src='http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js'></script>
    <script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>
    <#list actualProjectsInfo as project>
      <script type='text/javascript'>
        google.charts.load('current', {packages:['corechart']});
        google.charts.setOnLoadCallback(drawChart);
        function drawChart() {
          var data = google.visualization.arrayToDataTable([
            ['Task', 'Avis']
            <#list project?keys as key>
                <#if key != "name">
                    ,['${key}', ${project[key]}]
                </#if>
            </#list>
          ]);
          var options = {
            title: 'Nikos du jour',
            pieHole: 0.4,
            colors:['red','yellow','green'],
            backgroundColor : '#a00000'
          };
          var chart = new google.visualization.PieChart(document.getElementById('donutchart${project["name"]}'));
          chart.draw(data, options);
        }
      </script>
    </#list>
  </head>
  <body>
    <header>
      <img src='/img/BaniereCGI.jpg'>
    </header>
    <div align = right>
      <a href ='page de vote.html'><img src='/img/parametre.png' alt='exemple' width='5%'></a>
      <a href =/logout><img src='/img/logout.jpg' alt='exemple' width='5%'></a>
    </div>
    <h1 align='center'> Mes Projets en Cours</h1>

    <div id='main'>

      <div class='container' >
        <div class='row' >
          <#list actualProjectsNames as projectName>
            <div class='col-xs-12 col-sm-6 col-md-6'>
              <div class='projet'>
                <font color="white"><p> ${projectName}</p></font>
                <div style="align:center" id='donutchart${projectName}' style='width: 300px; height: 200px;' ></div>
                <form id= 'formid' action='/mood/week/change' method='get'>
                  <input type='hidden' name='projectName' value='${projectName}' />
                  <input type='hidden' name='date' value='${date?c}'/>
                  <input type='hidden' name='changeWeek'/>
                  <button type='submit' style='width:300px' value='submit'>Voir</button>
                      <div>
                            <#include "../includable/security/securityToken.ftl">
                      </div>
                </form>
              </div>
            </div>
          </#list>
        </div>
        <div class='row' >
          <div class='col-xs-12 col-sm-4 col-md-4'>
            <div class='changeavis'>
              <p> <font color='white'>Mon humeur du jour </font></p>

              <#if smile == 1>
                <img src='/img/niko-vert.png' alt='exemple' width='24%'>
              <#elseif smile == 0>
                <img src='/img/niko-jaune.png' alt='exemple' width='24%'>
              <#elseif smile == -1>
                <img src='/img/niko-rouge.png' alt='exemple' width='24%'>
              <#else>
                <img src='/img/niko-blanc.png' alt='exemple' width='24%'>
              </#if>

              </br>
              <form id= 'formid' action='/mood/vote/' method='get'>
                    <div>
                        <#include "../includable/security/securityToken.ftl">
                    </div>
                <font color='black'><input type='submit' color = 'black' name='Repondre 'style='width:200px'value="Changer d'avis"/>
                </font>
              </form>
            </div>
          </div>

          <div class='col-xs-12 col-sm-8 col-md-8'>
            <div class='changeavis'>
              <p> <FONT color='white'>Mes anciens projets </FONT></p>
              <#list oldProjectsNames as projectName>
                    <p> <FONT color='white'>${projectName}</font></p>
                    <form id= 'formid' action='/mood/week/change' method='get'>
                        <input type='hidden' name='date' value='${date?c}'/>
                        <input type='hidden' name='projectName' value='${projectName}' />
                        <input type='hidden' name='changeWeek'/>
                        <button type='submit' style='width:300px' value='submit'>Voir</button>
                            <div>
                                <#include "../includable/security/securityToken.ftl">
                            </div>
                    </form>
                    </br>
              </#list>
              <form action='/project/choose' method='get'>
                    <div>
                        <#include "../includable/security/securityToken.ftl">
                    </div>
                <button type='submit' style='width:300px' value='submit'>Vers la selection de projet</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <footer>
      <img src='/img/basdepage.jpg'>
    </footer>

    <script src='/js/projectsViewingOnResume.js'></script>

    <script>
    $(document).ready(function(){

    });

    $(document).unload(function(){

    });
    </script>

  </body>
</html>