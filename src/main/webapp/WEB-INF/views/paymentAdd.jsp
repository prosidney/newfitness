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
			function enforceNumericValue(obj){  
				 //  check for valid numeric strings	
				   {
					   var strValidChars = "0123456789";
					   var strChar;
					   var blnResult = true;
		
					  // if (strString.length == 0) return false;
		
					   //  test strString consists of valid characters listed above
					   for (i = 0; i < obj.value.length && blnResult == true; i++){
					      strChar = obj.value.charAt(i);
					      if (strValidChars.indexOf(strChar) == -1){
						 blnResult = false;
						 obj.value = obj.value.substring(0, obj.value.length-1);
					      }
					   }
					   //return blnResult;
				   }
		  	}	
			
		    $(function() {
			    $( "#datepicker" ).datepicker( "option", "dateFormat", "dd/mm/yy" );
			    $( "#datepicker" ).datepicker();
			    $j("#datepicker").datepicker({altField: '#expiration', altFormat: 'dd/mm/yy'});
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
						<form:hidden id="expiration" path="expirationDate" />					
						<td><input type="text" id="datepicker"></td>
						<td><form:errors path="expirationDate" cssClass="error" /></td>
					</tr>																
					<tr>
						<td>Data do pagamento:</td>
						<td><form:input id="datepicker" path="dtPayment" class="span3" style="width: 80px" maxlength="10" /></td>
						<td><form:errors path="dtPayment" cssClass="error" /></td>
					</tr>
					<tr>
						<td>Tipo do pagamento:</td>
						<td><form:select path="paymentType" items="${paymentTypesList}"/></td>
						<td><form:errors path="paymentType" cssClass="error" /></td>
					</tr>
					<tr>
						<td colspan="2">
						<center>
							<a class="btn" href="#" onclick="document.forms[0].submit()"><i class="icon-ok icon-black"></i> Salvar </a>
							<a class="btn" href="<c:url value="viewPayments.do?mat=${payment.aluno.matricula}"/>"><i class="icon-backward icon-black"></i> Voltar </a>
						</center>
						</td>
					</tr>				
				</table>
			</form:form>
		</center>
    </jsp:body>
</layout:page>