<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
    <h1> ${page} </h1>

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
                            <td>${item[key]?c}</td> 
                        <#else>
                            <td>${item[key]}</td>  
                        </#if>
                    </#if>
               </#list>       
            </#list>
        </tr>
    </table>

</body>