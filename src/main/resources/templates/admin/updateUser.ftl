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

  <div class="container">
      <div class="row">
      <#include "../includable/adminNav.ftl">
      <div class="col-xs-12 col-sm-8 col-md-8">
         <div class="changeavis">

    <h1> <font color = "white">Update User : ${item["registrationCGI"]} </font></h1>
    <form action="/user/${item["id"]}/update/done" method="POST">
        <font color = "white">login :</font><br/>
        <input type="text"  value="${security["login"]}" name="login" readonly/><br/>
        <font color = "white">password :</font><br/>
        <input type="text" name="password" required="required"/><br/>
        <font color = "white">enable :</font><br/>
        <input type="text"  value="${security["enable"]?c}" name="enable" pattern="(true|false)" required="required"/><br/>
        <#list fields as field>
                      <#list item?keys as key>
                        <#if key==field["name"]>
                            <#if key == "id">
                            <#else>
                            <font color = "white">${key} :</font><br/>
                            </#if>
                        </#if>
                    </#list>
                    <#list item?keys as key>
                        <#if key==field["name"]>
                            <#if key == "id">
                            <#elseif key == "registrationCGI">
                                <input type="text"  value="${item[key]}" name="${key}" readonly/><br/>
                            <#elseif item[key]?is_boolean>
                                <input type="text"  value="${item[key]?c}" name="${key}" pattern="(true|false)"
                                title="boolean : true/false" required="required"/><br/>
                            <#elseif item[key]?is_date_like>
                                <input type="text"  value="${item[key]?string('yyyy/MM/dd HH:mm:ss')}" name="${key}"
                                required="required" title="Date : YYYY/MM/DD HH:MM:SS" pattern="[0-2][0-9][0-9][0-9]\/((02\/(0[1-9]|[12][0-9]))|((0[469]|11)\/(0[1-9]|[12][0-9]|30))|((0[13578]|1[02])\/(0[1-9]|[12][0-9]|3[01])))\s([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]"/><br/>
                            <#elseif item[key]?is_number>
                                <input type="text"  value="${item[key]}" name="${key}" pattern="-?[0-9]*" required="required"/><br/>
                            <#else>
                                <input type="text"  value="${item[key]}" name="${key}" required="required"/><br/>
                            </#if>
                        </#if>
                   </#list>
                </#list>
        <br/>
        <button type="submit" value="submit">Update</button>
    <#include "../includable/security/securityToken.ftl">
    </form>
    </div>
    </div>
    </div>
    </div>
     <footer>
     <img src="/img/NikoSmile-logo.png" align="left" height="80px">
     <img src="/img/LogoCGI.png" align="right" height="80px">
     </footer>
</body>