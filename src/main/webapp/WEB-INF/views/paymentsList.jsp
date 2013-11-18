<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="layout" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" 	 uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<layout:page title="New Fitness" description="Home" keywords="amazing, app, New Fitness" user="Admin">
    <jsp:body>
        <center>
			<table class="table table-hover" style="width: 50%">
				<tr>
					<th colspan="6" style="text-align: center;" style="width: 80%"><c:out value="Pagamentos" /></th>
				</tr>
				<tr>
					<th><c:out value="Nome" /></th>
					<th><c:out value="Valor" /></th>
					<th><c:out value="Data do Vencimento" /></th>
					<th><c:out value="Data do Pagamento" /></th>
					<th><c:out value="Tipo de pagamento" /></th>
					<th><c:out value="Ações" /></th>
				</tr>
				<c:set var="total" scope="request" value="${0}"/>
				<c:forEach var="payment" items="${payments}">
						<c:choose>
							<c:when test="${payment.dtPayment != null}">
							  	<tr class="success">
							</c:when>
							<c:otherwise>
								<tr class="warning">
							</c:otherwise>						
						</c:choose>
						<td style="width: 20%"><c:out value="${payment.aluno.nome}" /></td>
						<td style="width: 5%"><c:out value="${payment.amount}" /></td>
						<td><fmt:formatDate pattern="dd/MM/yyyy" value="${payment.expirationDate}" /></td>
						<td><fmt:formatDate pattern="dd/MM/yyyy" value="${payment.dtPayment}" /></td>
						<td><c:out value="${payment.paymentType}" /></td>
						<td>
							<a class="btn" href="<c:url value="addPayment.do?payId=${payment.id}&mat=${payment.aluno.matricula}"/>"><i class="icon-edit icon-black" ></i> </a>
						</td>						
					</tr>
					<c:set var="total" scope="request" value="${total + payment.amount}"/>
				</c:forEach>
				<tr>
					<th colspan="7" style="text-align: center;" style="width: 80%"><c:out value="Total a pagar= ${total} R$" /></th>
					<th colspan="7" style="text-align: center;" style="width: 80%"><c:out value="Total pago= ${total} R$" /></th>
				</tr>
			</table>
			<a class="btn" href="<c:url value="addPayment.do?mat=${mat}"/>"><i class="icon-plus-sign icon-black"></i> Novo Pagamento </a>
			<a class="btn" href="admin"><i class="icon-backward icon-black"></i> Voltar </a>
		</center>        
    </jsp:body>
</layout:page>