<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>
    <script src="/Chart.js-master/src/chart.js"></script>
      <link rel="stylesheet" href="/css/bootstrap.css">
      <link rel="stylesheet" href="/css/main1.css">
</head>
<body>
    <header>
        <img src="/img/BaniereCGI.jpg">
    </header>
<div align = right><a href ="page de vote.html"><img src="/img/parametre.png" alt="exemple" width="5%"></a>
                    <a href =/security/login/><img src="/img/logout.jpg" alt="exemple" width="5%"></a></div>
  <h1 align="center"> Page d'administration</h1>

  <div id="main">

<div class="container">
      <div class="row">
        <div class="col-xs-12 col-sm-4 col-md-4">
         <div class="changeavis">
            <p style="align:center" ><font color="white"><b>Creation/Modification</b></font></p>

            <form id= "formid" action="/user/create/" method="get">
            <input style="width:75%" type="submit" value="Utilisateur"/>
            </form>

            <form id= "formid" action="/security/create/" method="get">
            <input style="width:75%" type="submit" value="Login/mdp/status"/>
            </form>

            <form id= "formid" action="/project/create/" method="get">
            <input style="width:75%" type="submit" value="Projet"/>
            </form>

            <form id= "formid" action="/admin/choose/" method="get">
            <input style="width:75%" type="submit" value="Membres d'un projet"/>
            </form>

            <hr style= "width:75%"/>

            <p style="align:center" ><font color="white"><b>Perso</b></font></p>

            <form id= "formid" action="/user/resume/" method="get">
            <input style="width:75%" type="submit" value="Ma page perso"/>
            </form>

            <form id= "formid" action="/mood/vote/" method="get">
            <input style="width:75%" type="submit" value="Voter"/>
            </form>
            <br/>

          </div>
      </div>
      <div class="col-xs-12 col-sm-8 col-md-8">
         <div class="changeavis">
            <font color = "white"><h1> Choisissez un projet </h1> </font>
                <form action="admin/choose/" method="GET" >
                    <font color = "white"> <th>Nom du projet :</th></br></font>
                    <input type="text" name="projectName" placeholder="entrez le nom du projet"/>

               </br>
                <button type="submit" value="submit">Chercher</button>
            </form>
             </div>
        </div>
      </div>
      </div>
 <footer>
    <img src="/img/basdepage.JPG">
  </footer>
</body>