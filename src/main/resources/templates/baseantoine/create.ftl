
<head>
    <#include "../includable/bootstrap.ftl">
    <#include "../includable/jquery.ftl">
    <#include "../includable/baseFieldValidator.ftl">
</head>
<html>
<body>
    <h1>${page}</h1>
    <form action="${path}/create/do" method="POST" id = "renseignement">
        <#list fields as field>
            <#list currentItem?keys as key>
                <#assign subItem = currentItem[key]>
                <#if field == key>
                    <#if field != "id">
                        <#if subItem['type'] == "Date">
                            <br>
                                Name:${key}
                                <input id="${key}" type="datetime" readonly
                                    name="${key}"
                                    value="" />
                                <input id="${key}date" type="date" value="">
                                <input id="${key}time" type="time" value="">
                            </br>
                        <#else>
                            <br>
                                Name:${key}
                                <input type="text"
                                    name="${key}"
                                    value="" />
                            </br>
                        </#if>
                    </#if>
                </#if>
            </#list>
        </#list>
               <button type="submit" value="submit">Create</button>
            </form>
    <a href="list">Back</a>

    <form id="createForm" action="" method="POST">
        <#include "../includable/formCreateContent.ftl">
        <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
        <br>
            <input type="submit" value="submit"/>
        </br>
    </form>
    <a href="${basePage}">Back</a>
</body>
</html>