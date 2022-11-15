<%@ page import="com.codeup.adlister.models.User" %>
<%@ page import="com.codeup.adlister.models.Ad" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads"/>
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>


<div class="container">
    <h1>Here Are all the ads!</h1>
    <form method="get" action="/ads">
        <p>Search for ad by title</p>
        <input name="search-criteria" type="text">
        <button type="submit">Search</button>
    </form>

    <span><a href="/ads/create">Create ad</a></span>

    <c:forEach var="ad" items="${ads}">
        <div class="col-md-6">
            <h2>${ad.title}</h2>
            <p>${ad.description}</p>
            <p>Created by user id: ${ad.userId}</p>

            <c:if test="${ad.getUserId() == user.getId()}">
                <form method="post" action="/ads/delete">
                    <input name="ad_id" type="hidden" value="${ad.id}">
                    <button type="submit">Delete</button>
                </form>

                <p>Update Ad</p>
                <form method="post" action="/ads/update">
                    <input type="hidden" name="updateId" value="${ad.id}">
                    <input name="updateTitle" value="${ad.title}">
                    <input name="updateDescription" value="${ad.description}">
                    <button type="submit">Submit</button>
                </form>

            </c:if>

        </div>
    </c:forEach>

</div>

</body>
</html>
