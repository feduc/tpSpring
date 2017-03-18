<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>
    <script src="/Chart.js-master/src/chart.js"></script>
</head>
<body>

    <h1> ${page} </h1>
    <form action="${path}/create/do" method="POST">
        <table class="table table-bordered table-hover">
            <tr>
                <#list fields as field>
                    <#if field["name"]=="id">
                    <#else>
                        <th>${field["name"]} :</th>
                    </#if>
                </#list>
            </tr>
            <tr>
                <#list fields as field>
                        <#if field["name"]=="id">
                        <#elseif field["type"]== "boolean">
                            <td><input type="text" name="${field["name"]}" pattern="(true|false)"
                                 title="boolean : true/false" required="required"/></td>
                        <#elseif field["type"] == "Date">
                            <td><input type="text" name="${field["name"]}" required="required" title="Date : YYYY/MM/DD HH:MM:SS"
                                 pattern="[0-9][0-9][0-9][0-9]\/((02\/(0[1-9]|[12][0-9]))|((0[469]|11)\/(0[1-9]|[12][0-9]|30))|((0[13578]|1[02])\/(0[1-9]|[12][0-9]|3[01])))\s([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]"/></td>
                        <#elseif field["type"] == "int">
                            <td><input type="text" name="${field["name"]}" pattern="(-?[0-9]+)" title="integer"
                                  required="required"/></td>
                        <#elseif field["type"] == "Long">
                            <td><input type="text" name="${field["name"]}" pattern="(-?[0-9])+" title="integer"
                                  required="required"/></td>
                        <#else>
                        <td><input type="text" name="${field["name"]}" required="required"/></td>
                    </#if>
                </#list>
            </tr>
        </table>
        <button type="submit" value="submit">Create</button>
    </form>
</body>