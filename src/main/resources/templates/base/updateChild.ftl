<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<script src="Chart.js-master/src/chart.js"></script>

<canvas id="Line" data-type="Line" width="600" height="400"></canvas>


<script type="text/javascript">
//Get the context of the canvas element we want to select
var ctx = document.getElementbyId("Line").getContext("2d");
var myNewChart = new Chart(ctx).PolarArea(data);

new Chart(ctx).Line(data,options);
var data = {
    labels : ["January","February","March","April","May","June","July"],
    datasets : [
        {
            fillColor : "rgba(220,220,220,0.5)",
            strokeColor : "rgba(220,220,220,1)",
            pointColor : "rgba(220,220,220,1)",
            pointStrokeColor : "#fff",
            data : [65,59,90,81,56,55,40]
        },
        {
            fillColor : "rgba(151,187,205,0.5)",
            strokeColor : "rgba(151,187,205,1)",
            pointColor : "rgba(151,187,205,1)",
            pointStrokeColor : "#fff",
            data : [28,48,40,19,96,27,100]
        }
    ]
}

</script>



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