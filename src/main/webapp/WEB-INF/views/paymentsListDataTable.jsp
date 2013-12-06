<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="layout" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" 	 uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<layout:page title="New Fitness" description="Home" keywords="amazing, app, New Fitness" user="Admin">
    <jsp:attribute name="extraHeader">
		<script type="text/javascript">
			$(document).ready( function() {
				var matId = '${mat}';
				$('#example').dataTable( {
					//"bJQueryUI": true,
					"sDom": "<'row'<'span8'l><'span8'f>r>t<'row'<'span8'i><'span8'p>>",
        			"sPaginationType": "bootstrap",
					"bProcessing": true,
			        "bServerSide": true,
			        "sAjaxSource": "../pages/viewPaymentsByMatJson.do?mat=" + matId,
					"bPaginate": true,
					"bFilter": false,
			        "sPaginationType": "full_numbers",
			        "oLanguage": {
			        	"sProcessing":   "Processando...",
			            "sLengthMenu":   "Mostrar _MENU_ registros",
			            "sZeroRecords":  "Não foram encontrados resultados",
			            "sInfo":         "Mostrando de _START_ até _END_ de _TOTAL_ registros",
			            "sInfoEmpty":    "Mostrando de 0 até 0 de 0 registros",
			            "sInfoFiltered": "(filtrado de _MAX_ registros no total)",
			            "sInfoPostFix":  "",
			            "sSearch":       "Buscar:",
			            "sUrl":          "",
			            "oPaginate": {
			                "sFirst":    "Primeiro",
			                "sPrevious": "Anterior",
			                "sNext":     "Seguinte",
			                "sLast":     "Último"
			            }
			        },
					"aoColumns": [
					  			{ "mDataProp": "id" },
					  			{ "mDataProp": "aluno.nome" },
					  			{ "mDataProp": "amount" },
					  			{ "mDataProp": "expirationDate", 
					  			  	"mRender": function ( data, type, full ) {
		  			  					var d = new Date(data);
		  		        				return d.getUTCDate() + '/' + (d.getUTCMonth() + 1)  + '/' + d.getUTCFullYear();
		  		        			}  
					  			},
					  			{ "mDataProp": "dtPayment" ,
					  			  	"mRender": function ( data, type, full ) {
					  			  		if(data != null){
			  			  					var d = new Date(data);
			  		        				return d.getUTCDate() + '/' + (d.getUTCMonth() + 1)  + '/' + d.getUTCFullYear();
					  			  		} else {
					  			  			return 'Não efetuado';
					  			  		}
		  		        			} 
					  			},
					  			{ "mDataProp": "paymentType" },
					  			{ "mDataProp": "observation" }
					  		]
				} );
			} );
		</script>
    </jsp:attribute>
    <jsp:body>
    	<center>
	    	<div id="container" style="width: 80%">
				<table id="example">
				    <thead>
				        <tr>
				            <th>ID</th>
				            <th style="width: 218px">Nome</th>
				            <th>Valor</th>
				            <th>Data de vencimento</th>
				            <th>Data do pagamento</th>
				            <th>Tipo do Pagamento</th>
				            <th>Observação</th>
				        </tr>
				    </thead>
				    <tbody></tbody>
				</table>        
	    	</div>
    	</center>
    </jsp:body>
</layout:page>