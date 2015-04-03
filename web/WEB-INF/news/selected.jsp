<jsp:include page="../elements/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">

    <h1>la new</h1>
    ${requestScope.n.titre}
    <p>${requestScope.n.txt}</p>

    <p>${requestScope.n.categorie.value}</p>

    <c:forEach var="row" items="${requestScope.listeTags}">
        
        <a href="<%=application.getContextPath()%>/viewByTag?name=${row.value}&id=${row.id}"><button type="button" class="btn btn-info">${row.value}</button></a>

    </c:forEach>
        <br><br><a href="<%=application.getContextPath()%>/"><button type="button" class="btn btn-alert">retour</button></a>
</div>
<jsp:include page="../elements/footer.jsp" />