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
     <img src="/img/NikoSmile-logo.png" align="left" height="80px">
     <img src="/img/LogoCGI.png" align="right" height="80px">
    </header>
    <div align = right>
      <a href =/user/parameters><img src='/img/parametre.png' alt='exemple' width='5%'></a>
      <a href =/logout><img src='/img/logout.jpg' alt='exemple' width='5%'></a>
    </div>
  <h1 align="center"> ${projectName}  </h1>
  <h1 align="center"> Semaine du ${debutsemaine} / ${mois} au ${finsemaine} / ${mois1} / ${annee}  </h1>
      <#if leader == true>
             <form id= 'formid' action='/project/HiddenAnonymous' method='POST'>
                 <div align = center>
                      <input type='hidden' name='projectName' value='${projectName}' />
                      <input type='hidden' name='date' value='${date?c}'/>
                      <#if isHidden ==true>
                      <input type="checkbox" name="isHidden" value="${isHidden?c}" checked > projet caché
                      <#else>
                      <input type="checkbox" name="isHidden" value="${isHidden?c}" unchecked> projet caché
                      </#if>
                      <#if isAnonymous ==true>
                      <input type="checkbox" name="isAnonymous" value="${isAnonymous?c}" checked> projet anonyme
                      <#else>
                      <input type="checkbox" name="isAnonymous" value="${isAnonymous?c}" unchecked> projet anonyme
                      </#if>
                      <#include "../includable/security/securityToken.ftl">
                      <button type='submit' style='width:100px' value='submit'>Valider</button>
                  </div>
            </form>
      <#else>
      </#if>
<br/>
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
               <#if encourslundi == "ok">
                    <form id= 'formid' action='/mood/day/' method='get'>
                      <input type='hidden' name='projectName' value='${projectName}' />
                      <input type='hidden' name='date' value='${lundi?c}'/>
                      <button type='submit' value='submit'>Lundi</button>
                    </form>
                <#else>
                <form>
                <button style = "background-color:red ; font-weight:bold; color:white"type='button' value='submit'>Lundi</button>
                </form>
                </#if>
                <br/>
                <img src="/img/niko-vert.png" width="50%"> ${jour1satis2} <br/>
                <img src="/img/niko-jaune.png" width="50%"> ${jour1satis1} <br/>
                <img src="/img/niko-rouge.png" width="50%"> ${jour1satis0} <br/>
                </div>
           </div>
          <div class="col-xs-12 col-sm-2 col-md-2">
              <div class="semaine">
               <#if encoursmardi == "ok">
                <form id= 'formid' action='/mood/day/' method='get'>
                  <input type='hidden' name='projectName' value='${projectName}' />
                  <input type='hidden' name='date' value='${mardi?c}'/>
                  <button type='submit' value='submit'>Mardi</button>
                </form>
                <#else>
                <form>
                <button style = "background-color:red ; font-weight:bold; color:white"type='button' value='submit'>Mardi</button>
                </form>
                </#if>
                <br/>
                <img src="/img/niko-vert.png" width="50%"> ${jour2satis2} <br/>
                <img src="/img/niko-jaune.png" width="50%"> ${jour2satis1} <br/>
                <img src="/img/niko-rouge.png" width="50%"> ${jour2satis0} <br/>
                </div>
           </div>
           <div class="col-xs-12 col-sm-2 col-md-2">
                <div class="semaine">
                <#if encoursmercredi == "ok">
                <form id= 'formid' action='/mood/day/' method='get'>
                  <input type='hidden' name='projectName' value='${projectName}' />
                  <input type='hidden' name='date' value='${mercredi?c}'/>
                  <button type='submit' value='submit'>Mercredi</button>
                </form>
                <#else>
                <form>
                <button style = "background-color:red ; font-weight:bold; color:white"type='button' value='submit'>Mercredi</button>
                </form>
                </#if>
                <br/>
                <img src="/img/niko-vert.png" width="50%"> ${jour3satis2} <br/>
                <img src="/img/niko-jaune.png" width="50%"> ${jour3satis1} <br/>
                <img src="/img/niko-rouge.png" width="50%"> ${jour3satis0} <br/>
                </div>
           </div>
            <div class="col-xs-12 col-sm-2 col-md-2">
                <div class="semaine">
                 <#if encoursjeudi == "ok">
                <form id= 'formid' action='/mood/day/' method='get'>
                  <input type='hidden' name='projectName' value='${projectName}' />
                  <input type='hidden' name='date' value='${jeudi?c}'/>
                  <button type='submit' value='submit'>Jeudi</button>
                </form>
                <#else>
                <form>
                <button style = "background-color:red ; font-weight:bold; color:white"type='button' value='submit'>Jeudi</button>
                </form>
                </#if>
                <br/>
                <img src="/img/niko-vert.png" width="50%"> ${jour4satis2} <br/>
                <img src="/img/niko-jaune.png" width="50%"> ${jour4satis1} <br/>
                <img src="/img/niko-rouge.png" width="50%"> ${jour4satis0} <br/>
                </div>
           </div>
           <div class="col-xs-12 col-sm-2 col-md-2">
                <div class="semaine">
                 <#if encoursvendredi == "ok">
                <form id= 'formid' action='/mood/day/' method='get'>
                  <input type='hidden' name='projectName' value='${projectName}' />
                  <input type='hidden' name='date' value='${vendredi?c}'/>
                  <button type='submit' value='submit'>Vendredi</button>
                </form>
                <#else>
                <form>
                 <button style = "background-color:red ; font-weight:bold; color:white"type='button' value='submit'>Vendredi</button>
                </form>
                </#if>
                <br/>
                <img src="/img/niko-vert.png" width="50%"> ${jour5satis2} <br/>
                <img src="/img/niko-jaune.png" width="50%"> ${jour5satis1} <br/>
                <img src="/img/niko-rouge.png" width="50%"> ${jour5satis0} <br/>
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
        <br/>
        <form id= 'formid' action='/mood/month/' method='get'>
                  <input type='hidden' name='projectName' value='${projectName}' />
                  <input type='hidden' name='date' value='${date?c}'/>
                  <input type='hidden' name='changeMonth'/>
                  <div style="text-align: center">
                    <button type='submit' style='width:300px' value='submit'>Vue par mois</button>
                  </div>
        </form>
       <form id= 'formid' action='/user/resume/' method='get'>
                  <div style="text-align: center">
                    <button type='submit' style='width:300px' value='submit'>Retour au resumé</button>
                  </div>
        </form>
    </div>
 </div>

    <footer>
     <img src="/img/NikoSmile-logo.png" align="left" height="80px">
     <img src="/img/LogoCGI.png" align="right" height="80px">
    </footer>

</body>
</html>