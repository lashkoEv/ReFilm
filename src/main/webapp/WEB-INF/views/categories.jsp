<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<c:set var="title" value="Study helper"/>
<c:set var="subtitle" value="Познайомтеся з нашими навчальними матеріалами!"/>
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

        <div class="col-lg-8 col-md-10 mx-auto" style="margin-bottom: 30px">
            <form action="<c:url value="/categories/"/>">
                <div class="row justify-content-center">
                    <div class="form-group floating-label-form-group controls col-lg-8 col-md-12">
                        <label for="search">Пошук</label>
                        <input id="search" name="search" type="text" required="required"
                               class="form-control" placeholder="Введіть дані для пошуку..."/>
                    </div>
                    <span>&nbsp;</span>
                    <button type="submit" class="btn btn-light">
                        <i class="fas fa-search"></i>
                    </button>
                    <span>&nbsp;</span>
                    <a class="btn btn-light" style="padding-top: 27px" href="<spring:url value="/categories"/>"><i
                            class="fa fa-times" aria-hidden="true"></i></a>
                </div>
            </form>
        </div>


        <div class="col-lg-8 col-md-10 mx-auto" style="">
            <c:forEach items="${categories}" var="c" varStatus="state">
                <c:if test="${not state.first}">
                    <hr>
                </c:if>
                <div class="card m-2 h-auto">
                    <div class="card-body">
                        <a href="<spring:url value="/categories/${c.id}"/>">
                            <h2 style="text-align: center" class="post-title">${c.getCategory()}</h2>
                        </a>
                    </div>
                </div>
            </c:forEach>

            <%@include file="include/pagination.jsp" %>

        </div>
    </div>
</div>

<hr>

<%@include file="./include/footer.jsp" %>
