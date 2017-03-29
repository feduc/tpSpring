<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>
    <script src="/Chart.js-master/src/chart.js"></script>
      <link rel="stylesheet" href="/css/bootstrap.css">
      <link rel="stylesheet" href="/css/main1.css">
</head>
<body>
    <header>
        <img src="/img/BaniereCGI.jpg">
    </header>
<div align = right><a href ="page de vote.html"><img src="/img/parametre.png" alt="exemple" width="5%"></a>
                    <a href =/logout><img src="/img/logout.jpg" alt="exemple" width="5%"></a></div>
  <h1 align="center"> Page d'administration</h1>

  <div id="main">

<div class="container">
      <div class="row">
      <#include "../includable/adminNav.ftl">
      <div class="col-xs-12 col-sm-8 col-md-8">
         <div class="changeavis">
            <font color = "white"><h1> ${page} </h1> </font>
                <form action="/user/create/done" method="POST" id = "renseignement">
                        <font color = "white"><th>Login :</th></br></font>
                        <input type="text" name="login" required="required"/>
                        </br>
                                 
                        <font color = "white"><th>Mot de Passe :</th></br></font>
                        <input type="text" name="password" required="required"/>
                        </br>
                    <#list fields as field>
                        <#if field["name"]=="id">
                        <#elseif field["type"]== "boolean">
                        <font color = "white"><th>${field["name"]} :</th></br></font>
                            <input type="text" name="${field["name"]}" pattern="(true|false)"
                                 title="boolean : true/false" required="required"/>
                                 </br>
                        <#elseif field["type"] == "Date">
                        <font color = "white"> <th>${field["name"]} :</th></br></font>
                      <input type="text" name="${field["name"]}" required="required" title="Date : YYYY/MM/DD HH:MM:SS"
                                 pattern="[0-9][0-9][0-9][0-9]\/((02\/(0[1-9]|[12][0-9]))|((0[469]|11)\/(0[1-9]|[12][0-9]|30))|((0[13578]|1[02])\/(0[1-9]|[12][0-9]|3[01])))\s([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]"/>
                        </br>
                        <#elseif field["type"] == "int">
                        <font color = "white"> <th>${field["name"]} :</th></br></font>
                          <input type="text" name="${field["name"]}" pattern="(-?[0-9]+)" title="integer"
                                  required="required"/>
                         </br>
                        <#elseif field["type"] == "Long">
                        <font color = "white"> <th>${field["name"]} :</th></br></font>
                            <input type="text" name="${field["name"]}" pattern="(-?[0-9])+" title="integer"
                                  required="required"/>
                         </br>
                        <#else>
                        <font color = "white"> <th>${field["name"]} :</th></br></font>
                        <input type="text" name="${field["name"]}" required="required"/>
                        </br>
                       </#if>
                    </#list>
                    <#include "../includable/security/securityToken.ftl">
               </br>
                <button type="submit" value="submit">Create</button>

                    <div>
        <#include "../includable/security/securityToken.ftl">
    </div>
            </form>

            <form id= "formid" action="/user/list/" method="get">
    <div>
        <#include "../includable/security/securityToken.ftl">
    </div>
            <input style="width:7%" type="submit" value="Liste"/>
            </form>
            </div>
        </div>
      </div>
      </div>
 <footer>
    <img src="/img/basdepage.JPG">
  </footer>
</body>