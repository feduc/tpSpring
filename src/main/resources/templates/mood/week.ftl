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
                    <a href ="page_accueil.html"><img src="/img/logout.jpg" alt="exemple" width="5%"></a></div>

  <h1 align="center"> Semaine du ${debutsemaine} / ${mois} au ${finsemaine} / ${mois1} / ${annee}  </h1>

  <div id="main">
     <div class="container">
      <div class="row">
      
          <div class="col-xs-12 col-sm-1 col-md-1">
                <form action="/mood/week/change" method="GET">
                    <input type="hidden" name="id" value="${child["id"]}" />
                    <input type="hidden" name="agency" value="${child["agency"]}" />
                    <input type="hidden" name="firstName" value="${child["firstName"]}" />
                    <input type="hidden" name="lastName" value="${child["lastName"]}" />
                    <input type="hidden" name="registrationCGI" value="${child["registrationCGI"]}" />
                    <input type="hidden" name="verticale" value="${child["verticale"]}" />
                    <input type="hidden" name="date" value="${date?c}" />
                    <input type="hidden" name="projectName" value="${projectName}" />
            
                    <div class="button">
                        <button type="submit" name="changeWeek" value="previous">Previous</button>
                    </div>

                </form>

           </div>
          <div class="col-xs-12 col-sm-2 col-md-2">
              <div class="semaine">
                <p>Lundi</p>
                <img src="/img/niko-vert-fond blanc.png" width="50%"> ${jour1satis2} <br/>
                <img src="/img/niko-jaune-fond blanc.png" width="50%"> ${jour1satis1} <br/>
                <img src="/img/niko-rouge-fond blanc.png" width="50%"> ${jour1satis0} <br/>
                </div>
           </div>
          <div class="col-xs-12 col-sm-2 col-md-2">
              <div class="semaine">
                <p>Mardi</p>
                <img src="/img/niko-vert-fond blanc.png" width="50%"> ${jour2satis2} <br/>
                <img src="/img/niko-jaune-fond blanc.png" width="50%"> ${jour2satis1} <br/>
                <img src="/img/niko-rouge-fond blanc.png" width="50%"> ${jour2satis0} <br/>
                </div>
           </div>
           <div class="col-xs-12 col-sm-2 col-md-2">
                <div class="semaine">
                <p>Mercredi</p>
                <img src="/img/niko-vert-fond blanc.png" width="50%"> ${jour3satis2} <br/>
                <img src="/img/niko-jaune-fond blanc.png" width="50%"> ${jour3satis1} <br/>
                <img src="/img/niko-rouge-fond blanc.png" width="50%"> ${jour3satis0} <br/>
                </div>
           </div>
            <div class="col-xs-12 col-sm-2 col-md-2">
                <div class="semaine">
                <p>Jeudi</p>
                <img src="/img/niko-vert-fond blanc.png" width="50%"> ${jour4satis2} <br/>
                <img src="/img/niko-jaune-fond blanc.png" width="50%"> ${jour4satis1} <br/>
                <img src="/img/niko-rouge-fond blanc.png" width="50%"> ${jour4satis0} <br/>
                </div>
           </div>
           <div class="col-xs-12 col-sm-2 col-md-2">
                <div class="semaine">
                <p>Vendredi</p>
                <img src="/img/niko-vert-fond blanc.png" width="50%"> ${jour5satis2} <br/>
                <img src="/img/niko-jaune-fond blanc.png" width="50%"> ${jour5satis1} <br/>
                <img src="/img/niko-rouge-fond blanc.png" width="50%"> ${jour5satis0} <br/>
                </div>
           </div>

           <div class="col-xs-12 col-sm-1 col-md-1">
                <form action="/mood/week/change" method="GET">
                    <input type="hidden" name="id" value="${child["id"]}" />
                    <input type="hidden" name="agency" value="${child["agency"]}" />
                    <input type="hidden" name="firstName" value="${child["firstName"]}" />
                    <input type="hidden" name="lastName" value="${child["lastName"]}" />
                    <input type="hidden" name="registrationCGI" value="${child["registrationCGI"]}" />
                    <input type="hidden" name="verticale" value="${child["verticale"]}" />
                    <input type="hidden" name="date" value="${date?c}" />
                    <input type="hidden" name="projectName" value="${projectName}" />
                    
                    <div class="button">
                        <button type="submit" name="changeWeek" value="next">Next</button>
                    </div>

                </form>

           </div>

        </div>
    </div>
 </div>

    <footer>
        <img src="/img/basdepage.jpg">
    </footer>

</body>
</html>