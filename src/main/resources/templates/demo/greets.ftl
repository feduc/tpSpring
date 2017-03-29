<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js">
    </script>
</head>
<body>
    <div class="main">
        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Greeting</th>
                </tr>
            </thead>
            <tbody id="tbody">
            </tbody>
        </table>

        <form>
            <label for="offset">Indiquer le premier élément que vous souhaitez voir :</label><input type="text" id="offset" name="offset" value="1">
            <label for="limit">Indiquer le nombre de résultats que vous souhaitez afficher :</label><input type="text" id="limit" name="limit" value="10">
        </form> 

        <button onclick=refresh()>Refresh</button>
    </div>

<script type="text/javascript">
    function refresh () {
     var xhttp = new XMLHttpRequest();
     var offset = $(#offset).val();
     var limit = $(#limit).val();
     var url = "demo/greeting" + "?offset="+ offset +"&limit=" + limit;

     $("#tbody").ajax(url)
        .done(function(data) {
            var elt = $(this);
            elt.empty();
            $(data.content).each(function(index, element){
                elt.append(<tr><td>element.id</td><td>element.content</td></tr>)
            });
        });
    };
</script>
<script type="text/javascript">
    $(document).ready(refresh());
</script>

</body>
</html>