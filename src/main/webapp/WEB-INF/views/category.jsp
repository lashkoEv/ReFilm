<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<c:set var="title" value="${category.getCategory()}"/>
<c:set var="subtitle" value="Усього матеріалів: ${posts.size()}"/>
<c:set var="picture" value="${pageContext.request.contextPath}/img/bg7.jpg"/>

<%@include file="include/navigation.jsp" %>
<%@include file="include/header.jsp" %>


<!-- FIXME добавить пагинацию -->

<div class="container">
    <div class="row">

        <div class="col-lg-12 col-md-12 mx-auto">
            <div class="row justify-content-md-center">
                <h1 style="color: #0085A1">НАВЧАЛЬНІ МАТЕРІАЛИ</h1>
            </div>
            <hr/>
            <c:forEach items="${posts}" var="p" varStatus="state">
                <c:if test="${not state.first}">
                    <hr>
                </c:if>
                <div class="card m-2 h-auto">
                    <div class="card-header">
                        <a href="<spring:url value="/categories/${p.category.id}"/>">${p.category.category}</a>
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

        <div class="align-content-lg-center">
            <a class="btn btn-primary" href="<spring:url value="/categories/edit/${category.id}"/>">Редагувати</a>
            <a class="btn btn-primary" href="<spring:url value="/categories/delete/${category.id}"/>">Видалити</a>
        </div>
    </div>
</sec:authorize>
<hr>

<%@include file="./include/footer.jsp" %>
