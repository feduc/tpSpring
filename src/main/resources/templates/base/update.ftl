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
    <div align = right>
      <a href =/user/parameters><img src='/img/parametre.png' alt='exemple' width='5%'></a>
      <a href =/logout><img src='/img/logout.jpg' alt='exemple' width='5%'></a>
    </div>
  <h1 align="center"> Page d'administration</h1>

  <div class="container">
      <div class="row">
      <#include "../includable/adminNav.ftl">
      <div class="col-xs-12 col-sm-8 col-md-8">
         <div class="changeavis">

    <font color = "white"><h1> ${page}
                <#list fields as field>
                  <#list item?keys as key>
                    <#if key==field["name"]>
                        <#if key == "name">
                            ${item[key]}
                        </#if>
                     </#if>
                </#list>
            </#list>
    </h1></font>
    <form action="${path}/${item["id"]}/update/done" method="POST">

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
                            <#elseif key == "name">
                                <input type="text"  value="${item[key]}" name="${key}" readonly/> <br/>
                            <#elseif item[key]?is_boolean>
                                <input type="text"  value="${item[key]?c}" name="${key}" pattern="(true|false)"
                                title="boolean : true/false" required="required"/><br/>
                            <#elseif item[key]?is_date_like>
                                <input type="text"  value="${item[key]?string('yyyy/MM/dd HH:mm:ss')}" name="${key}"
                                required="required" title="Date : YYYY/MM/DD HH:MM:SS" pattern="[0-2][0-9][0-9][0-9]\/((02\/(0[1-9]|[12][0-9]))|((0[469]|11)\/(0[1-9]|[12][0-9]|30))|((0[13578]|1[02])\/(0[1-9]|[12][0-9]|3[01])))\s([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]"/><br/>
                            <#elseif item[key]?is_number>
                                <input type="text"  value="${item[key]}" name="${key}" data-validation="number" data-validation-allowing="integer"/><br/>
                            <#else>
                                <input type="text"  value="${item[key]}" name="${key}"/><br/>
                            </#if>
                        </#if>
                   </#list>
                </#list>

        <button type="submit" value="submit">Update</button>
    <#include "../includable/security/securityToken.ftl">
    </form>

                </div>
        </div>
      </div>
      </div>
 <footer>
    <img src="/img/basdepage.JPG">
  </footer>
</body>