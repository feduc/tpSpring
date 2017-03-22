<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF8">
  <title>Niko Niko</title>
      <link rel="stylesheet" href="/css/bootstrap.css">
      <link rel="stylesheet" href="/css/main1.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Task', 'Avis'],
          ['Content',     60],
          ['Normal',      20],
          ['Mecontent',  20],
        ]);
        var options = {
          title: 'Nikos du jour',
          pieHole: 0.4,
          colors:['green','yellow','red'],
          backgroundColor : '#a00000'
        };
        var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
        chart.draw(data, options);
      }
    </script>
    <script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Task', 'Avis'],
          ['Content',     60],
          ['Normal',      20],
          ['Mecontent',  20],
        ]);
        var options = {
          title: 'Nikos du jour',
          pieHole: 0.4,
          colors:['green','yellow','red'],
          backgroundColor : '#a00000'
        };
        var chart = new google.visualization.PieChart(document.getElementById('donutchart2'));
        chart.draw(data, options);
      }
    </script>
    <script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Task', 'Avis'],
          ['Content',     50],
          ['Normal',      20],
          ['Mecontent',  20],
        ]);
        var options = {
          title: 'Nikos du jour',
          pieHole: 0.4,
          colors:['green','yellow','red'],
          backgroundColor : '#a00000'
        };
        var chart = new google.visualization.PieChart(document.getElementById('donutchart3'));
        chart.draw(data, options);
      }
    </script>
  </head>
<body>
    <header>
     <img src="/img/BaniereCGI.jpg">
    </header>
<div align = right><a href ="page de vote.html"><img src="/img/parametre.png" alt="exemple" width="5%"></a>
                    <a href ="page_accueil.html"><img src="/img/logout.jpg" alt="exemple" width="5%"></a></div>
  <h1 align="center"> Mes Projets en Cours</h1>

  <div id="main">

        <div class="container" >
            <div class="row" >
                <div  id = "actualProjects">
                
                </div>
                <div class="col-xs-12 col-sm-2 col-md-4">
                    <div class="changeavis">
                        <p> <FONT color="white">Mon humeur du jour <FONT color="white"></p>

                        <#if smile == 1>
                        <img src="/img/niko-vert.png" alt="exemple" width="24%">
                        <#elseif smile == 0>
                        <img src="/img/niko-jaune.png" alt="exemple" width="24%">
                        <#elseif smile== -1>
                        <img src="/img/niko-rouge.png" alt="exemple" width="24%">
                        <#else>
                        <img src="/img/niko-vert.png" alt="exemple" width="24%">
                        </#if>

                        </br>
                        <form id= "formid" action="/mood/vote/" method="get">
                        <input type="hidden" name="id" value="${child["id"]}" />
                        <input type="hidden" name="agency" value="${child["agency"]}" />
                        <input type="hidden" name="firstName" value="${child["firstName"]}" />
                        <input type="hidden" name="lastName" value="${child["lastName"]}" />
                        <input type="hidden" name="registrationCGI" value="${child["registrationCGI"]}" />
                        <input type="hidden" name="verticale" value="${child["verticale"]}" />
                        <font color="black"><input type="submit" color = "black" name="Repondre "style="width:200px"value="Changer d'avis"/>
                        </font>
                        </form>
                    </div>
                </div>

                <div class="col-xs-12 col-sm-2 col-md-8">
                    <div class="changeavis">
                        <p> <FONT color="white">Mes anciens projets <FONT color="white"></p>
                        <a href="vue_mois_projet.html">Projet1</a>
                        </br>
                        <a href="vue_mois_projet.html">Projet2</a>
                        </br>
                        <a href="vue_mois_projet.html">Projet3</a>
                        </br>
                        </br>
                        <a href="page de vote.html"><input type="button" value="Vers la selection de projet"/></a>
                    </div>
                </div>
            </div>
        </main>
      </div>
    </div>
</div>

  <footer>
    <img src="/img/basdepage.jpg">
  </footer>
  
  <script src="/js/projectsViewingOnResume.js"></script>

    <script>
      $(document).ready(function(){
        <#list actualProjectsNames as name>
        addProject(${name});
        
        
        </#list>
      });

      $(document).unload(function(){
        
      });
    </script>
    
</body>
</html>