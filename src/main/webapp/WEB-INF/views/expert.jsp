<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<c:set var="title" value="${user.getName()}"/>
<c:set var="subtitle" value="Дата народження: ${user.getBirthDateFormatted()} Email: ${user.getEmail()}"/>
<c:set var="picture" value="${pageContext.request.contextPath}/img/bg4.jpg"/>

<%@include file="include/navigation.jsp" %>
<%@include file="include/header.jsp" %>


<!-- FIXME добавить пагинацию -->

<div class="container">
    <div class="row">

        <div class="col-lg-12 col-md-12 mx-auto">
            <div class="row justify-content-md-center">
                <h1 style="color: #0085A1">РЕЦЕНЗІЇ</h1>
            </div>
            <hr/>
            <c:forEach items="${posts}" var="p" varStatus="state">
                <c:if test="${not state.first}">
                    <hr>
                </c:if>
                <div class="card m-2 h-auto">
                    <div class="card-header">
                        <a href="<spring:url value="/categories/${p.film.id}"/>">${p.film.name}</a>
                    </div>
                    <div class="card-body">
                        <a href="<spring:url value="/posts/${p.id}"/>" style="font-size: 20pt">
                                ${p.title}
                        </a>
                    </div>
                    <div class="card-footer">Автор
                        <a href="<spring:url value="/experts/${p.user.getId()}"/>">${p.user.getName()}</a>,
                        додано ${p.getCreatedDate()}
                    </div>
                </div>
            </c:forEach>
        </div>
        <hr/>
    </div>

</div>
<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
    <hr>
    <div class="row justify-content-center">

        <div class="card m-2 h-auto">
            <div class="card-body">
                <div class="align-content-lg-center">
                    <a class="btn btn-primary" href="<spring:url value="/experts/edit/${user.id}"/>">Редагувати</a>
                    <span>&ensp;</span>
                    <a class="btn btn-primary" href="<spring:url value="/experts/delete/${user.id}"/>">Видалити</a>
                </div>
            </div>
        </div>

    </div>

</sec:authorize>
<hr>

<%@include file="./include/footer.jsp" %>
