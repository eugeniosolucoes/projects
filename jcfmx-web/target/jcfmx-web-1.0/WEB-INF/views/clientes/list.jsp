<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<c:import url="../inc/header.jsp"  />

<form class="form-horizontal" role="form" action="<c:url value='/clientes' />" method="post">
    <input type="hidden" name="id" value="${cliente.id}" />
    <div class="form-group">
        <label for="nome" class="col-sm-2 control-label">Nome</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="nome" name="nome" 
                   placeholder="Nome" value="${cliente.nome}" />
        </div>
    </div>
    <div class="form-group">
        <label for="email" class="col-sm-2 control-label">Email</label>
        <div class="col-sm-10">
            <input type="email" class="form-control" id="email" name="email" 
                   placeholder="Email" value="${cliente.email}" />
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="button" id="btn-cliente-novo" class="btn btn-default">Novo</button>
            <button type="submit" class="btn btn-default">Salvar</button>
        </div>
    </div>
</form>
<form>
    <table class="table table-bordered">
        <c:forEach var="cliente" items="${clientes}" >
            <tr>
                <td>${cliente.nome} <br/>
                    <c:forEach var="conta" items="${cliente.contas}" >
                        <c:out value="${conta}" />
                    </c:forEach>
                </td>
                <td>${cliente.email}</td>
                <td style="width: 9em;" >
                    <button type="submit" class="btn btn-default btn-lg" 
                            formaction="<c:url value='/clientes/${cliente.id}' />" >
                        <span class="glyphicon glyphicon-edit"></span>
                    </button>
                    <button type="button" class="btn btn-default btn-lg" 
                            onclick="javascript:excluir('<c:url value="/clientes/${cliente.id}" />')" >
                        <span class="glyphicon glyphicon-trash"></span>
                    </button>
                </td>
            </tr>
        </c:forEach>
    </table>
</form>
<c:import url="../inc/scripts-footer.jsp"  />

<script>
    $(document).ready(function() {
        $('#btn-cliente-novo').click(function() {
            rest_call('get', '<c:url value="/clientes" />');
        });
        show_message('${tipoMensagem}', '${mensagem}');
    });
</script> 

<c:import url="../inc/html-footer.jsp"  />