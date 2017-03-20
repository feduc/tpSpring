<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
    <h1> ${page} </h1>

    <table class="table table-bordered table-hover">
        <tr>
            <#list items as item>
                <#list fields as field>
                    <#list item?keys as key>
                        <#if key==field['name']>
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
                    <#if key==field['name']>
                        <#if item[key]?is_boolean>
                            <td>${item[key]?c}</td>
                        <#elseif item[key]?is_date>
                            <td>${item[key]?string('yyyy/MM/dd HH:mm:ss')}</td>
                        <#else>
                            <td>${item[key]}</td>
                        </#if>
                    </#if>
               </#list>
            </#list>
                <td><a href="${item["id"]}/show">Select</a></td>
                <td>
                    <form action="${item["id"]}/delete" method="POST">
                        <input type="hidden" name="id" value="${item["id"]}">
                        <input type="submit" value="delete"/>
                    </form>
                </td>
            </tr>
        </#list>
    </table>

</body>