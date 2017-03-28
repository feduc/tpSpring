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
                    <a href =/security/login/><img src="/img/logout.jpg" alt="exemple" width="5%"></a></div>
  <h1 align="center"> Page d'administration</h1>

  <div id="main">

<div class="container">
      <div class="row">
        <div class="col-xs-12 col-sm-4 col-md-4">
         <div class="changeavis">
            <p style="align:center" ><font color="white"><b>Creation/Modification</b></font></p>

            <form id= "formid" action="/user/create/" method="get">
            <input style="width:75%" type="submit" value="Utilisateur"/>
            </form>

            <form id= "formid" action="/security/create/" method="get">
            <input style="width:75%" type="submit" value="Login/mdp/status"/>
            </form>

            <form id= "formid" action="/project/create/" method="get">
            <input style="width:75%" type="submit" value="Projet"/>
            </form>

            <form id= "formid" action="/admin/choose/" method="get">
            <input style="width:75%" type="submit" value="Membres d'un projet"/>
            </form>

            <hr style= "width:75%"/>

            <p style="align:center" ><font color="white"><b>Perso</b></font></p>

            <form id= "formid" action="/user/resume/" method="get">
            <input style="width:75%" type="submit" value="Ma page perso"/>
            </form>

            <form id= "formid" action="/mood/vote/" method="get">
            <input style="width:75%" type="submit" value="Voter"/>
            </form>
            <br/>

          </div>
      </div>
      <div class="col-xs-12 col-sm-8 col-md-8">
         <div class="changeavis">
    <font color = "white"><h1> ${page} </h1><font color = "white">

    <table class="table table-bordered table-hover" style:"color="white"">
        <tr>
            <#list items as item>
                <#list fields as field>
                    <#list item?keys as key>
                        <#if key==field['name']>
                            <font color = "white"><th>${key}</th><font color = "white">
                        </#if>
                    </#list>
                </#list>
                <#break>
            </#list>
        </tr>
        <#list items as item>
            <tr>
            <#list fields as field>
                <#list item?keys as key>
                    <#if key==field['name']>
                        <#if item[key]?is_boolean>
                             <font color = "white"><td>${item[key]?c}</td></font>
                        <#elseif item[key]?is_date>
                             <font color = "white"><td>${item[key]?string('yyyy/MM/dd HH:mm:ss')}</td></font>
                        <#else>
                             <font color = "white"><td>${item[key]}</td></font>
                        </#if>
                    </#if>
               </#list>
            </#list>
                <td><a href="../${item["id"]}/show">Select</a></td>
                <td>
                    <form action="${item["id"]}/delete" method="POST">
                        <input type="hidden" name="id" value="${item["id"]}">
                        <input type="submit" value="delete"/>
                    </form>
                </td>
            </tr>
        </#list>
    </table>
    </div>

</body>
 <footer>
    <img src="/img/basdepage.JPG">
  </footer>
</body>