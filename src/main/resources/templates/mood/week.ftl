<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF8">
  <title>Niko Niko</title>
      <link rel="stylesheet" href="/css/bootstrap.css">
      <link rel="stylesheet" href="/css/main1.css">
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
  <h1 align="center"> Semaine du ${debutsemaine} au ${finsemaine} / ${mois} / ${totbad}/  </h1>

  <div id="main">

<div class="container">
      <div class="row">
          <div class="col-xs-12 col-sm-2 col-md-2">
              <div class="semaine">
                <p>Lundi</p>
                <img src="/img/niko-vert-fond blanc.png" width="50%">
                <img src="/img/niko-jaune-fond blanc.png" width="50%">
                <img src="/img/niko-rouge-fond blanc.png" width="50%">
                </div>
           </div>
          <div class="col-xs-12 col-sm-2 col-md-2">
              <div class="semaine">
                <p>Mardi</p>
                <img src="/img/niko-vert-fond blanc.png" width="50%">
                <img src="/img/niko-jaune-fond blanc.png" width="50%">
                <img src="/img/niko-rouge-fond blanc.png" width="50%">
                </div>
           </div>

           <div class="col-xs-12 col-sm-2 col-md-2">
                <div class="semaine">
                <p>Mercredi</p>
                <img src="/img/niko-vert-fond blanc.png" width="50%">
                <img src="/img/niko-jaune-fond blanc.png" width="50%">
                <img src="/img/niko-rouge-fond blanc.png" width="50%">
                </div>
           </div>

            <div class="col-xs-12 col-sm-2 col-md-2">
                <div class="semaine">
                <p>Jeudi</p>
                <img src="/img/niko-vert-fond blanc.png" width="50%">
                <img src="/img/niko-jaune-fond blanc.png" width="50%">
                <img src="/img/niko-rouge-fond blanc.png" width="50%">
                </div>
           </div>

           <div class="col-xs-12 col-sm-2 col-md-2">
                <div class="semaine">
                <p>Vendredi</p>
                <img src="/img/niko-vert-fond blanc.png" width="50%">
                <img src="/img/niko-jaune-fond blanc.png" width="50%">
                <img src="/img/niko-rouge-fond blanc.png" width="50%">
                </div>
           </div>

           <div class="col-xs-12 col-sm-2 col-md-2">
                <div class="semaine">
                <p>Samedi/Dimanche</p>
                <img src="/img/niko-vert-fond blanc.png" width="50%">
                <img src="/img/niko-jaune-fond blanc.png" width="50%">
                <img src="/img/niko-rouge-fond blanc.png" width="50%">
                </div>
           </div>
        </div>
    </div>

      </div>
    </div>
  </div>
  <footer>
    <img src="/img/basdepage.jpg">
  </footer>
</body>
</html>