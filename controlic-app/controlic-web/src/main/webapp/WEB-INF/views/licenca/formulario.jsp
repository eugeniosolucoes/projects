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

                <form class="form-inline">
                    <input type="hidden" name="id" value="${licenca.id}" />
                    <label for="tipo">Tipo da Licença:</label>
                    <select name="tipo">
                        <c:forEach items="${tipos}" var="tipo" >
                            <option value="${tipo}" ${tipo eq licenca.tipo? 'selected' : ''} >${tipo.descricao}</option>
                        </c:forEach>
                    </select>
                    <label for="dataLicenca">Data da Licença:</label>
                    <input class="span2"
                           type="text" id="data_licenca" name="dataLicenca" placeholder="Data da Licença"
                           value="<fmt:formatDate pattern="dd/MM/yyyy" value="${licenca.dataLicenca}" />" /> 
                    <label for="publicadaPD">Publicada no PD:</label>
                    <select class="span1" name="publicadaPD">
                        <option value="TRUE" ${ licenca.publicadaPD ? 'selected' : ''} >SIM</option>
                        <option value="FALSE" ${ licenca.publicadaPD ? '' : 'selected'} >NÃO</option>
                    </select>
                    <br/>
                    <label for="motivo">Motivo:</label>
                    <textarea name="motivo" class="input-block-level">${licenca.motivo}</textarea>
                    <button type="button" class="btn" id="btn-salvar">Salvar</button>
                    <c:if test="${not empty licenca.id}">
                        <button type="button" class="btn" id="btn-novo">Novo</button>
                        <button type="button" class="btn" id="btn-excluir">Excluir</button>
                    </c:if>
                    <button type="button" class="btn" id="btn-listar">Listar</button>
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
                
                var dateToday = new Date();
                
                $("#data_licenca").datepicker({
                    buttonText: "data da licença",
                    showButtonPanel: true,
                    changeMonth: true,
                    changeYear: true,
                    minDate: dateToday                 
                });

                $("#btn-salvar").click(function() {
                    document.forms[0].action = '<c:url value="/licenca/salvar" />';
                    document.forms[0].method = 'post';
                    document.forms[0].submit();
                });

                $("#btn-novo").click(function() {
                    window.location.assign('<c:url value="/licenca/novo" />');
                });

                $("#btn-excluir").click(function() {
                    excluir('a esta licença', '<c:url value="/licenca/excluir/${licenca.id}" />');

                });

                $("#btn-listar").click(function() {
                    window.location.assign('<c:url value="/licenca/listar" />');
                });

                show_message('${tipoMensagem}', '${mensagem}');

            });
        </script>

    </body>
</html>
