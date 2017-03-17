<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
    <h1> ${page} </h1>
    <form action="${path}/update/do" method="POST">
        <table class="table table-bordered table-hover">
            <tr>

                    <#list item?keys as key>
                        <#if key==field>
                            <th>${key} :</th>
                        </#if>
                    </#list>

            </tr>
            <tr>

                    <#list item?keys as key>
                        <#if key==field>
                            <#list item[field] as child>
                                <td><input type="text"  value="${child}" name="${key}"/></td> 
                            </#list>
                        </#if>
                   </#list>       

            </tr>
        </table>
        <button type="submit" value="submit">Update</button>
    </form>
</body>