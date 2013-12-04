<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="layout" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" 	 uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<layout:page title="New Fitness" description="Home" keywords="amazing, app, New Fitness" user="Admin">
    <jsp:attribute name="extraHeader">
		<script type="text/javascript">
			$(document).ready( function() {
				$('#example').dataTable( {
					"bJQueryUI": true,
					"bProcessing": true,
			        "bServerSide": true,
			        "sAjaxSource": "../pages/viewPaymentsByMatJson.do?mat=1",
					"bPaginate": true,
			        "sPaginationType": "full_numbers",
					"aoColumns": [
					  			{ "mDataProp": "id" },
					  			{ "mDataProp": "amount" },
					  			{ "mDataProp": "expirationDate" },
					  			{ "mDataProp": "dtPayment" },
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