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

        <link rel="stylesheet" href="<c:url value='/resources/js/datatable/css/demo_page.css' />" >
        <link rel="stylesheet" href="<c:url value='/resources/js/datatable/css/jquery.dataTables.css' />" >
        <script type="text/javascript" language="javascript" src="<c:url value='/resources/js/datatable/js/jquery.js' />"></script>
        <script type="text/javascript" language="javascript" src="<c:url value='/resources/js/datatable/js/jquery.dataTables.min.js' />"></script>
        <script type="text/javascript" src="<c:url value='/resources/js/message/javascript/jquery.toastmessage.js' />"></script>


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
                <select id="anos">
                    <c:forEach items="${anos}" var="vano"   >
                        <option value="${vano}" ${vano eq ano ? 'selected': ''}>${vano}</option>
                    </c:forEach>
                </select>
                <select id="meses">
                    <c:forEach items="${meses}" var="vmes"  >
                        <option value="${vmes[0]}" ${vmes[0] eq mes ? 'selected': ''} >${vmes[1]}</option>
                    </c:forEach>
                </select>
                <table id="tbl_calendar" border='1' ></table>
                <table id="tbl_listagem">
                    <thead>
                        <tr>
                            <th>Data</th>
                            <th>Militar</th>
                            <th>Tipo</th>
                            <th>Motivo</th>
                            <th>PD</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
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

            create_calendar('${url}', ${ano}, ${mes});

            $(document).ready(function() {
                
                populate_table('${url}');
                
                $('#tbl_listagem').dataTable( {
                    "bPaginate": true,
                    "iDisplayLength": 10,
                    "aLengthMenu": [[10, 50, 100], [10, 50, 100]],
                    "oLanguage": {
                        sEmptyTable: "Nenhum resultado",
                        "sInfo": "Resultado _START_ a _END_ de _TOTAL_ ",
                        "sSearch": "Buscar:",
                        "sLengthMenu": 'Registros: _MENU_ ',
                        "sInfoFiltered": "(filtro de _MAX_ total registros)",
                        "sInfoEmpty": "Nenhum resultado",
                        "oPaginate": {
                            "sPrevious": "Anterior",
                            "sNext": "Próxima"
                        }
                    }
                });

                show_message('${tipoMensagem}', '${mensagem}');

                $('#meses').change(function() {
                    var ano = $('#anos').val();
                    var mes = $('#meses').val();
                    window.location.assign('<c:url value="/licenca/visualizar" />' + '/' + ano + '/' + mes);
                });

            });
        </script>

    </body>
</html>
