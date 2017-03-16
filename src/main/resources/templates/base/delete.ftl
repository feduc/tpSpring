<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
    <h1> ${page} </h1>
    <form action="/${path}/delete/do" method="POST">
        <table class="table table-bordered table-hover">
            <tr>

                <#list fields as field>
                    <#list item?keys as key>
                        <#if key==field>
                            <th>${key}</th>
                        </#if>
                    </#list>
                </#list>
            </tr>
            <tr>
                <#list fields as field>
                    <#list item?keys as key>
                        <#if key==field>
                            <#if item[key]?is_boolean>
                                <td><input type="text"  value="${item[key]?c}" name="${key}" readonly/> 
                            <#elseif item[key]?is_date>
                                <td><input type="text"  value="${item[key]?string('yyyy/MM/dd HH:mm:ss')}" name="${key}" readonly/></td> 
                            <#else>
                                <td><input type="text"  value="${item[key]}" name="${key}" readonly/></td> 
                            </#if>
                        </#if>
                    </#list>       
                </#list>
            </tr>
        </table>
        <button type="submit" value="submit">Delete</button>
    </form>
</body>