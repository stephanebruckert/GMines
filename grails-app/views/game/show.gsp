<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'game.label', default: 'Game')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
        <script>
          $(function() {
            var i = 0;
            var actionCount = ${game.actionCount};
            
            function afficheConversation() {
                $.ajax({
                    url:"${g.createLink(controller:'game',action:'refresh', id:game.id)}",
                    dataType: 'text',
                    success: function(data) {
                        $('#ajax').html(data + " " + i++ );

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
            setInterval(afficheConversation, 1000);
          });
        </script>
    </head>
    <body>
        <a href="#show-game" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="ajax">init</div>
        <div id="show-game" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
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
            <g:form resource="${game}" method="DELETE">
                <fieldset class="buttons">
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>