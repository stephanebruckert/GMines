<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'game.label', default: 'Game')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
        <script>
          $(function() {
            var actionCount = ${game.actionCount};

            function refresh() {
                $.ajax({
                    url:"${g.createLink(controller:'game',action:'refresh', id:game.id)}",
                    dataType: 'text',
                    success: function(data) {
                        if (parseInt(data) != actionCount) {
                            location.reload();
                        }
                    },
                    error: function(request, status, error) {

                    },
                    complete: function() {

                    }
                });
            }
            setInterval(refresh, 1000);
          });
        </script>
    </head>
    <body>
        <a href="#show-game" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/games')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-game" class="content scaffold-show" role="main">
            <h1>${game.player1} (${game.player1minesFound}) vs. ${game.player2} (${game.player2minesFound})</h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            ${game.winner}
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
            </ol>
            <!--<g:form resource="${game}" method="DELETE">
                <fieldset class="buttons">
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
            -->
        </div>
    </body>
</html>