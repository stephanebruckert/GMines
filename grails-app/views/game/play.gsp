<table>
<g:each in="${cells}" var="row">
	<tr>
	<g:each in="${row}" var="cell">
 		<td>${cell.nbCellsAdjacent}</td>
	</g:each>
	</tr>
</g:each>
<table>