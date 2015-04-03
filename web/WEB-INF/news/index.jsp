<jsp:include page="../elements/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
    <h1>les news</h1>

    <form role="form" action="<%=application.getContextPath()%>/viewByCat" method="post">
        <div class="form-group">
            <label>Categorie disponnible
                <select id="categorie" name="categorie" class="form-control">
                    <option id="0" value="0">Toute les news</option>
                    <c:forEach var="row" items="${requestScope.listeCategorie}">
                        <option id="${row.id}" value="${row.id}">${row.value}</option>
                    </c:forEach>
                </select>
            </label> 
        </div>
        <button type="submit" class="btn btn-success">ok</button>
    </form>

    <div>
        <c:forEach var="row" items="${requestScope.listeNew}">
            <article style="border:1px solid grey; background: #ccc; margin-top: 10px;">
                <h1>${row.titre}</h1>
                <p>${row.categorie.value}</p>
                <p>${row.txt}</p>
                <a href="<%=application.getContextPath()%>/New?id=${row.id}"><button type="button" class="btn btn-info btn-sm">Lire</button></a></a>
            </article>              
        </c:forEach>
    </div>

</div>
<jsp:include page="../elements/footer.jsp" />