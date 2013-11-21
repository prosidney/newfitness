<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="layout" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" 	 uri="http://java.sun.com/jsp/jstl/core"%>
<layout:page title="New Fitness" description="Inclusão de novo Aluno" keywords="amazing, app, New Fitness" user="${sessionScope.usuarioLogado.nome}">
    <jsp:attribute name="extraHeader">
    	<style>
			.error {
				color: #ff0000;
			}
			 
			.errorblock {
				color: #000;
				background-color: #ffEEEE;
				border: 3px solid #ff0000;
				padding: 8px;
				margin: 16px;
			}
    	</style>
		<script type="text/javascript">
		    $(function() {
			    $( "#datepickerExpirationDate" ).datepicker({ dateFormat: "dd/mm/yy" });
			    $( "#datepickerDtPayment" ).datepicker({ dateFormat: "dd/mm/yy" });
			});
		 </script>
    </jsp:attribute>
    <jsp:body>
		<center>
			<h4>Cadastro de pagamentos</h4>
			<form:form method="post" action="savePayment.do" id="form" modelAttribute="payment" class="navbar-form pull-center">
				<table>
					<form:hidden path="id"/>
					<input type="hidden" name="mat" value="${payment.aluno.matricula}"/>
					<tr>
						<td>Valor:</td>
						<td><form:input path="amount" class="span3" style="width: 80px" onkeyup="enforceNumericValue(this)"/></td>
						<td><form:errors path="amount" cssClass="error" /></td>
					</tr>
					<tr>
						<td>Data de vencimento:</td>
						<td><form:input id="datepickerExpirationDate" path="expirationDate" /></td>
						<td><form:errors path="expirationDate" cssClass="error" /></td>
					</tr>																
					<tr>
						<td>Data do pagamento:</td>
						<td><form:input id="datepickerDtPayment" path="dtPayment"/></td>
						<td><form:errors path="dtPayment" cssClass="error" /></td>
					</tr>
					<tr>
						<td>Tipo do pagamento:</td>
						<td><form:select path="paymentType" items="${paymentTypesList}"/></td>
						<td><form:errors path="paymentType" cssClass="error" /></td>
					</tr>
					<tr>
						<td>Observação:</td>
						<td><form:textarea path="observation"/></td>
						<td><form:errors path="observation" cssClass="error" /></td>
					</tr>
					<tr>
						<td colspan="2">
						<center>
							<a class="btn" href="#" onclick="document.forms[0].submit()"><i class="icon-ok icon-black"></i> Salvar </a>
							<a class="btn" href="<c:url value="viewPaymentsByMat.do?mat=${payment.aluno.matricula}"/>"><i class="icon-backward icon-black"></i> Voltar </a>
						</center>
						</td>
					</tr>				
				</table>
			</form:form>
		</center>
    </jsp:body>
</layout:page>