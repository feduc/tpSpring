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

  <h1 align="center">Votes du ${jour} / ${mois} / ${annee} pour ${projectName}</h1>

  <div id="main">
     <div class="container">
      <div class="row">
        <#list dayInfos as info>
          <div class="col-xs-12 col-sm-12 col-md-12">
              <div class="jour">
              <div class="row">
                <div class="col-xs-3 col-sm-3 col-md-3" >
                    <#if info["satis"] == 1>
                        <img src='/img/niko-vert.png' alt='exemple' width='60px'>
                    <#elseif info["satis"] == 0>
                        <img src='/img/niko-jaune.png' alt='exemple' width='60px'>
                    <#elseif info["satis"] == -1>
                        <img src='/img/niko-rouge.png' alt='exemple' width='60px'>
                    <#else>
                        <img src='/img/niko-blanc.png' alt='exemple' width='60px'>
                    </#if>
                </div>
                <div class="col-xs-9 col-sm-9 col-md-9" id="comment">
                  <#if info["comment"]?has_content>
                    <font color="white">${info["comment"]} - </font>
                  </#if>
                    <i><font color="white">${info["FirstName"]} ${info["LastName"]}</font></i>
                </div>
              </div>
              </div>
           </div>
         </#list>
            <div "col-xs-12 col-sm-12 col-md-12">

                <form id= 'formid' action='/mood/week/change' method='get'>

                    <div>
                        <#include "../includable/security/securityToken.ftl">
                    </div>
                  <input type='hidden' name='projectName' value='${projectName}' />
                  <input type='hidden' name='date' value='${date?c}'/>
                  <input type='hidden' name='changeWeek'/>
                  <div style="text-align: center">
                    <button type='submit' style='width:300px' value='submit'>Vue par semaine</button>
                  </div>
                </form>

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