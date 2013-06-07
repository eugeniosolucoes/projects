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
                            <li><a href="#tabs-1">Autonomo</a></li>
                            <li><a href="#tabs-2">Endereço</a></li>
                            <li><a href="#tabs-3">Telefones</a></li>
                        </ul>
                        <div id="tabs-1">
                            <input type="hidden" name="id" value="${autonomo.id}" /> 
                            <input class="input-block-level" type="text" name="nome"
                                   placeholder="Nome" value="${autonomo.nome}" /> 
                            <input class="span2" type="text" id="cpf" name="cpf" placeholder="CPF" value="${autonomo.cpf}" /> 
                            <input class="span2" type="text"
                                   name="identidade" placeholder="Identidade"
                                   value="${autonomo.identidade}"/> 
                            <input class="span2"
                                   type="text" id="admissao" name="admissao" placeholder="Admissão"
                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${autonomo.admissao}" />" /> 
                            <input class="span2"
                                   type="text" id="demissao" name="demissao" placeholder="Demissão"
                                   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${autonomo.demissao}" />" />
                            <label for="descricao">Descrição</label>
                            <textarea name="descricao" class="input-block-level">${autonomo.descricao}</textarea>
                        </div>
                        <div id="tabs-2">
                            <input type="hidden" name="endereco.id" value="${autonomo.endereco.id}" /> 
                            <input class="input-block-level" type="text" name="endereco.logradouro" placeholder="Logradouro" value="${autonomo.endereco.logradouro}" />
                            <input class="span2" type="text" name="endereco.numero" placeholder="Número" value="${autonomo.endereco.numero}" />
                            <input class="span2" type="text" name="endereco.complemento" placeholder="Complemento" value="${autonomo.endereco.complemento}" />
                            <input class="span2" type="text" id="cep" name="endereco.cep" placeholder="CEP" value="${autonomo.endereco.cep}"  /> 
                            <input class="span2" type="text" name="endereco.bairro" placeholder="Bairro" value="${autonomo.endereco.bairro}" /> 
                            <input class="span2" type="text" name="endereco.cidade" placeholder="Cidade" value="${autonomo.endereco.cidade}"/> 
                            <select name="endereco.estado">
                                <c:choose>
                                    <c:when test="${not empty autonomo.endereco}" >
                                        <c:forEach items="${estados}" var="estado" >
                                            <option value="${estado}" ${estado == autonomo.endereco.estado ? 'selected' : ''} >${estado}</option>
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
                            <label for="telefoneResidencial">Residencial</label>
                            <input id="telefoneResidencial" name="telefoneResidencial"  value="${autonomo.telefoneResidencial}" />
                            <label for="telefoneMovel">Móvel</label>
                            <input id="telefoneMovel" name="telefoneMovel"  value="${autonomo.telefoneMovel}"  />
                            <label for="telefoneComercial">Comercial</label>
                            <input id="telefoneComercial" name="telefoneComercial"  value="${autonomo.telefoneComercial}"  />
                        </div>
                        <div style="padding: 0 0 10px 25px;">
                            <button type="button" class="btn" id="btn-salvar">Salvar</button>
                            <c:if test="${not empty autonomo.id}">
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
                $("#admissao").datepicker({
                    buttonText: "data da admissão",
                    showButtonPanel: true,
                    changeMonth: true,
                    changeYear: true
                });

                $("#demissao").datepicker({
                    buttonText: "data da demissão",
                    showButtonPanel: true,
                    changeMonth: true,
                    changeYear: true
                });
                
                $("#cpf").mask("99.999.999-99");
                
                $("#cep").mask("99.999-999");
                
                $("#telefoneResidencial").mask("(99)9999-9999");
                
                $("#telefoneMovel").mask("(99)9999-9999");
                
                $("#telefoneComercial").mask("(99)9999-9999");

                $("#tabs").tabs({active: ${tabIndex}});

                $("#btn-salvar").click(function() {
                    document.forms[0].action = '<c:url value="/autonomo/salvar" />';
                    document.forms[0].method = 'post';
                    var indice = $("#tabs").tabs("option", "active");
                    document.getElementById('tabIndex').value = indice;
                    document.forms[0].submit();
                });

                $("#btn-novo").click(function() {
                    window.location.assign('<c:url value="/autonomo/novo" />');
                });

                $("#btn-excluir").click(function() {
                    excluir('o autonomo ${autonomo.nome}', '<c:url value="/autonomo/excluir/${autonomo.id}" />');

                });

                $("#btn-listar").click(function() {
                    window.location.assign('<c:url value="/autonomo/listar" />');
                });

                show_message('${tipoMensagem}', '${mensagem}');

            });
        </script>

    </body>
</html>
