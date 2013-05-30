<%-- 
    Document   : home
    Created on : 15/05/2013, 20:07:30
    Author     : eugenio
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->
    <head>
        <c:import url='../inc/cabecalho.jsp' />
    </head>
    <body>
        <!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]-->

        <!-- This code is taken from http://twitter.github.com/bootstrap/examples/hero.html -->

        <c:import url='../inc/menu-main.jsp' />

        <div class="container">

            <!-- Main hero unit for a primary marketing message or call to action -->
            <div class="hero-unit">

                <form>
                    <div id="tabs">
                        <ul>
                            <li><a href="#tabs-1">Localização</a></li>
                        </ul>
                        <div id="tabs-1">  
                            <input type="hidden" name="id" value="${localizacao.id}" /> 
                            <input class="span2" type="text"
                                   name="descricao" placeholder="Descrição"
                                   value="${localizacao.descricao}"/> 
                            <div>
                                <button type="button" class="btn" id="btn-salvar">Salvar</button>
                                <c:if test="${not empty localizacao.id}">
                                    <button type="button" class="btn" id="btn-novo">Novo</button>
                                    <button type="button" class="btn" id="btn-excluir">Excluir</button>
                                </c:if>
                                <button type="button" class="btn" id="btn-listar">Listar</button>
                            </div>
                        </div> <!-- fim tabs -->
                        <input type="hidden" id="tabIndex" name="tabIndex" value="${tabIndex}" />
                </form>
                <div id="dialog-confirm"></div>
            </div>

            <hr>

            <footer>
                <p>
                    <c:import url='../inc/rodape.jsp' />
                </p>
            </footer>

        </div>
        <!-- /container -->

        <c:import url="../inc/footer-bootstrap.jsp" />

        <script>
            $(document).ready(function() {
                
                $("#tabs").tabs({active: ${tabIndex}});


                $("#btn-salvar").click(function() {
                    document.forms[0].action = '<c:url value="/localizacao/salvar" />';
                    document.forms[0].method = 'post';
                    var indice = $("#tabs").tabs("option", "active");
                    document.getElementById('tabIndex').value = indice;
                    document.forms[0].submit();
                });

                $("#btn-novo").click(function() {
                    window.location.assign('<c:url value="/localizacao/novo" />');
                });

            <c:if test="${not empty localizacao.id}">
                    $("#btn-excluir").click(function() {
                        excluir('o localizacao ${localizacao.descricao}', '<c:url value="/localizacao/excluir/${localizacao.id}" />');

                    });
            </c:if>

                    $("#btn-listar").click(function() {
                        window.location.assign('<c:url value="/localizacao/listar" />');
                    });

                    show_message('${tipoMensagem}', '${mensagem}');

                });
        </script>

    </body>
</html>
