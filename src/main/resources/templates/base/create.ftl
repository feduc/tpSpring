<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>
    <script src="/Chart.js-master/src/chart.js"></script>
      <link rel="stylesheet" href="/css/bootstrap.css">
      <link rel="stylesheet" href="/css/main.css">
</head>
<body>
    <header>
        <img src="/img/BaniereCGI.jpg">
    </header>
<div align = right><a href ="page de vote.html"><img src="assets/img/parametre.png" alt="exemple" width="5%"></a>
                    <a href ="page_accueil.html"><img src="assets/img/logout.jpg" alt="exemple" width="5%"></a></div>
  <h1 align="center"> Page d'administration</h1>

  <div id="main">

<div class="container">
      <div class="row">
        <div class="col-xs-12 col-sm-2 col-md-4">
         <div class="changeavis">

            <a href="/user/create/">
            <input type="button" name="Repondre "style="width:300px"value="Creation/modification d'un utilisateur"/></a>
            </br>

            <a href="/security/create/">
            <input type="button" name="Repondre "style="width:300px"value="Creation/modification d'un login/mdp/status"/></a>
            </br>

            <a href="/project/create/">
            <input type="button" name="Repondre "style="width:300px"value="Creation/modification d'un projet"/></a>
            </br>

            <a href="/project/create/">
            <input type="button" name="Repondre "style="width:300px"value="Gestion d'un projet"/></a>
            </br>

            <a href="/user/resume/">
            <input type="button" name="Repondre "style="width:300px"value="Vues Projets"/></a>
            </br>

           <form action="/mood/admin/vote/" method="GET">
            <input type="hidden" name="id" value="${child["id"]}" />
            <input type="hidden" name="agency" value="${child["agency"]}" />
            <input type="hidden" name="firstName" value="${child["firstName"]}" />
            <input type="hidden" name="lastName" value="${child["lastName"]}" />
            <input type="hidden" name="registrationCGI" value="${child["registrationCGI"]}" />
            <input type="hidden" name="verticale" value="${child["verticale"]}" />
            <button type="submit" style="width:300px"value="submit">Page de vote</button>
            </form>
        </div>
      </div>
      <div class="col-xs-12 col-sm-2 col-md-8">
         <div class="changeavis">
            <font color = "white"><h1> ${page} </h1> </font>
                <form action="${path}/create/do" method="POST" id = "renseignement">
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
                  </br>
 -        <button type="submit" value="submit">Create</button>
            </form>
          </div>
        </div>
      </div>
    </div>
 <footer>
    <img src="/img/basdepage.JPG">
 </footer>
</body>