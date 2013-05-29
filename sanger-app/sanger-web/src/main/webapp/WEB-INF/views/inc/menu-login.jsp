<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="#">SANGER</a>
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li class="active"><a href="<c:url value='/' />">In&iacute;cio</a></li>
                    <li><a href="#about">Sobre</a></li>
                    <li><a href="#contact">Contato</a></li>
                </ul>
                <form class="navbar-form pull-right" action="<c:url value='login.php' />" method="post">
                    <input class="span2" type="text" placeholder="Login" name="login">
                    <input class="span2" type="password" placeholder="Senha" name="senha">
                    <button type="submit" class="btn">Entrar</button>
                </form>
            </div><!--/.nav-collapse -->
        </div>
    </div>
</div>

