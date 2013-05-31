<%-- 
    Document   : home
    Created on : 15/05/2013, 20:07:30
    Author     : eugenio
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>

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
                    Transporte Local
                    <div id="tabs">
                        <ul>
                            <li><a href="#tabs-1">Remetente</a></li>
                            <li><a href="#tabs-2">Destinatário</a></li>
                            <li><a href="#tabs-3">Veículo</a></li>
                            <li><a href="#tabs-4">Equipe de trabalho</a></li>
                            <li><a href="#tabs-5">Especificações</a></li>
                            <li><a href="#tabs-6">Observações</a></li>
                        </ul>
                        <div id="tabs-1">
                            <input type="hidden" name="id" value="${transportelocal.id}" /> 
                            <select class="input-block-level" name="_cliente">
                                <c:forEach items="${clientes}" var="cliente">
                                    <option value="${cliente.id}" ${cliente.id == transportelocal.cliente.id ? 'selected' : ''} >${cliente.nome}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div id="tabs-2"> 
                            <input type="hidden" name="destinatario.id" value="${transportelocal.destinatario.id}" /> 
                            <input class="input-block-level" type="text" name="destinatario.nome"
                                   placeholder="Nome" value="${transportelocal.destinatario.nome}" /> 
                            <input type="hidden" name="destinatario.endereco.id" value="${destinatario.endereco.id}" /> 
                            <input class="input-block-level" type="text" name="destinatario.endereco.logradouro" placeholder="Logradouro" value="${transportelocal.destinatario.endereco.logradouro}" />
                            <input class="span2" type="text" name="destinatario.endereco.numero" placeholder="Número" value="${transportelocal.destinatario.endereco.numero}" />
                            <input class="span2" type="text" name="destinatario.endereco.complemento" placeholder="Complemento" value="${transportelocal.destinatario.endereco.complemento}" />
                            <input class="span2" type="text" name="destinatario.endereco.cep" placeholder="CEP" value="${transportelocal.destinatario.endereco.cep}" onkeypress="return(maskEvent(this, '##.###-###', event));" /> 
                            <input class="span2" type="text" name="destinatario.endereco.bairro" placeholder="Bairro" value="${transportelocal.destinatario.endereco.bairro}" /> 
                            <input class="span2" type="text" name="destinatario.endereco.cidade" placeholder="Cidade" value="${transportelocal.destinatario.endereco.cidade}"/> 
                            <select name="destinatario.endereco.estado">
                                <c:choose>
                                    <c:when test="${not empty transportelocal.destinatario.endereco}" >
                                        <c:forEach items="${estados}" var="estado" >
                                            <option value="${estado}" ${estado == transportelocal.destinatario.endereco.estado ? 'selected' : ''} >${estado}</option>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach items="${estados}" var="estado" >
                                            <option value="${estado}" ${estado == 'RJ' ? 'selected' : ''} >${estado}</option>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                        </div>
                        <div id="tabs-3">
                            <select class="input-block-level" name="_veiculoDeTransporte">
                                <c:forEach items="${veiculos}" var="veiculo">
                                    <option value="${veiculo.id}" ${veiculo.id == transportelocal.veiculoDeTransporte.id ? 'selected' : ''} >Placa: ${veiculo.placa} - Marca/Modelo:  ${veiculo.marca}/${veiculo.modelo}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div id="tabs-4">
                            Equipe de trabalho
                        </div>
                        <div id="tabs-5">  
                            Especificações
                        </div>
                        <div id="tabs-6">
                            Observações
                        </div>                        
                        <div style="padding: 0 0 10px 25px;">
                            <button type="button" class="btn" id="btn-salvar">Salvar</button>
                            <c:if test="${not empty transportelocal.id}">
                                <button type="button" class="btn" id="btn-novo">Novo</button>
                                <button type="button" class="btn" id="btn-excluir">Excluir</button>
                            </c:if>
                            <button type="button" class="btn" id="btn-listar">Listar</button>
                        </div>  <!-- fim tabs -->
                        <input type="hidden" id="tabIndex" name="tabIndex" value="${tabIndex}" />

                    </div>
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
                    document.forms[0].action = '<c:url value="/transportelocal/salvar" />';
                    document.forms[0].method = 'post';
                    var indice = $("#tabs").tabs("option", "active");
                    document.getElementById('tabIndex').value = indice;
                    document.forms[0].submit();
                });

                $("#btn-novo").click(function() {
                    window.location.assign('<c:url value="/transportelocal/novo" />');
                });

                $("#btn-excluir").click(function() {
                    excluir('o transportelocal ', '<c:url value="/transportelocal/excluir/${transportelocal.id}" />');

                });

                $("#btn-listar").click(function() {
                    window.location.assign('<c:url value="/transportelocal/listar" />');
                });

                show_message('${tipoMensagem}', '${mensagem}');

            });
        </script>

    </body>
</html>
