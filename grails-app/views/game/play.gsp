<table>
<g:each in="${cells}" var="row" status="rowcount">
	<tr>
	<g:each in="${row}" var="cell" status="colcount">
 		<td>
			<g:form controller="game" action="stroke">
			    <g:hiddenField name="x" value="${rowcount}" />
			    <g:hiddenField name="y" value="${colcount}" />
			    <g:hiddenField name="id" value="${game.id}" />
			    <g:actionSubmit value=" ${cell.isDiscovered} " action="stroke"/>
			</g:form>
 		</td>
	</g:each>
	</tr>
</g:each>
<table>