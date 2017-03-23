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

  <h1 align="center"> Mois du ${debutmois} / ${mois} au ${finmois} / ${mois} / ${annee}  </h1>

  <div id="main">
     <div class="container">
      <div class="row">
        <#list days as int>
          <#if int?has_content>
            <#if int["joursem"]== 7 || int["joursem"]== 1 >
                <#if int["med"]?has_content>
                    <div class="col-xs-12 col-sm-2 col-md-2">
                        <div class="weekend" style="background-color:rgb(${int["red"]},${int["green"]},${int["blue"]})">
                            <p>${int["nomjour"]} ${int["jour"]}/${mois}</p>
                        </div>
                    </div>
                <#else>
                    <div class="col-xs-12 col-sm-2 col-md-2">
                        <div class="weekend">
                            <p>Pas de contenu</p>
                        </div>
                    </div>
                </#if>
                <#else>
                <#if int["med"]?has_content>
                    <div class="col-xs-12 col-sm-2 col-md-2">
                        <div class="calendjour" style="background-color:rgb(${int["red"]},${int["green"]},${int["blue"]})" >
                            <p>${int["nomjour"]} ${int["jour"]}/${mois}</p>
                        </div>
                    </div>
                <#else>
                    <div class="col-xs-12 col-sm-2 col-md-2">
                        <div class="weekend">
                            <p>Pas de contenu</p>
                        </div>
                    </div>
                </#if>
            </#if>
          </#if>
        </#list>
      </div>
    </div>
 </div>

    <footer>
        <img src="/img/basdepage.jpg">
    </footer>

</body>
</html>