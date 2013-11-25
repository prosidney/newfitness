<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="layout" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" 	 uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<layout:page title="New Fitness" description="Home" keywords="amazing, app, New Fitness" user="Admin">
    <jsp:attribute name="extraHeader">
		<script type="text/javascript">
		
		</script>
    </jsp:attribute>
    <jsp:body>
    	<jsp:useBean id="now" class="java.util.Date" />
		<fmt:formatDate var="year" value="${now}" pattern="yyyy" />
        <center>
			<form method="post" action="generatePaymentsYear.do">
        		<table class="table" style="width: 30%">
	        		<tr>	
						<td><c:out value="Gerar pagamentos" /></td>
						<td>
							<select id="year" name="year">
								<option value="${year}" label="${year}"/>
								<option value="${year + 1}" label="${year + 1}" selected="selected"/>
							</select>
						</td>
						<td><input type="hidden" name="mat" value="${mat}" /></td>
						<td> 
							<a class="btn" href="#" onclick="document.forms[1].submit()"> <i class="icon-plus-sign icon-black"></i> Gerar </a> 
						</td>
					</tr>
        		</table>
        	</form>
			<table class="table table-hover" style="width: 85%">
				<tr>
					<th colspan="6" style="text-align: center;"><c:out value="Detalhes" /></th>
				</tr>
				<tr>
					<th style="width: 40%"><c:out value="Nome" /></th>
					<th style="width: 5%"><c:out value="Valor" /></th>
					<th style="width: 15%"><c:out value="Data do Vencimento" /></th>
					<th style="width: 15%"><c:out value="Data do Pagamento" /></th>
					<th style="width: 15%"><c:out value="Tipo de pagamento" /></th>
					<th><c:out value="Ações" /></th>
				</tr>
				<c:set var="totalPaid" scope="request" value="${0}"/>
				<c:set var="totalNotPaid" scope="request" value="${0}"/>
				<c:forEach var="payment" items="${payments}">
					<c:choose>
						<c:when test="${payment.dtPayment != null}">
						  	<tr class="success">
						</c:when>
						<c:otherwise>
							<tr class="warning">
						</c:otherwise>						
					</c:choose>
						<td><c:out value="${payment.aluno.nome}" /></td>
						<td><c:out value="${payment.amount}" /></td>
						<td><fmt:formatDate pattern="dd/MM/yyyy" value="${payment.expirationDate}" /></td>
						<td><fmt:formatDate pattern="dd/MM/yyyy" value="${payment.dtPayment}" /></td>
						<td><c:out value="${payment.paymentType}" /></td>
						<td>
							<a class="btn" href="<c:url value="addPayment.do?payId=${payment.id}&mat=${payment.aluno.matricula}"/>"><i class="icon-edit icon-black" ></i> </a>
						</td>						
					</tr>
					<c:choose>
						<c:when test="${payment.dtPayment != null}">
							<c:set var="totalPaid" scope="request" value="${totalPaid + payment.amount}"/>
						</c:when>
						<c:otherwise>
							<c:set var="totalNotPaid" scope="request" value="${totalNotPaid + payment.amount}"/>
						</c:otherwise>						
					</c:choose>					
				</c:forEach>
				<tr>
					<th colspan="4" style="text-align: right;" style="width: 50%">
						<c:out value="Total a pagar = " />
					</th>
					<th colspan="3" style="text-align: left;" style="width: 30%">
						<c:out value="${totalNotPaid} R$" />
					</th>
				</tr>
				<tr>
					<th colspan="4" style="text-align: right;" style="width: 50%">
						<c:out value="Total pago = " />
					</th>				
					<th colspan="3" style="text-align: left;" style="width: 30%">
						<c:out value="${totalPaid} R$" />
					</th>
				</tr>
			</table>
			<a class="btn" href="admin"><i class="icon-backward icon-black"></i> Voltar </a>
		</center>        
    </jsp:body>
</layout:page>