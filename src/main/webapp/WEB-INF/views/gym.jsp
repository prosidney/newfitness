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
    </jsp:attribute>
    <jsp:body>
		<center>
			<h4>Cadastro da Unidade</h4>
			<form:form method="post" action="saveGym.do" id="form" modelAttribute="gym" class="navbar-form pull-center">
				<table>
					<form:hidden path="id"/>
					<tr>
						<td>Nome:</td>
						<td><form:input path="name" class="span3" style="width: 150px"/></td>
						<td><form:errors path="name" cssClass="error" /></td>
					</tr>
					<tr>
						<td>Capacidade maxima:</td>
						<td><form:input path="maxCapacity" class="span3" style="width: 40px" onkeyup="enforceNumericValue(this)"/></td>
						<td><form:errors path="maxCapacity" cssClass="error" /></td>
					</tr>
					<tr>
						<td>Hora de abertura:</td>
						<td><form:input path="opening" class="span3" style="width: 40px" onkeyup="enforceNumericValue(this)"/></td>
						<td><form:errors path="opening" cssClass="error" /></td>
					</tr>
					<tr>
						<td>Hora de fechamento:</td>
						<td><form:input path="closing" class="span3" style="width: 40px" onkeyup="enforceNumericValue(this)"/></td>
						<td><form:errors path="closing" cssClass="error" /></td>
					</tr>
					<tr>
						<td>Valor mensalidade:</td>
						<td><form:input path="amount" class="span3" style="width: 40px" onkeyup="enforceNumericValue(this)"/></td>
						<td><form:errors path="amount" cssClass="error" /></td>
					</tr>
					<tr>
						<td colspan="2">
						<center>
							<a class="btn" href="#" onclick="document.forms[0].submit()"><i class="icon-ok icon-black"></i> Salvar </a>
							<a class="btn" href="<c:url value="admin"/>"><i class="icon-backward icon-black"></i> Voltar </a>
						</center>
						</td>
					</tr>				
				</table>
			</form:form>
		</center>
    </jsp:body>
</layout:page>