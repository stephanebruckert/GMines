<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'game.label', default: 'Game')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
    	<div id="board">
	    	<div id="grid">
				<g:each in="${cells}" var="row" status="rowcount">
					<g:each in="${row}" var="cell" status="colcount">
						<g:form controller="game" action="stroke">
						    <g:hiddenField name="x" value="${rowcount}" />
						    <g:hiddenField name="y" value="${colcount}" />
						    <g:hiddenField name="id" value="${game.id}" />
						    <g:actionSubmitImage value="stroke" action="stroke" 
						    	src="${resource(dir: 'images', file: cell)}" />
						</g:form>
					</g:each>
				</g:each>
			</div>
		</div>
	</body>
</html>