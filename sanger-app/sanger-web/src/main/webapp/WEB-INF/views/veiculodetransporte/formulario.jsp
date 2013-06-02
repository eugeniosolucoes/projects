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
                            <li><a href="#tabs-1">Veículo de Transporte</a></li>
                            <li><a href="#tabs-2">Proprietário (Endereço)</a></li>
                            <li><a href="#tabs-3">Proprietário (Telefones)</a></li>
                        </ul>
                        <div id="tabs-1">
                            <input type="hidden" name="id" value="${veiculodetransporte.id}" /> 
                            <input class="span2" type="text" id="placa" name="placa" placeholder="Placa"
                                   value="${veiculodetransporte.placa}" /> 
                            <input class="span2" type="text"
                                   name="cidade" placeholder="Cidade"
                                   value="${veiculodetransporte.cidade}"/>
                            <select name="estado">
                                <c:choose>
                                    <c:when test="${not empty veiculodetransporte}" >
                                        <c:forEach items="${estados}" var="estado" >
                                            <option value="${estado}" ${estado == veiculodetransporte.estado ? 'selected' : ''} >${estado}</option>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach items="${estados}" var="estado" >
                                            <option value="${estado}" ${estado == 'RJ' ? 'selected' : ''} >${estado}</option>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                            <input class="span2" type="text"
                                   name="cor" placeholder="Cor"
                                   value="${veiculodetransporte.cor}"/>
                            <input class="span2" type="text"
                                   name="marca" placeholder="Marca"
                                   value="${veiculodetransporte.marca}"/>
                            <input class="span2" type="text"
                                   name="modelo" placeholder="Modelo"
                                   value="${veiculodetransporte.modelo}"/>
                        </div>
                        <div id="tabs-2">
                            <input type="hidden" name="proprietario.id" 
                                   value="${veiculodetransporte.proprietario.id}" /> 
                            <input class="input-block-level" type="text" 
                                   name="proprietario.nome" placeholder="Nome do proprietário" 
                                   value="${veiculodetransporte.proprietario.nome}" />
                            <input type="hidden" name="proprietario.endereco.id" 
                                   value="${veiculodetransporte.proprietario.endereco.id}" /> 
                            <input class="input-block-level" type="text" 
                                   name="proprietario.endereco.logradouro" 
                                   placeholder="Logradouro" 
                                   value="${veiculodetransporte.proprietario.endereco.logradouro}" />
                            <input class="span2" type="text" name="proprietario.endereco.numero" 
                                   placeholder="Número" value="${veiculodetransporte.proprietario.endereco.numero}" />
                            <input class="span2" type="text" name="proprietario.endereco.complemento" 
                                   placeholder="Complemento" value="${veiculodetransporte.proprietario.endereco.complemento}" />
                            <input class="span2" type="text" id="cep" name="proprietario.endereco.cep" 
                                   placeholder="CEP" value="${veiculodetransporte.proprietario.endereco.cep}"  /> 
                            <input class="span2" type="text" name="proprietario.endereco.bairro" 
                                   placeholder="Bairro" value="${veiculodetransporte.proprietario.endereco.bairro}" /> 
                            <input class="span2" type="text" name="proprietario.endereco.cidade" 
                                   placeholder="Cidade" value="${veiculodetransporte.proprietario.endereco.cidade}"/> 
                            <select name="proprietario.endereco.estado">
                                <c:choose>
                                    <c:when test="${not empty veiculodetransporte.proprietario.endereco}" >
                                        <c:forEach items="${estados}" var="estado" >
                                            <option value="${estado}" ${estado == veiculodetransporte.proprietario.endereco.estado ? 'selected' : ''} >${estado}</option>
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
                            <label for="proprietario.telefoneResidencial">Residencial</label>
                            <input id="telefoneResidencial" name="proprietario.telefoneResidencial"  value="${veiculodetransporte.proprietario.telefoneResidencial}" onkeypress="return(maskEvent(this, '(##)####-####', event));" />
                            <label for="proprietario.telefoneMovel">Móvel</label>
                            <input id="telefoneMovel" name="proprietario.telefoneMovel"  value="${veiculodetransporte.proprietario.telefoneMovel}" onkeypress="return(maskEvent(this, '(##)####-####', event));" />
                            <label for="proprietario.telefoneComercial">Comercial</label>
                            <input id="telefoneComercial" name="proprietario.telefoneComercial"  value="${veiculodetransporte.proprietario.telefoneComercial}" onkeypress="return(maskEvent(this, '(##)####-####', event));" />
                        </div>
                        <div style="padding: 0 0 10px 25px;">
                            <button type="button" class="btn" id="btn-salvar">Salvar</button>
                            <c:if test="${not empty veiculodetransporte.id}">
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

                $("#placa").mask("aaa-9999");
                
                $("#cep").mask("99.999-999");
                
                $("#telefoneResidencial").mask("(99)9999-9999");
                
                $("#telefoneMovel").mask("(99)9999-9999");
                
                $("#telefoneComercial").mask("(99)9999-9999");


                $("#btn-salvar").click(function() {
                    document.forms[0].action = '<c:url value="/veiculodetransporte/salvar" />';
                    document.forms[0].method = 'post';
                    var indice = $("#tabs").tabs("option", "active");
                    document.getElementById('tabIndex').value = indice;
                    document.forms[0].submit();
                });

                $("#btn-novo").click(function() {
                    window.location.assign('<c:url value="/veiculodetransporte/novo" />');
                });

                $("#btn-excluir").click(function() {
                    excluir('o veículo de transporte ${veiculodetransporte.placa}', '<c:url value="/veiculodetransporte/excluir/${veiculodetransporte.id}" />');

                });

                $("#btn-listar").click(function() {
                    window.location.assign('<c:url value="/veiculodetransporte/listar" />');
                });

                show_message('${tipoMensagem}', '${mensagem}');

            });
        </script>

    </body>
</html>
