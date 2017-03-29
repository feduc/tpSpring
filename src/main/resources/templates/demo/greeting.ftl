<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF8">
  <title>Niko Smile</title>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <link rel="stylesheet" href="/css/main.css">
</head>
<body>
    <header>
     <img src="/img/BaniereCGI.jpg">
  </header>

            <#list greeting as greeting>
                <#list fields as field>
                    <#list item?keys as key>
                        <#if key==field['name']>
                            <font color = "white"><th>${key}</th><font color = "white">
                        </#if>
                    </#list>
                </#list>
                <#break>
            </#list>
  <footer>
    <img src="/image/basdepage.jpg">
  </footer>
</body>
</html>