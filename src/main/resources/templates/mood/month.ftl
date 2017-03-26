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
              <div class="col-xs-12 col-sm-1 col-md-12">
              <h1 align="center">
                    <form action="/mood/month/change" method="GET">
                        <input type="hidden" name="id" value="${child["id"]}" />
                        <input type="hidden" name="agency" value="${child["agency"]}" />
                        <input type="hidden" name="firstName" value="${child["firstName"]}" />
                        <input type="hidden" name="lastName" value="${child["lastName"]}" />
                        <input type="hidden" name="registrationCGI" value="${child["registrationCGI"]}" />
                        <input type="hidden" name="verticale" value="${child["verticale"]}" />
                        <input type="hidden" name="date" value="${date?c}" />
                        <input type="hidden" name="projectName" value="${projectName}" />
                        <button id="previousMonth" type="submit" name="changeMonth" value="previous"></button>
                        </form> Mois du ${debutmois} / ${mois} au ${finmois} / ${mois} / ${annee}
                    <form action="/mood/month/change" method="GET">
                        <input type="hidden" name="id" value="${child["id"]}" />
                        <input type="hidden" name="agency" value="${child["agency"]}" />
                        <input type="hidden" name="firstName" value="${child["firstName"]}" />
                        <input type="hidden" name="lastName" value="${child["lastName"]}" />
                        <input type="hidden" name="registrationCGI" value="${child["registrationCGI"]}" />
                        <input type="hidden" name="verticale" value="${child["verticale"]}" />
                        <input type="hidden" name="date" value="${date?c}" />
                        <input type="hidden" name="projectName" value="${projectName}" />
                        <button id="nextMonth" type="submit" name="changeMonth" value="next"></button>
                        </form>
               </h1>
            </div>

  <div id="main">
     <div class="container">
      <div class="row">
        <#list days as int>
          <#if int?has_content>
            <#if int["joursem"]== 1 && int["jour"]== 1 >
                <#if int["med"]?has_content>
                   <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,0)"><p>Decalage</p></div> </div>
                   <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,0)"><p>Decalage</p></div></div>
                   <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,0)"><p>Decalage</p></div> </div>
                   <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,0)"><p>Decalage</p></div></div>
                   <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,0)"><p>Decalage</p></div> </div>
                   <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,0)"><p>Decalage</p></div></div>
                   <div class="col-xs-12 col-sm-2 col-md-2">
                        <div class="weekend" style="background-color:rgb(${int["red"]},${int["green"]},${int["blue"]})">
                            <p>${int["nomjour"]} ${int["jour"]}/${mois} /${int["debutsemaine"]}/ ${int["finsemaine"]}</p>
                        </div>
                    </div>
                <#else>
                    <div class="col-xs-12 col-sm-2 col-md-2">
                        <div class="weekend">
                            <p>Pas de contenu ${int["debutsemaine"]}/ ${int["finsemaine"]}</p>
                        </div>
                    </div>
                </#if>
            </#if>
            <#if int["joursem"]== 2 && int["jour"]== 1 >
                <#if int["med"]?has_content>
                    <div class="col-xs-12 col-sm-2 col-md-2">
                        <div class="calendjour" style="background-color:rgb(${int["red"]},${int["green"]},${int["blue"]})" >
                            <p>${int["nomjour"]} ${int["jour"]}/${mois} ${int["debutsemaine"]}/ ${int["finsemaine"]}</p>
                        </div>
                    </div>
                <#else>
                    <div class="col-xs-12 col-sm-2 col-md-2">
                        <div class="weekend">
                            <p>Pas de contenu ${int["debutsemaine"]}/ ${int["finsemaine"]}</p>
                        </div>
                    </div>
                </#if>
            </#if>
            <#if int["joursem"]== 3 && int["jour"]== 1 >
                <#if int["med"]?has_content>
                    <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,0)"><p>Decalage</p></div> </div>
                    <div class="col-xs-12 col-sm-2 col-md-2">
                        <div class="calendjour" style="background-color:rgb(${int["red"]},${int["green"]},${int["blue"]})" >
                            <p>${int["nomjour"]} ${int["jour"]}/${mois} ${int["debutsemaine"]}/ ${int["finsemaine"]}</p>
                        </div>
                    </div>
                <#else>
                    <div class="col-xs-12 col-sm-2 col-md-2">
                        <div class="weekend">
                            <p>Pas de contenu ${int["debutsemaine"]}/ ${int["finsemaine"]}</p>
                        </div>
                    </div>
                </#if>
            </#if>
            <#if int["joursem"]== 4 && int["jour"]== 1 >
                <#if int["med"]?has_content>
                    <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,0)"><p>Decalage</p></div> </div>
                    <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,0)"><p>Decalage</p></div> </div>
                    <div class="col-xs-12 col-sm-2 col-md-2">
                        <div class="calendjour" style="background-color:rgb(${int["red"]},${int["green"]},${int["blue"]})" >
                            <p>${int["nomjour"]} ${int["jour"]}/${mois} ${int["debutsemaine"]}/ ${int["finsemaine"]}</p>
                        </div>
                    </div>
                <#else>
                    <div class="col-xs-12 col-sm-2 col-md-2">
                        <div class="weekend">
                            <p>Pas de contenu ${int["debutsemaine"]}/ ${int["finsemaine"]}</p>
                        </div>
                    </div>
                 </#if>
             </#if>
             <#if int["joursem"]== 5 && int["jour"]== 1 >
                <#if int["med"]?has_content>
                    <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,0)"><p>Decalage</p></div> </div>
                    <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,0)"><p>Decalage</p></div> </div>
                    <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,0)"><p>Decalage</p></div> </div>
                    <div class="col-xs-12 col-sm-2 col-md-2">
                        <div class="calendjour" style="background-color:rgb(${int["red"]},${int["green"]},${int["blue"]})" >
                            <p>${int["nomjour"]} ${int["jour"]}/${mois} ${int["debutsemaine"]}/ ${int["finsemaine"]}</p>
                        </div>
                    </div>
                <#else>
                    <div class="col-xs-12 col-sm-2 col-md-2">
                        <div class="weekend">
                            <p>Pas de contenu ${int["debutsemaine"]}/ ${int["finsemaine"]}</p>
                        </div>
                    </div>
                </#if>
            </#if>
            <#if int["joursem"]== 6 && int["jour"]== 1 >
                <#if int["med"]?has_content>
                    <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,0)"><p>Decalage</p></div> </div>
                    <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,0)"><p>Decalage</p></div> </div>
                    <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,0)"><p>Decalage</p></div> </div>
                    <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,0)"><p>Decalage</p></div> </div>
                    <div class="col-xs-12 col-sm-2 col-md-2">
                        <div class="calendjour" style="background-color:rgb(${int["red"]},${int["green"]},${int["blue"]})" >
                            <p>${int["nomjour"]} ${int["jour"]}/${mois} ${int["debutsemaine"]}/ ${int["finsemaine"]}</p>
                        </div>
                    </div>
                <#else>
                    <div class="col-xs-12 col-sm-2 col-md-2">
                        <div class="weekend">
                            <p>Pas de contenu ${int["debutsemaine"]}/ ${int["finsemaine"]}</p>
                        </div>
                    </div>
                 </#if>
             </#if>
             <#if int["joursem"]== 7 && int["jour"]== 1 >
                <#if int["med"]?has_content>
                    <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,0)"><p>Decalage</p></div> </div>
                    <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,0)"><p>Decalage</p></div> </div>
                    <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,0)"><p>Decalage</p></div> </div>
                    <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,0)"><p>Decalage</p></div> </div>
                    <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,0)"><p>Decalage</p></div> </div>
                    <div class="col-xs-12 col-sm-2 col-md-2">
                        <div class="calendjour" style="background-color:rgb(${int["red"]},${int["green"]},${int["blue"]})" >
                            <p>${int["nomjour"]} ${int["jour"]}/${mois} ${int["debutsemaine"]}/ ${int["finsemaine"]}</p>
                        </div>
                    </div>
                <#else>
                    <div class="col-xs-12 col-sm-2 col-md-2">
                        <div class="weekend">
                            <p>Pas de contenu ${int["debutsemaine"]}/ ${int["finsemaine"]}</p>
                        </div>
                    </div>
               </#if>
             </#if>
            <#else>
                <#if int["med"]?has_content>
                    <div class="col-xs-12 col-sm-2 col-md-2">
                        <div class="calendjour" style="background-color:rgb(${int["red"]},${int["green"]},${int["blue"]})" >
                            <p>${int["nomjour"]} ${int["jour"]}/${mois} ${int["debutsemaine"]}/ ${int["finsemaine"]}</p>
                        </div>
                    </div>
                <#else>
                    <div class="col-xs-12 col-sm-2 col-md-2">
                        <div class="weekend">
                            <p>Pas de contenu ${int["debutsemaine"]}/ ${int["finsemaine"]}</p>
                        </div>
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