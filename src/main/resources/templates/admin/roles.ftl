<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
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
  <#if admin==true>
  <h1 align="center"> Page d'administration</h1>
  <#else>
  <h1 align="center"> Page de modération</h1>
  </#if>

  <div id="main">

<div class="container">
      <div class="row">
      <#include "../includable/adminNav.ftl">
      <div class="col-xs-12 col-sm-8 col-md-8 pull-right">
        <table class="table">
            <thead>
                <tr>
                    <th>Role</th>
                    <th>Retirer à l'utilisateur</th>
                </tr>
            </thead>
            <tbody>
                <#if roles??>
                <#list roles as role>
                    <tr>
                        <td>${role["role"]}</td>
                        <td>
                            <form action="/admin/${userId}/roles/${role["id"]}/remove" method ="POST">
                            <input type="hidden" name="userRegistration" value="${userRegistration}"/>
                            <#include "../includable/security/securityToken.ftl">
                            <input type="submit" value="Retirer"/>
                            </form>
                        </td>
                    </tr>
                </#list>
                </#if>
            </tbody>
        </table>
      </div>
      <div class="col-xs-12 col-sm-8 col-md-8 pull-right">
        <table class="table">
            <thead>
                <tr>
                    <th>Role</th>
                    <th>Ajouter à l'utilisateur</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>admin</td>
                    <td>
                        <form action="/admin/${userId}/roles/1/add" method ="POST">
                            <input type="hidden" name="userRegistration" value="${userRegistration}"/>
                            <#include "../includable/security/securityToken.ftl">
                            <input type="submit" value="Ajouter"/>
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>modo</td>
                    <td>
                        <form action="/admin/${userId}/roles/2/add" method ="POST">
                            <input type="hidden" name="userRegistration" value="${userRegistration}"/>
                            <#include "../includable/security/securityToken.ftl">
                            <input type="submit" value="Ajouter"/>
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>visu</td>
                    <td>
                        <form action="/admin/${userId}/roles/3/add" method ="POST">
                            <input type="hidden" name="userRegistration" value="${userRegistration}"/>
                            <#include "../includable/security/securityToken.ftl">
                            <input type="submit" value="Ajouter"/>
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>user</td>
                    <td>
                        <form action="/admin/${userId}/roles/4/add" method ="POST">
                            <input type="hidden" name="userRegistration" value="${userRegistration}"/>
                            <#include "../includable/security/securityToken.ftl">
                            <input type="submit" value="Ajouter"/>
                        </form>
                    </td>
                </tr>
            </tbody>
        </table>
      </div>
        </div>
      </div>
      </div>
 <footer>
     <img src="/img/NikoSmile-logo.png" align="left" height="80px">
     <img src="/img/LogoCGI.png" align="right" height="80px">
  </footer>
</body>