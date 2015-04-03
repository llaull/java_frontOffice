<jsp:include page="../elements/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
    <h1>les news</h1>

    <div>Categorie</div>
    <form role="form" action="<%=application.getContextPath()%>/viewByCat" method="post">
        <div class="form-group col-sm-6">
            <label>Categorie disponnible
                <select id="categorie" name="categorie" class="form-control">
                    <c:forEach var="row" items="${requestScope.listeCategorie}">
                        <option id="${row.id}" value="${row.id}">${row.value}</option>
                    </c:forEach>
                </select>
            </label> 
        </div>
        <button type="submit" class="btn btn-success">ok</button>
    </form>



    <table class="table table-hover">
        <thead>
            <tr>
                <th>id</th>
                <th>Libellé</th>
                <th>Categorie</th>
                <th>ACTION</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="row" items="${requestScope.listeNew}">
                <tr>
                    <td>${row.id}</td>
                    <td>${row.titre}</td>
                    <td>${row.categorie.value}</td>
                    <td><a href="<%=application.getContextPath()%>/New?id=${row.id}"><button type="button" class="btn btn-info btn-sm">Lire</button></a></a></td>

                </tr>
            </c:forEach>
            </div>
            <jsp:include page="../elements/footer.jsp" />