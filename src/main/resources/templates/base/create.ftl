<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
    <h1> ${page} </h1>
    <form action="/${path}/create/do" method="POST">
        <table class="table table-bordered table-hover">
            <tr>
                <#list fields as field>
                    <th>${field} :</th>
                </#list>
            </tr>
            <tr>
                <#list fields as field>
                    <td><input type="text" name="${field}"/></td>  
                </#list>
            </tr>
        </table>
        <button type="submit" value="submit">Create</button>
    </form>
</body>