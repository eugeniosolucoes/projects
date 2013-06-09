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
                            <input class="span2" type="text" id="cep" name="destinatario.endereco.cep" placeholder="CEP" value="${transportelocal.destinatario.endereco.cep}" onkeypress="return(maskEvent(this, '##.###-###', event));" /> 
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
                            <input class="span2" id="telefoneResidencial" type="text" name="destinatario.telefoneResidencial" placeholder="Telefone residencial" value="${transportelocal.destinatario.telefoneResidencial}"/> 
                            <input class="span2" id="telefoneComercial" type="text" name="destinatario.telefoneComercial" placeholder="Telefone comercial" value="${transportelocal.destinatario.telefoneComercial}"/> 
                            <input class="span2" id="telefoneMovel" type="text" name="destinatario.telefoneMovel" placeholder="Telefone móvel" value="${transportelocal.destinatario.telefoneMovel}"/> 

                        </div>
                        <div id="tabs-3">
                            <div>Motorista</div>
                            <select class="input-block-level" name="_motorista">
                                <c:forEach items="${motoristas}" var="motorista">
                                    <option value="${motorista.id}" ${motorista.id == transportelocal.motorista.id ? 'selected' : ''} >${motorista.nome}</option>
                                </c:forEach>
                            </select>
                            <div>Veículo</div>
                            <select class="input-block-level" name="_veiculoDeTransporte">
                                <c:forEach items="${veiculos}" var="veiculo">
                                    <option value="${veiculo.id}" ${veiculo.id == transportelocal.veiculoDeTransporte.id ? 'selected' : ''} >Placa: ${veiculo.placa} - Marca/Modelo:  ${veiculo.marca}/${veiculo.modelo}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div id="tabs-4">
                            <div>Inventariante</div>
                            <select id="inventariantes-disponiveis" size="10" name="_inventariante" style="width: 100%" >
                                <c:forEach var="autonomo" items="${ajudantes}">
                                    <option value="${autonomo.id}" ${autonomo.id == transportelocal.inventariante.id ? 'selected' : ''}>${autonomo.nome}</option>
                                </c:forEach>
                            </select>
                            <button type="button" class="btn-block" id="btn-adicionar">Adicionar</button>
                            <div>Ajudantes Selecionados</div>
                            <select id="ajudantes-selecionados" name="ajudantes_selecionados" size="10" style="width: 100%" multiple="multiple">
                                <c:forEach var="autonomo" items="${transportelocal.ajudantes}">
                                    <option value="${autonomo.id}">${autonomo.nome}</option>
                                </c:forEach>
                            </select>
                            <button type="button" class="btn-block" id="btn-remover" >Remover</button>
                        </div>
                        <div id="tabs-5"> 
                            <div class="form-inline">
                                <div class="control-group">
                                    <label>Hora da saída:</label>
                                    <input class="span1"
                                           type="text" id="_saida"  name="_saida" 
                                           value="<fmt:formatDate pattern="HH:mm" type="time" value="${transportelocal.saida}" />" />
                                    <label>Hora da chegada no cliente:</label>
                                    <input class="span1"
                                           type="text" id="_chegadaCliente"  name="_chegadaCliente" 
                                           value="<fmt:formatDate pattern="HH:mm" value="${transportelocal.chegadaCliente}" />" />
                                    <label>Hora de saída do cliente:</label>
                                    <input class="span1"
                                           type="text" id="_saidaCliente" name="_saidaCliente" 
                                           value="<fmt:formatDate pattern="HH:mm" value="${transportelocal.saidaCliente}" />" />
                                    <label>Hora de retorno:</label>
                                    <input class="span1"
                                           type="text" id="_retorno" name="_retorno" 
                                           value="<fmt:formatDate pattern="HH:mm" value="${transportelocal.retorno}" />" />
                                </div>
                                <div class="control-group">
                                    <label>Preço por hora:</label>
                                    <input class="span2"
                                           type="text" id="_precoPorHora"  name="_precoPorHora" 
                                           value="<fmt:formatNumber maxFractionDigits="2" type="number" 
                                                             minFractionDigits="2"
                                                             value="${transportelocal.precoPorHora}" />" 
                                           onkeypress="return(currencyFormat(this,'.',',',event))"
                                           style="text-align: right;" />
                                    <label>Mínimo de horas:</label>
                                    <input class="span2"
                                           type="text"  name="minimoDeHoras" 
                                           value="${transportelocal.minimoDeHoras}" 
                                           style="text-align: right;" />
                                    <label>Preço por cada caixa:</label>
                                    <input class="span2"
                                           type="text"  name="_precoPorCaixa" 
                                           value="<fmt:formatNumber maxFractionDigits="2" type="number" 
                                                             minFractionDigits="2"
                                                             value="${transportelocal.precoPorCaixa}" />" 
                                           onkeypress="return(currencyFormat(this,'.',',',event))"
                                           style="text-align: right;" />
                                </div>
                                <div class="control-group">
                                    <label>Preço de retorno:</label>
                                    <input class="span2"
                                           type="text"  name="_precoRetorno" 
                                           value="<fmt:formatNumber maxFractionDigits="2" type="number" 
                                                             minFractionDigits="2"
                                                             value="${transportelocal.precoRetorno}" />"
                                           onkeypress="return(currencyFormat(this,'.',',',event))"
                                           style="text-align: right;" />
                                </div>
                            </div>
                        </div>
                        <div id="tabs-6">
                            <div class="form-inline">
                                <div class="control-group">
                                    <label for="dataDoServico">Data do Serviço:</label>
                                    <input class="span2"
                                           type="text" id="dataDoServico" name="dataDoServico" placeholder="Data do Serviço"
                                           value="<fmt:formatDate pattern="dd/MM/yyyy" value="${transportelocal.dataDoServico}" />" />
                                    <label for="total">Total:</label>
                                    <input class="span2"
                                           type="text" id="_total" name="_total" placeholder="Total"
                                           value="<fmt:formatNumber maxFractionDigits="2" type="number" 
                                                             minFractionDigits="2"
                                                             value="${transportelocal.total}" />"
                                           onkeypress="return(currencyFormat(this,'.',',',event))"
                                           style="text-align: right;" />
                                </div>
                            </div>
                            <label for="observacoes">Observações</label>
                            <textarea name="observacoes" class="input-block-level">${transportelocal.observacoes}</textarea>                            
                        </div>                        
                        <div style="padding: 0 0 10px 25px;">
                            <button type="button" class="btn" id="btn-salvar">Salvar</button>
                            <c:if test="${not empty transportelocal.id}">
                                <button type="button" class="btn" id="btn-novo">Novo</button>
                                <button type="button" class="btn" id="btn-imprimir">Imprimir</button>
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
                
                $("#dataDoServico").datepicker({
                    buttonText: "data do serviço",
                    showButtonPanel: true,
                    changeMonth: true,
                    changeYear: true
                });                
                
                $("#cep").mask("99.999-999");
                
                $("#_saida").mask("99:99");
                
                $("#_chegadaCliente").mask("99:99");
                
                $("#_saidaCliente").mask("99:99");
                
                $("#_retorno").mask("99:99");
                
                $("#telefoneResidencial").mask("(99)9999-9999");
                
                $("#telefoneComercial").mask("(99)9999-9999");
                
                $("#telefoneMovel").mask("(99)9999-9999");
                
                $("#tabs").tabs({active: ${tabIndex}});

                $("#btn-salvar").click(function() {
                    document.forms[0].action = '<c:url value="/transportelocal/salvar" />';
                    document.forms[0].method = 'post';
                    var indice = $("#tabs").tabs("option", "active");
                    document.getElementById('tabIndex').value = indice;
                    $('#ajudantes-selecionados option').prop('selected', 'selected');
                    document.forms[0].submit();
                });

                $("#btn-novo").click(function() {
                    window.location.assign('<c:url value="/transportelocal/novo" />');
                });

                $("#btn-imprimir").click(function() {
                    window.open('<c:url value="/transportelocal/imprimir/${transportelocal.id}" />', '_blank');

                });

                $("#btn-excluir").click(function() {
                    excluir('o Transporte Local:  ${transportelocal.cliente.nome} -> ${transportelocal.destinatario.nome} ', '<c:url value="/transportelocal/excluir/${transportelocal.id}" />');

                });

                $("#btn-listar").click(function() {
                    window.location.assign('<c:url value="/transportelocal/listar" />');
                });

                $("#btn-adicionar").click(function() {
                    var itens = $('#inventariantes-disponiveis').find("option");
                    if(itens.length > 1) {
                        var selecionados = $('#inventariantes-disponiveis').find(":selected");
                        $('#ajudantes-selecionados').append(selecionados);
                    }
                });
                
                $("#btn-remover").click(function() {
                    var selecionados = $('#ajudantes-selecionados').find(":selected");
                    $('#inventariantes-disponiveis').append(selecionados);
                });                

                show_message('${tipoMensagem}', '${mensagem}');

            });
        </script>

    </body>
</html>
