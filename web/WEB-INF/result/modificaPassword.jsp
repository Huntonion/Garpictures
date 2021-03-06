<%--
  Created by IntelliJ IDEA.
  User: Antonio
  Date: 11/7/2020
  Time: 2:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/result/header.jsp" %>
<c:choose>
    <c:when test="${beanUtente.visitatore == false}">
        <body>
        <div id="spazio_registrazione" class="spazioregistrazione">
            <h1>
                Modifica password
            </h1>
            <form action="ModificaPassword" method="post" id="modificaPassword">
                <div class="riga_input">
                    <div class="spazioinput">
                        <input type="password" name="nuovaPassword" id="nuovaPassword" onfocusout="validatePassword()"
                               placeholder="Nuova Password">
                        <p id="erroreNuovaPassword"></p>
                    </div>
                    <div class="spazioinput">
                        <input type="password" name="nuovaPasswordConferma" id="nuovaPasswordConferma"
                               onfocusout="validateConfermaPassword()" placeholder="Ripeti Password">
                        <p id="erroreNuovaPasswordConferma"></p>
                    </div>
                </div>
                <div class="riga_input">
                    <input type="submit" class="siteButtons2" value="cambia password" id="submitCambiaPassword">
                    <p>
                        <c:if test="${errore != null}">
                        ${errore}
                    </c:if></p>
                </div>
            </form>
        </div>
        <%@ include file="/WEB-INF/result/footer.jsp" %>
    </c:when>
    <c:otherwise>
        <%
            String redirectURL = "home";
            response.sendRedirect(redirectURL);
        %>
    </c:otherwise>
</c:choose>
</body>
<script src="formValidatorModifica.js"></script>
</html>