<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VirtualTrainer</title>
<link href="css/bootstrap.css" rel="stylesheet">	

<script src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<!-- Le fav and touch icons -->
<link rel="shortcut icon" href="../assets/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144" href="ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114" href="ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72" href="ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed" href="ico/apple-touch-icon-57-precomposed.png">
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="/newfitness/pages/admin"/> New Fitness </a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active"><a href="/newfitness/pages/admin" /> Principal</a></li>
              <li><a href="/newfitness/pages/about">Sobre</a></li>
              <li><a href="#contact">Contato</a></li>
            </ul>
            <form action="j_spring_security_check" id="formLogin" name="formAutenticacao" method="post" class="navbar-form pull-right">
              <input class="span2" type="text" name= "j_username" placeholder="Usuário">
              <input class="span2" type="password" name= "j_password" placeholder="Senha">
              <button type="submit" class="btn">Login</button>
            </form>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>
    
	<div class="container">
	  <c:if test="${param.mensagemErroLogin != null}">
	  	<h4> <c:out value="${param.mensagemErroLogin}"/> </h4>
	  </c:if>
      <!-- Main hero unit for a primary marketing message or call to action -->
      <div class="hero-unit">
        <center><img src="img/logo.jpg"></center>
        
        <br><br><br>
        <center><p>O Sistema da academia new fitness.</p></center>
      </div>
      
      <hr>

      <footer>
        <p>&copy; New fitness</p>
      </footer>

    </div>    
</body>
</html>