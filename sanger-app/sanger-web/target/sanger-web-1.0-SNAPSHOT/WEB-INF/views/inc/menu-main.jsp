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
                    <li class="active"><a href="<c:url value='/'/>">In&iacute;cio</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Cadastrar<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="<c:url value='/clientepessoafisica/listar' />">Cliente (Pessoa Física)</a></li>
                            <li><a href="<c:url value='/clientepessoajuridica/listar' />">Cliente (Pessoa Jurídica)</a></li>
                            <li><a href="<c:url value='/autonomo/listar' />">Autonomo</a></li>
                            <li><a href="<c:url value='/motorista/listar' />">Motorista</a></li>
                            <li><a href="<c:url value='/veiculodetransporte/listar' />">Veículo de Transporte</a></li>
                            <li><a href="<c:url value='/simbolo/listar' />">Estado das Peças (Simbologia)</a></li>
                            <li><a href="<c:url value='/localizacao/listar' />">Estado das Peças (Localização)</a></li>
                        </ul>
                    </li>                     
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Movimentação<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="<c:url value='/transportelocal/listar' />">Transporte Local</a></li>
                            <li><a href="<c:url value='#' />">Transporte Interestadual</a></li>
                            <li><a href="<c:url value='#' />">Transporte de Autom&oacute;vel</a></li>
                            <li><a href="<c:url value='#' />">Recibo</a></li>
                        </ul>
                    </li>                    
                    <li><a href="<c:url value='#' />">Sair</a></li>
                </ul>
            </div><!--/.nav-collapse -->
        </div>
    </div>
</div>
