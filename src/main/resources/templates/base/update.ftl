<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>
    <script>
        $.validate({
        modules : 'date'
        });
    </script>
</head>
<body>
    <h1> ${page} </h1>
    <form action="${path}/${item["id"]}/update/do" method="POST">
        <table class="table table-bordered table-hover">
            <tr>
                <#list fields as field>
                    <#list item?keys as key>
                        <#if key==field["name"]>
                            <th>${key} :</th>
                        </#if>
                    </#list>
                </#list>
            </tr>
            <tr>
                <#list fields as field>
                    <#list item?keys as key>
                        <#if key==field["name"]>
                            <#if key == "id">
                                <td><input type="text"  value="${item[key]?c}" name="${key}" readonly/></td>
                            <#elseif item[key]?is_boolean>
                                <td><input type="text"  value="${item[key]?c}" name="${key}" pattern="(true|false)"/></td> 
                            <#elseif item[key]?is_date_like>
                                <td><input type="text"  value="${item[key]?string('yyyy/MM/dd HH:mm:ss')}" name="${key}" 
                                pattern="[0-2][0-9][0-9][0-9]\/((02\/(0[1-9]|[12][0-9]))|((0[469]|11)\/(0[1-9]|[12][0-9]|30))|((0[13578]|1[02])\/(0[1-9]|[12][0-9]|3[01])))\s([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]"/></td> 
                            <#elseif item[key]?is_number>
                                <td><input type="text"  value="${item[key]}" name="${key}" data-validation="number" data-validation-allowing="integer"/></td>                          
                            <#else>
                                <td><input type="text"  value="${item[key]}" name="${key}"/></td> 
                            </#if>
                        </#if>
                   </#list>       
                </#list>
            </tr>
        </table>
        <button type="submit" value="submit">Update</button>
    </form>
</body>