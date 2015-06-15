<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'game.label', default: 'Game')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <script>
            jQuery(document).ready(function() {
                jQuery(".time-ago").timeago();
            });
        </script>
    </head>
    <body>
        <a href="#list-game" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/games')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-game" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${gameList != null}">
                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>
                <table class='display'>
                  <tr>
                    <th>#</th>
                    <th>Player 1</th>
                    <th></th>
                    <th></th>
                    <th>Player 2</th>
                    <th>Last activity</th>
                    <th>Status</th>
                  </tr>
                  <g:each in="${gameList}" var="game">
                    <tr>
                      <td><g:link action="show" id="${game.id}">${game.id}</g:link></td>
                      <td>${game.player1}</td>
                      <td>${game.player1minesFound}</td>
                      <td>${game.player2minesFound}</td>
                      <td>${game.player2}</td>
                      <td class="time-ago" title="${game.player1lastActivity}"></td>
                      <td>
                        <g:if test="${game.winner == -1}">
                            <g:if test="${game.player2shouldPlay}">
                                <g:if test="${game.player2 == null}">
                                    Waiting for player 2
                                </g:if>
                                <g:else>
                                    Player 2's turn
                                </g:else>
                            </g:if>
                            <g:elseif test="${!game.player2shouldPlay}">
                                Player 1's turn
                            </g:elseif>
                        </g:if>
                        <g:elseif test="${game.winner == 1}">
                            Player 2 won!
                        </g:elseif>
                        <g:else>
                            Player 1 won!
                        </g:else>
                      </td>
                    </tr>
                  </g:each>
                </table>
            </g:if>
            <g:else>
                <div class="message" role="status">No games have recently been played, be the first one to create a game!</div>
            </g:else>
            <div class="pagination">
                <g:paginate total="${gameCount ?: 0}" />
            </div>
        </div>
    </body>
</html>