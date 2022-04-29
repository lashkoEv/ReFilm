<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<c:set var="title" value="ReFilm"/>
<c:set var="subtitle" value="Найкращі рецензії українською!"/>
<c:set var="picture" value="${pageContext.request.contextPath}/img/bg6.jpg"/>

<%@include file="include/navigation.jsp" %>
<%@include file="include/header.jsp" %>

<!-- Main Content -->
<div class="container">
    <div class="row">

        <div class="col-lg-8 col-md-10 mx-auto">
            <c:if test="${not empty msg}">
                <div class="alert alert-success">
                    <strong>Повідомлення:</strong> ${msg}
                </div>
            </c:if>
        </div>


        <div class="col-lg-8 col-md-10 mx-auto" style="">

            <form action="<c:url value="/films/countries"/>" method="post">

                <c:forEach items="${countries}" var="c">
                    <div><input type="checkbox" id="${c.id}" value="${c.id}" name="countries"/> ${c.country}</div>
                </c:forEach>

                <hr>
                <div class="row justify-content-center">
                    <div class="align-content-lg-center">
                        <button type="submit" class="btn btn-primary"> Пошук
                            <i class="fas fa-search"></i>
                        </button>
                    </div>
                </div>

            </form>
        </div>

    </div>
</div>

<hr>

<%@include file="./include/footer.jsp" %>
