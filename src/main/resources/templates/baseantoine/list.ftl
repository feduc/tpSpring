
<head>
    <#include "../includable/bootstrap.ftl">
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


            <form id= "formid" action="/mood/admin/vote/" method="GET">
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
    <a href="create">Create new</a>
    <table class="table table-bordered table-hover">
        <tr>
            <#list items as item>
                <#list fields as field>
                    <#list item?keys as key>
                        <#if field == key>
                            <th>${key}</th>
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
                        <#if field == key>
                            <#if item[key]?is_boolean>
                                <td>${item[key]?c}</td>
                            <#elseif item[key]?is_date_like>
                                <td>${item[key]?string("yyyy/MM/dd HH:mm:ss")}</td>
                            <#else>
                                <td>${item[key]}</td>
                            </#if>
                        </#if>
                    </#list>
                </#list>
                <td><a href="${item}/${item["id"]}/show">Select</a></td>
                <td>
                    <form action="${item["id"]}/delete" method="POST">
                        <input type="hidden" name="id" value="${item["id"]}">
                        <input type="submit" value="delete"/>
                    </form>
                </td>
            </tr>
        </#list>
    </table>
 <footer>
    <img src="/img/basdepage.JPG">
 </footer>
</body>