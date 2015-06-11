<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'game.label', default: 'Game')}" />
        <title>GMines</title>
    </head>
    <body>
        <a href="#list-game" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            Welcome on GMines
        </div>
        <div id="list-game" class="content scaffold-list" role="main">
            <h1>
            <g:form action="join">
			    <label for="nickname">Please enter your name</label>
			    <g:textField name="nickname"/>
			    <g:submitButton name="Join Game"/>
			</g:form>
			</h1>
        </div>
    </body>
</html>