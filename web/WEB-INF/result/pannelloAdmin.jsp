<%--
  Created by IntelliJ IDEA.
  User: Antonio
  Date: 11/1/2020
  Time: 1:08 PM
  To change this template use File | Settings | File Templates.
--%>
    <%@ include file="/WEB-INF/result/header.jsp" %>
<div class="sito" id="homecontainer">
    <p>Benvenuto, amministratore ${sessionScope.beanUtente.nome}.</p>

    <table>
        <tr>
            <form action="ChiamaOperazioneAdmin">
            <th><button type="submit" class="siteButtons" name="operazione" value="listaProdotti">Lista Prodotti</button> </th>
                <th><button type="submit" class="siteButtons" name="operazione" value="listaUtenti">Lista Utenti</button> </th>
                <th><button type="submit"  class="siteButtons" name="operazione" value="aggiungiProdotto">Aggiungi un Prodotto</button> </th>
            </form>
        </tr>
    </table>
</div>
<%@ include file="/WEB-INF/result/footer.jsp" %>
</body>
</html>
