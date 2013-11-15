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
			    $( "#datepicker" ).datepicker();
			    $( "#datepicker" ).datepicker( "option", "dateFormat", "mm/dd/yy" );
			});
		 </script>
    </jsp:attribute>
    <jsp:body>
		<center>
			<h4>Cadastro do Aluno</h4>
			<form:form method="post" action="saveClient.do" id="form" modelAttribute="client" class="navbar-form pull-center">
				<table>
					<form:hidden path="matricula"/>
					<tr>
						<td>Nome:</td>
						<td><form:input path="nome" class="span3" /></td>
						<td><form:errors path="nome" cssClass="error" /></td>
					</tr>
					<tr>
						<td>CPF:</td>
						<td><form:input path="cpf" class="span3" style="width: 117px" maxlength="14" /></td>
						<td><form:errors path="cpf" cssClass="error" /></td>
					</tr>																
					<tr>
						<td>RG:</td>
						<td><form:input path="rg" class="span3" style="width: 117px" maxlength="13" /></td>
						<td><form:errors path="rg" cssClass="error" /></td>
					</tr>
					<tr>
						<td>Idade:</td>
						<td><form:input path="idade" class="span3" style="width: 30px"/></td>
						<td><form:errors path="idade" cssClass="error" /></td>
					</tr>
					<tr>
						<td>Peso:</td>
						<td><form:input path="peso" class="span3" style="width: 65px"/></td>
						<td><form:errors path="peso" cssClass="error" /></td>
					</tr>
					<tr>
						<td>Data de Nascimento:</td>
						<td><form:input id="datepicker" path="dataNasc" class="span3" style="width: 80px" maxlength="10" /></td>
						<td><form:errors path="dataNasc" cssClass="error" /></td>
					</tr>
					<tr>
						<td>Bairro:</td>
						<td><form:input path="bairro" class="span3" /></td>
						<td><form:errors path="bairro" cssClass="error" /></td>
					</tr>
					<tr>
						<td>Rua:</td>
						<td><form:input path="rua" class="span3"/></td>
						<td><form:errors path="rua" cssClass="error" /></td>
					</tr>
					<tr>
						<td>Número:</td>
						<td><form:input path="numero" class="span3" style="width: 60px"/></td>
						<td><form:errors path="numero" cssClass="error" /></td>
					</tr>
					<tr>
						<td>Tel Fixo:</td>
						<td><form:input path="telefoneFixo" class="span3" /></td>
						<td><form:errors path="telefoneFixo" cssClass="error" /></td>
					</tr>
					<tr>
						<td>Tel Celular:</td>
						<td><form:input path="telefoneCelular" class="span3" /></td>
						<td><form:errors path="telefoneCelular" cssClass="error" /></td>
					</tr>
					<tr>
						<td>Horario de treinamento:</td>
						<td><form:input path="horarioTreinamento" class="span3" maxlength="5"/></td>
						<td><form:errors path="horarioTreinamento" cssClass="error" /></td>
					</tr>
					<tr>
						<td>Dia do vencimento da parcela:</td>
						<td><form:input path="diaVencimentoParcela" class="span3" style="width: 30px" /></td>
						<td><form:errors path="diaVencimentoParcela" cssClass="error" /></td>
					</tr>					
					<tr>
						<td>Observação:</td>
						<td><form:textarea path="observacao" class="span3"/></td>
						<td><form:errors path="observacao" cssClass="error" /></td>
					</tr>
					<tr>
						<td colspan="2">
						<center>
							<a class="btn" href="#" onclick="document.forms[0].submit()"><i class="icon-ok icon-black"></i> Salvar </a>
							<a class="btn" href="admin"><i class="icon-backward icon-black"></i> Voltar </a>
						</center>
						</td>
					</tr>				
				</table>
			</form:form>
		</center>
    </jsp:body>
</layout:page>