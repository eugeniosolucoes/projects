<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>DataTables example</title>
		<style type="text/css" title="currentStyle">
			@import "js/css/demo_page.css";
			@import "js/css/demo_table.css";
		</style>
		<script type="text/javascript" language="javascript" src="js/js/jquery.js"></script>
		<script type="text/javascript" language="javascript" src="js/js/jquery.dataTables.js"></script>
		<script type="text/javascript" charset="utf-8">
			$(document).ready(function() {
				$('#example').dataTable( {
                                        "sPaginationType": "full_numbers",
					"bProcessing": true,
					"bServerSide": true,
					"sAjaxSource": "dados"
				} );
			} );
		</script>
    </head>
    <body>
        <div id="dynamic">
            <table cellpadding="0" cellspacing="0" border="0" class="display" id="example">
                <thead>
                    <tr>
                        <th width="20%">Rendering engine</th>
                        <th width="25%">Browser</th>
                        <th width="25%">Platform(s)</th>
                        <th width="15%">Engine version</th>
                        <th width="15%">CSS grade</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td colspan="5" class="dataTables_empty">Loading data from server</td>
                    </tr>
                </tbody>
                <tfoot>
                    <tr>
                        <th>Rendering engine</th>
                        <th>Browser</th>
                        <th>Platform(s)</th>
                        <th>Engine version</th>
                        <th>CSS grade</th>
                    </tr>
                </tfoot>
            </table>
        </div>

    </body>
</html>
