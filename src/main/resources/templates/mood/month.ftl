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
    <div align = right>
      <a href =/user/parameters><img src='/img/parametre.png' alt='exemple' width='5%'></a>
      <a href =/logout><img src='/img/logout.jpg' alt='exemple' width='5%'></a>
    </div>

      <div class="row">
          <div class="col-xs-12 col-sm-1 col-md-4">
                    <form action="/mood/month/change" method="GET">
                      <input type="hidden" name="date" value="${date?c}" />
                        <input type="hidden" name="projectName" value="${projectName}" />
                            <button id="previousMonth" type="submit" name="changeMonth" value="previous"></button>
                    </form>
           </div>
          <div class="col-xs-12 col-sm-1 col-md-4">
                      <h1 align="center">  Mois du ${debutmois} / ${mois} au ${finmois} / ${mois} / ${annee}</h1>
          </div>
          <div class="col-xs-12 col-sm-1 col-md-4">
                    <form action="/mood/month/change" method="GET">
                        <input type="hidden" name="date" value="${date?c}" />
                        <input type="hidden" name="projectName" value="${projectName}" />
                        <button id="nextMonth" type="submit" name="changeMonth" value="next"></button>
                    </form>
            </div>
        </div>


  <div id="main">
     <div class="container">
      <div class="row">
        <#list days as int>
          <#if int?has_content>
            <#if int["joursem"]== 1 && int["jour"]== 1 >
                <#if int["med"]?has_content>
                    <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"></div> </div>
                    <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"></div> </div>
                    <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"></div> </div>
                    <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"></div> </div>
                    <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"></div> </div>
                    <div class="col-xs-12 col-sm-2 col-md-1"><div class="weekend" style="background-color:rgb(255,255,255)"></div> </div>
                    <div class="col-xs-12 col-sm-2 col-md-1">
                        <div class="weekend" style="background-color:rgb(192,192,192)">
                                <form id= 'formid' action='/mood/week/' method='get'>
                                    <input type='hidden' name='projectName' value='${projectName}' />
                                    <input type="hidden" name="date" value="${int['date']?c}" />
                                    <button type='submit' value='submit'>WE</button>
                                </form>
                        </div>
                    </div>
                    <#else>
                    <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"></div> </div>
                    <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"></div> </div>
                    <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"></div> </div>
                    <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"></div> </div>
                    <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"></div> </div>
                    <div class="col-xs-12 col-sm-2 col-md-1"><div class="weekend" style="background-color:rgb(255,255,255)"></div> </div>
                    <div class="col-xs-12 col-sm-2 col-md-1">
                        <div class="weekend" style="background-color:rgb(192,192,192)">
                                <form id= 'formid' action='/mood/week/' method='get'>
                                    <input type='hidden' name='projectName' value='${projectName}' />
                                    <input type="hidden" name="date" value="${int['date']?c}" />
                                    <button type='submit' value='submit'>WE</button>
                                </form>
                        </div>
                    </div>
                </#if>

                <#elseif int["joursem"]== 2 && int["jour"]== 1 >
                    <#if int["encours"]== "ok">
                        <#if int["med"]?has_content>
                            <div class="col-xs-12 col-sm-2 col-md-2">
                                <div class="weekend" style="background-color:rgb(${int["red"]},${int["green"]},${int["blue"]})">
                                    <form id= 'formid' action='/mood/week/' method='get'>>
                                        <input type='hidden' name='projectName' value='${projectName}' />
                                        <input type="hidden" name="date" value="${int['date']?c}" />
                                        <button type='submit' value='submit'>${int["nomjour"]} ${int["jour"]}/${mois}</button>
                                    </form>
                                </div>
                            </div>
                            <#else>
                            <div class="col-xs-12 col-sm-2 col-md-2">
                                <div class="weekend">
                                    <form id= 'formid' action='/mood/week/' method='get'>
                                        <input type='hidden' name='projectName' value='${projectName}' />
                                        <input type="hidden" name="date" value="${int['date']?c}" />
                                        <button type='submit' value='submit'>Pas de contenu</button>
                                    </form>
                                </div>
                            </div>
                        </#if>
                        <#else>
                        <div class="col-xs-12 col-sm-2 col-md-2">
                            <div class="weekend">
                                <form id= 'formid' action='/mood/week/' method='get'>
                                    <input type='hidden' name='projectName' value='${projectName}' />
                                    <input type="hidden" name="date" value="${int['date']?c}" />
                                    <button type='submit' value='submit'>Pas de contenu</button>
                                </form>
                            </div>
                        </div>
                    </#if>

                <#elseif int["joursem"]== 3 && int["jour"]== 1 >
                    <#if int["encours"]== "ok">
                        <#if int["med"]?has_content>
                            <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)" style ="color:white" ></div> </div>
                                <div class="col-xs-12 col-sm-2 col-md-2">
                                    <div class="weekend" style="background-color:rgb(${int["red"]},${int["green"]},${int["blue"]})">
                                        <form id= 'formid' action='/mood/week/' method='get'>
                                            <input type='hidden' name='projectName' value='${projectName}' />
                                            <input type="hidden" name="date" value="${int['date']?c}" />
                                            <button type='submit' value='submit'>${int["nomjour"]} ${int["jour"]}/${mois}</button>
                                        </form>
                                    </div>
                                </div>

                            <#else>
                            <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                            <div class="col-xs-12 col-sm-2 col-md-2">
                                <div class="weekend">
                                    <form id= 'formid' action='/mood/week/' method='get'>
                                        <input type='hidden' name='projectName' value='${projectName}' />
                                        <input type="hidden" name="date" value="${int['date']?c}" />
                                        <button type='submit' value='submit'>Pas de contenu</button>
                                    </form>
                                </div>
                            </div>
                        </#if>
                        <#else>
                            <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                            <div class="col-xs-12 col-sm-2 col-md-2">
                                <div class="weekend">
                                    <form id= 'formid' action='/mood/week/' method='get'>
                                        <input type='hidden' name='projectName' value='${projectName}' />
                                        <input type="hidden" name="date" value="${int['date']?c}" />
                                        <button type='submit' value='submit'>Pas de contenu</button>
                                    </form>
                                </div>
                            </div>
                    </#if>

                <#elseif int["joursem"]== 4 && int["jour"]== 1 >
                    <#if int["encours"]== "ok">
                        <#if int["med"]?has_content>
                            <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                            <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                            <div class="col-xs-12 col-sm-2 col-md-2">
                                <div class="weekend" style="background-color:rgb(${int["red"]},${int["green"]},${int["blue"]})">
                                    <form id= 'formid' action='/mood/week/' method='get'>
                                        <input type='hidden' name='projectName' value='${projectName}' />
                                        <input type="hidden" name="date" value="${int['date']?c}" />
                                        <button type='submit' value='submit'>${int["nomjour"]} ${int["jour"]}/${mois}</button>
                                    </form>
                                </div>
                            </div>
                            <#else>
                            <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                            <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                            <div class="col-xs-12 col-sm-2 col-md-2">
                                <div class="weekend">
                                    <form id= 'formid' action='/mood/week/' method='get'>
                                        <input type='hidden' name='projectName' value='${projectName}' />
                                        <input type="hidden" name="date" value="${int['date']?c}" />
                                        <button type='submit' value='submit'>Pas de contenu</button>
                                    </form>
                                </div>
                            </div>
                         </#if>
                      <#else>
                        <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                        <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                        <div class="col-xs-12 col-sm-2 col-md-2">
                            <div class="weekend">
                                <form id= 'formid' action='/mood/week/' method='get'>
                                    <input type='hidden' name='projectName' value='${projectName}' />
                                    <input type="hidden" name="date" value="${int['date']?c}" />
                                    <button type='submit' value='submit'>Pas de contenu</button>
                                </form>
                            </div>
                        </div>
                    </#if>

                    <#elseif int["joursem"]== 5 && int["jour"]== 1 >
                        <#if int["encours"]== "ok">
                            <#if int["med"]?has_content>
                                <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                                <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                                <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                                <div class="col-xs-12 col-sm-2 col-md-2">
                                    <div class="weekend" style="background-color:rgb(${int["red"]},${int["green"]},${int["blue"]})">
                                        <form id= 'formid' action='/mood/week/' method='get'>
                                            <input type='hidden' name='projectName' value='${projectName}' />
                                            <input type="hidden" name="date" value="${int['date']?c}" />
                                            <button type='submit' value='submit'>${int["nomjour"]} ${int["jour"]}/${mois}</button>
                                        </form>
                                    </div>
                                </div>
                            <#else>
                                <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                                <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                                <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                                <div class="col-xs-12 col-sm-2 col-md-2">
                                    <div class="weekend">
                                        <form id= 'formid' action='/mood/week/' method='get'>
                                            <input type='hidden' name='projectName' value='${projectName}' />
                                            <input type="hidden" name="date" value="${int['date']?c}" />
                                            <button type='submit' value='submit'>Pas de contenu</button>
                                        </form>
                                    </div>
                                </div>
                            </#if>
                            <#else>
                                <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                                <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                                <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                                <div class="col-xs-12 col-sm-2 col-md-2">
                                    <div class="weekend">
                                        <form id= 'formid' action='/mood/week/' method='get'>
                                            <input type='hidden' name='projectName' value='${projectName}' />
                                            <input type="hidden" name="date" value="${int['date']?c}" />
                                            <button type='submit' value='submit'>Pas de contenu</button>
                                        </form>
                                    </div>
                                </div>
                        </#if>

                    <#elseif int["joursem"]== 6 && int["jour"]== 1 >
                        <#if int["encours"]== "ok">
                            <#if int["med"]?has_content>
                                <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                                <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                                <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                                <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                                <div class="col-xs-12 col-sm-2 col-md-2">
                                    <div class="weekend" style="background-color:rgb(${int["red"]},${int["green"]},${int["blue"]})">
                                        <form id= 'formid' action='/mood/week/' method='get'>
                                            <input type='hidden' name='projectName' value='${projectName}' />
                                            <input type="hidden" name="date" value="${int['date']?c}" />
                                            <button type='submit' value='submit'>${int["nomjour"]} ${int["jour"]}/${mois}</button>
                                        </form>
                                    </div>
                                </div>
                                <#else>
                                <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                                <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                                <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                                <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                                <div class="col-xs-12 col-sm-2 col-md-2">
                                    <div class="weekend">
                                        <form id= 'formid' action='/mood/week/' method='get'>
                                            <input type='hidden' name='projectName' value='${projectName}' />
                                            <input type="hidden" name="date" value="${int['date']?c}" />
                                            <button type='submit' value='submit'>Pas de contenu</button>
                                        </form>
                                    </div>
                                </div>
                            </#if>
                            <#else>
                                <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                                <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                                <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                                <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                                <div class="col-xs-12 col-sm-2 col-md-2">
                                    <div class="weekend">
                                        <form id= 'formid' action='/mood/week/' method='get'>
                                            <input type='hidden' name='projectName' value='${projectName}' />
                                            <input type="hidden" name="date" value="${int['date']?c}" />
                                            <button type='submit' value='submit'>Pas de contenu</button>
                                        </form>
                                    </div>
                                </div>
                        </#if>

                <#elseif int["joursem"]== 7 && int["jour"]== 1 >

                            <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                            <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                            <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                            <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                            <div class="col-xs-12 col-sm-2 col-md-2"><div class="weekend" style="background-color:rgb(255,255,255)"style ="color:white"></div> </div>
                            <div class="col-xs-12 col-sm-2 col-md-1">
                                <div class="weekend" style="background-color:rgb(192,192,192)">
                                    <form id= 'formid' action='/mood/week/' method='get'>
                                        <input type='hidden' name='projectName' value='${projectName}' />
                                        <input type="hidden" name="date" value="${int['date']?c}" />
                                        <button type='submit' value='submit'>WE</button>
                                    </form>
                                </div>
                            </div>

                <#else>
                <#if int["joursem"]== 7>
                            <div class="col-xs-12 col-sm-2 col-md-1">
                                <div class="weekend" style="background-color:rgb(192,192,192)">
                                    <form id= 'formid' action='/mood/week/' method='get'>
                                        <input type='hidden' name='projectName' value='${projectName}' />
                                        <input type="hidden" name="date" value="${int['date']?c}" />
                                        <button type='submit' value='submit'>WE</button>
                                    </form>
                                </div>
                            </div>
                        <#elseif int["joursem"]== 1>
                            <div class="col-xs-12 col-sm-2 col-md-1">
                                <div class="weekend" style="background-color:rgb(192,192,192)">
                                    <form id= 'formid' action='/mood/week/' method='get'>
                                        <input type='hidden' name='projectName' value='${projectName}' />
                                        <input type="hidden" name="date" value="${int['date']?c}" />
                                        <button type='submit' value='submit'>WE</button>
                                    </form>
                                </div>
                            </div>
                        <#else>
                        <#if int["encours"]== "ok">
                            <#if int["med"]?has_content>
                                <div class="col-xs-12 col-sm-2 col-md-2">
                                    <div class="calendjour" style="background-color:rgb(${int["red"]},${int["green"]},${int["blue"]})" >
                                        <form id= 'formid' action='/mood/week/' method='get'>
                                            <input type='hidden' name='projectName' value='${projectName}' />
                                            <input type="hidden" name="date" value="${int['date']?c}" />
                                            <button type='submit' value='submit'>${int["nomjour"]} ${int["jour"]}/${mois}</button>
                                        </form>
                                    </div>
                                </div>
                                <#else>
                                <div class="col-xs-12 col-sm-2 col-md-2">
                                    <div class="weekend">
                                        <form id= 'formid' action='/mood/week/' method='get'>
                                            <input type='hidden' name='projectName' value='${projectName}' />
                                            <input type="hidden" name="date" value="${int['date']?c}" />
                                            <button type='submit' value='submit'>Pas de contenu</button>
                                        </form>
                                    </div>
                                </div>
                            </#if>
                             <#else>
                                <div class="col-xs-12 col-sm-2 col-md-2">
                                    <div class="weekend">
                                        <form id= 'formid' action='/mood/week/' method='get'>
                                            <input type='hidden' name='projectName' value='${projectName}' />
                                            <input type="hidden" name="date" value="${int['date']?c}" />
                                            <button type='submit' value='submit'>Pas de contenu</button>
                                        </form>
                                    </div>
                                </div>
                        </#if>
                </#if>
            </#if>
          </#if>
        </#list>
      </div>
    </div>
           <form id= 'formid' action='/user/resume/' method='get'>
                  <input type='hidden' name='projectName' value='${projectName}' />
                  <input type='hidden' name='date' value='${date?c}'/>
                  <input type='hidden' name='changeMonth'/>
                  <div style="text-align: center">
                    <button type='submit' style='width:300px' value='submit'>Retour au resumé</button>
                  </div>
            </form>

                      <form action='/project/choose' method='get'>
                    <div>
                        <#include "../includable/security/securityToken.ftl">
                    </div>
                    <div style="text-align: center">
                                      <input type='hidden' name='projectName' value='${projectName}' />
                  <input type='hidden' name='date' value='${date?c}'/>
                <button type='submit' style='width:300px' value='submit'>Vers la selection de projet</button>
               </div>
              </form>
 </div>

    <footer>
        <img src="/img/basdepage.jpg">
    </footer>

</body>
</html>