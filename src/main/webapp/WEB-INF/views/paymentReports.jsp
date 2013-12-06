<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="layout" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" 	 uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<layout:page title="New Fitness" description="Home" keywords="amazing, app, New Fitness" user="Admin">
    <jsp:attribute name="extraHeader">
		<script type="text/javascript">
			// Load the Visualization API and the piechart package.
		    google.load('visualization', '1.0', {'packages':['corechart']});
	
		    // Set a callback to run when the Google Visualization API is loaded.
		    google.setOnLoadCallback(drawChart);
		    
		    // Callback that creates and populates a data table,
		    // instantiates the pie chart, passes in the data and
		    // draws it.
		    function drawChart() {
		    	
		    	var qtPaid = '${requestScope.qtPaid}';
		    	var qtPendent = '${requestScope.qtPendent}';
		    	
				//window.alert(qtPaid + ', ' + qtPendent);			    	
		    	
		        // Create the data table.
		        var data = new google.visualization.DataTable();
		        data.addColumn('string', 'Topping');
		        data.addColumn('number', 'Slices');
		        data.addRows([
		          ['Pagos', parseInt(qtPaid)],
		          ['Pendentes', parseInt(qtPendent)]
		        ]);
	
		        // Set chart options
		        var options = {'title':'Quantidade de parcelas pagas',
		                       'width':400,
		                       'height':300};
	
		        // Instantiate and draw our chart, passing in some options.
		        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
		        chart.draw(data, options);
		    }
		    
			$(document).ready( function() {
				var matId = '${mat}';
				$('#example').dataTable( {
					//"bJQueryUI": true,
					"sDom": "<'row'<'span8'l><'span8'f>r>t<'row'<'span8'i><'span8'p>>",
        			"sPaginationType": "bootstrap",
					"bProcessing": true,
			        "bServerSide": true,
			        "sAjaxSource": "../pages/paymentsReportJson.do",
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
    	<jsp:useBean id="now" class="java.util.Date" />
		<fmt:formatDate var="year" value="${now}" pattern="yyyy" />
        <center>
        	<div id="container" style="width: 80%">
				<!--Div that will hold the pie chart-->
	 			<div id="chart_div" > </div>  
				<%--
				TODO Depois ver a necessidade dessa busca
				<form method="post" action="viewPaymentsReportByMemberName.do">
	        		<table class="table" style="width: 30%">
	        			<tr>
	        				<td colspan="3"><center> <c:out value="Buscar" /> </center></td>
	        			</tr>
		        		<tr>	
							<td><c:out value="Nome" /></td>
							<td><input type="text" name="name"/></td>
							<td> <a class="btn" href="#" onclick="document.forms[0].submit()"><i class="icon-search icon-black"></i> Buscar </a> </td>
						</tr>
	        		</table>
	        	</form> 
	        	--%>
	        	
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
					
				<a class="btn" href="admin"><i class="icon-backward icon-black"></i> Voltar </a>
			</div>
		</center>        
    </jsp:body>
</layout:page>