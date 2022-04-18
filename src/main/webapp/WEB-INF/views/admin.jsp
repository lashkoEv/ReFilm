<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<c:set var="title" value="Study helper"/>
<c:set var="subtitle" value="Здобувай нові знання з нами!"/>
<c:set var="picture" value="${pageContext.request.contextPath}/img/bg2.jpg"/>

<%@include file="include/navigation.jsp" %>
<%@include file="include/header.jsp" %>


<!-- Main Content -->
<div class="container">
    <div class="row">

        <div class="col-lg-8 col-md-10 mx-auto">
            <c:forEach items="${strings}" var="s" varStatus="state">
                <c:if test="${not state.first}">
                    <hr>
                </c:if>

                <p>${s}</p>

            </c:forEach>

            <%@include file="include/pagination.jsp" %>

        </div>
    </div>
</div>


<hr>

<%@include file="./include/footer.jsp" %>