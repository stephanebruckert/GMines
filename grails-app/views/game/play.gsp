<table>
<g:each in="${cells}" var="row">
	<tr>
	<g:each in="${row}" var="cell" status="col">
 		<td>
			<g:form controller="game" action="stroke">
			    <g:hiddenField name="x" value="${row}" />
			    <g:hiddenField name="y" value="${col}" />
			    <g:hiddenField name="id" value="${game.id}" />
			    <g:actionSubmit value=" ${cell.nbCellsAdjacent} " action="stroke"/>
			</g:form>
 		</td>
	</g:each>
	</tr>
</g:each>
<table>