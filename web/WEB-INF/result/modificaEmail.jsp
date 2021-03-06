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
        <div id="spazio_registrazione" class="spazioregistrazione">
            <h1>
                Modifica e-mail
            </h1>
            <form action="ModificaEmail" method="post" id="modificaEmail">
                <div class="riga_input">
                    <div class="spazioinput">
                        <input type="text" name="nuovaEmail" id="nuovaEmail" onfocusout="validateMail()"
                               placeholder="Nuova e-mail">
                        <p id="erroreMail">
                            <c:if test="${errore != null}">
                            ${errore}
                        </c:if></p>
                    </div>
                    <input type="submit" class="siteButtons3" value="cambia email" id="submitEmail" name="submitEmail">
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
