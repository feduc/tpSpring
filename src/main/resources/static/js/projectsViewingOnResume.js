function addProject(projectName) {
	var name = "projectName";
	var projectDiv = $("#actualProjects");
	    projectDiv.innerHTML += "<div class='col-xs-12 col-sm-6 col-md-4'>";
        projectDiv.innerHTML += "<div class='projet'>";
        projectDiv.innerHTML += "<p> "+name+"</p>";
        projectDiv.innerHTML += "<div id='donutchart"+name+"' style='width: 300px; height: 200px;' ></div>";
        projectDiv.innerHTML += "<form id= 'formid' action='/mood/week/change' method='get'>";
        projectDiv.innerHTML += "<input type='hidden' name='id' value='${child['id']}' />";
        projectDiv.innerHTML += "<input type='hidden' name='agency' value='${child['agency']}' />";
        projectDiv.innerHTML += "<input type='hidden' name='firstName' value='${child['firstName']}' />";
        projectDiv.innerHTML += "<input type='hidden' name='lastName' value='${child['lastName']}' />";
        projectDiv.innerHTML += "<input type='hidden' name='registrationCGI' value='${child['registrationCGI']}' />";
        projectDiv.innerHTML += "<input type='hidden' name='verticale' value='${child['verticale']}' />";
        projectDiv.innerHTML += "<input type='hidden' name='projectName' value='"+ name +"' />";
        projectDiv.innerHTML += "<input type='hidden' name='date' value='${date?c}'/>";
        projectDiv.innerHTML += "<input type='hidden' name='changeWeek'/>";
        projectDiv.innerHTML += "<button type='submit' style='width:300px' value='submit'>Voir</button>";
        projectDiv.innerHTML += "</form>";
        projectDiv.innerHTML += "</div>";
        projectDiv.innerHTML += "</div>";
}


function addChart(projectName) {
	var name = "projectName";
    var chartDiv = $("#chart");
        chartDiv.innerHTML += "<script type='text/javascript'>";
        chartDiv.innerHTML += "google.charts.load('current', {packages:['corechart']});";
        chartDiv.innerHTML += "google.charts.setOnLoadCallback(drawChart);";
        chartDiv.innerHTML += "function drawChart() {";
        chartDiv.innerHTML += "var data = google.visualization.arrayToDataTable([";
        chartDiv.innerHTML += "['Task', 'Avis'],";
        chartDiv.innerHTML += "['Content',     60],";
        chartDiv.innerHTML += "['Normal',      20],";
        chartDiv.innerHTML += "['Mecontent',  20],";
        chartDiv.innerHTML += "]);";
        chartDiv.innerHTML += "var options = {";
        chartDiv.innerHTML += "title: 'Nikos du jour',";
        chartDiv.innerHTML += "pieHole: 0.4,";
        chartDiv.innerHTML += "colors:['green','yellow','red'],";
        chartDiv.innerHTML += "backgroundColor : '#a00000'";
        chartDiv.innerHTML += "};";
        chartDiv.innerHTML += "var chart = new google.visualization.PieChart(document.getElementById('donutchart"+name+"'));";
        chartDiv.innerHTML += "chart.draw(data, options);";
        chartDiv.innerHTML += "}";
        chartDiv.innerHTML += "</script>";
}