<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="layout" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" 	 uri="http://java.sun.com/jsp/jstl/core"%>
<layout:page title="New Fitness" description="Home" keywords="amazing, app, New Fitness" user="Admin">
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
        	<c:if test="${errorMessage != null}">
        		<span class="error">
		        	<c:out value="${errorMessage}"/>
        		</span>
        	</c:if>
        	<form method="post" action="findClient.do">
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
			<table class="table table-hover" style="width: 50%">
				<tr>
					<th colspan="7" style="text-align: center;" style="width: 80%"><c:out value="Alunos (${qtde})" /></th>
				</tr>
				<tr>
					<th><c:out value="Matricula" /></th>
					<th><c:out value="Nome" /></th>
					<th><c:out value="Idade" /></th>
					<th><c:out value="Peso" /></th>
					<th><c:out value="Situação" /></th>
					<th><c:out value="Ações" /></th>
				</tr>
				<c:forEach var="aluno" items="${alunos}">
					<c:choose>
						<c:when test="${aluno.status.sigla == 'OK'}">
						  	<tr class="success">
						</c:when>
						<c:otherwise>
							<tr class="warning">
						</c:otherwise>						
					</c:choose>
						<td style="width: 5%"><c:out value="${aluno.matricula}" /></td>
						<td><c:out value="${aluno.nome}" /></td>
						<td><c:out value="${aluno.idade}" /></td>
						<td><c:out value="${aluno.peso} Kg" /></td>
						<td><c:out value="${aluno.status}" /></td>
						<td>
							<a class="btn" href="<c:url value="addClient.do?mat=${aluno.matricula}"/>"><i class="icon-edit icon-black" ></i> </a>						
							<a class="btn" href="<c:url value="viewPaymentsByMat.do?mat=${aluno.matricula}"/>"><i class="icon-eye-open icon-black" ></i> </a>
							<a class="btn" href="<c:url value="addPayment.do?mat=${aluno.matricula}"/>"><i class="icon-plus-sign icon-black"></i> </a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<a class="btn" href="<c:url value="addClient.do"/>"><i class="icon-plus-sign icon-black"></i> Cadastrar </a>
		</center>        
    </jsp:body>
</layout:page>