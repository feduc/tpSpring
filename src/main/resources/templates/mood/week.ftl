<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF8">
  <title>Niko Niko</title>
      <link rel="stylesheet" href="/css/bootstrap.css">
      <link rel="stylesheet" href="/css/main1.css">
</head>

<body>
    <header>
        <img src="/img/BaniereCGI.jpg">
    </header>
    <div align = right><a href ="page de vote.html"><img src="/img/parametre.png" alt="exemple" width="5%"></a>
                    <a href =/security/login/><img src="/img/logout.jpg" alt="exemple" width="5%"></a></div>

  <h1 align="center"> Semaine du ${debutsemaine} / ${mois} au ${finsemaine} / ${mois1} / ${annee}  </h1>

  <div id="main">
     <div class="container">
      <div class="row">

          <div class="col-xs-12 col-sm-1 col-md-1">
                <form action="/mood/week/change" method="GET">
                    <input type="hidden" name="date" value="${date?c}" />
                    <input type="hidden" name="projectName" value="${projectName}" />

                        <button id="previousWeek" type="submit" name="changeWeek" value="previous"></button>

                </form>
          </div>
          <div class="col-xs-12 col-sm-2 col-md-2">
              <div class="semaine">
                <form id= 'formid' action='/mood/day/' method='get'>
                  <input type='hidden' name='projectName' value='${projectName}' />
                  <input type='hidden' name='date' value='${lundi?c}'/>
                  <button type='submit' value='submit'>Lundi</button>
                </form>
                </br>
                <img src="/img/niko-vert-fond blanc.png" width="50%"> ${jour1satis2} <br/>
                <img src="/img/niko-jaune-fond blanc.png" width="50%"> ${jour1satis1} <br/>
                <img src="/img/niko-rouge-fond blanc.png" width="50%"> ${jour1satis0} <br/>
                </div>
           </div>
          <div class="col-xs-12 col-sm-2 col-md-2">
              <div class="semaine">
                <form id= 'formid' action='/mood/day/' method='get'>
                  <input type='hidden' name='projectName' value='${projectName}' />
                  <input type='hidden' name='date' value='${mardi?c}'/>
                  <button type='submit' value='submit'>Mardi</button>
                </form>
                </br>
                <img src="/img/niko-vert-fond blanc.png" width="50%"> ${jour2satis2} <br/>
                <img src="/img/niko-jaune-fond blanc.png" width="50%"> ${jour2satis1} <br/>
                <img src="/img/niko-rouge-fond blanc.png" width="50%"> ${jour2satis0} <br/>
                </div>
           </div>
           <div class="col-xs-12 col-sm-2 col-md-2">
                <div class="semaine">
                <form id= 'formid' action='/mood/day/' method='get'>
                  <input type='hidden' name='projectName' value='${projectName}' />
                  <input type='hidden' name='date' value='${mercredi?c}'/>
                  <button type='submit' value='submit'>Mercredi</button>
                </form>
                </br>
                <img src="/img/niko-vert-fond blanc.png" width="50%"> ${jour3satis2} <br/>
                <img src="/img/niko-jaune-fond blanc.png" width="50%"> ${jour3satis1} <br/>
                <img src="/img/niko-rouge-fond blanc.png" width="50%"> ${jour3satis0} <br/>
                </div>
           </div>
            <div class="col-xs-12 col-sm-2 col-md-2">
                <div class="semaine">
                <form id= 'formid' action='/mood/day/' method='get'>
                  <input type='hidden' name='projectName' value='${projectName}' />
                  <input type='hidden' name='date' value='${jeudi?c}'/>
                  <button type='submit' value='submit'>Jeudi</button>
                </form>
                </br>
                <img src="/img/niko-vert-fond blanc.png" width="50%"> ${jour4satis2} <br/>
                <img src="/img/niko-jaune-fond blanc.png" width="50%"> ${jour4satis1} <br/>
                <img src="/img/niko-rouge-fond blanc.png" width="50%"> ${jour4satis0} <br/>
                </div>
           </div>
           <div class="col-xs-12 col-sm-2 col-md-2">
                <div class="semaine">
                <form id= 'formid' action='/mood/day/' method='get'>
                  <input type='hidden' name='projectName' value='${projectName}' />
                  <input type='hidden' name='date' value='${vendredi?c}'/>
                  <button type='submit' value='submit'>Vendredi</button>
                </form>
                </br>
                <img src="/img/niko-vert-fond blanc.png" width="50%"> ${jour5satis2} <br/>
                <img src="/img/niko-jaune-fond blanc.png" width="50%"> ${jour5satis1} <br/>
                <img src="/img/niko-rouge-fond blanc.png" width="50%"> ${jour5satis0} <br/>
                </div>
           </div>

           <div class="col-xs-12 col-sm-1 col-md-1">
                <form action="/mood/week/change" method="GET">
                    <input type="hidden" name="date" value="${date?c}" />
                    <input type="hidden" name="projectName" value="${projectName}" />

                        <button id="nextWeek" type="submit" name="changeWeek" value="next"></button>

                </form>

           </div>
        </div>
        </br>
        <form id= 'formid' action='/mood/month/' method='get'>
                  <input type='hidden' name='projectName' value='${projectName}' />
                  <input type='hidden' name='date' value='${date?c}'/>
                  <input type='hidden' name='changeMonth'/>
                  <div style="text-align: center">
                    <button type='submit' style='width:300px' value='submit'>Vue par mois</button>
                  </div>
        </form>
    </div>
 </div>

    <footer>
        <img src="/img/basdepage.jpg">
    </footer>

</body>
</html>