
<head>
    <#include "../../includable/bootstrap.ftl">
</head>
<body>
    <h1>${page}</h1>
    <div class="container">
        <ul>
            <#list controllers as controller>
                <li>
                    <a href="/${pathlinker}${controller}${pathindex}">${controller}</a>
                </li>
            </#list>
        </ul>
    </div>
</body>

</html>
