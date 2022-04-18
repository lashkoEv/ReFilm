<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<c:set var="title" value="Study helper"/>
<c:set var="subtitle" value="Створіть нову категорію навчальних матеріалів..."/>
<c:set var="picture" value='${pageContext.request.contextPath}/img/bg12.jpg'/>


<%@include file="include/navigation.jsp" %>
<%@include file="include/header.jsp" %>


<!-- Main Content -->
<div class="container">
    <div class="row">

        <div class="col-lg-8 col-md-10 mx-auto">
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger">
                    <strong>Помилка:</strong> ${errorMessage}
                </div>
            </c:if>
        </div>


        <div class="col-lg-8 col-md-10 mx-auto">
            <div class="card m-2 h-auto">
                <div class="card-body">
                    <form:form method="POST" modelAttribute="newCategory">

                        <form:errors path="*" cssClass="alert alert-danger" element="div"/>


                        <div class="control-group">
                            <div class="form-group floating-label-form-group controls">
                                <form:label path="category">Назва категорії</form:label>
                                <form:input id="category" path="category" name="category" type="text"
                                            required="required"
                                            class="form-control" placeholder="Назва категорії"/>

                                <form:errors path="category" cssClass="error"/>
                            </div>
                        </div>
                </div>
                        <br/>
                        <div class="card-footer text-center">
                            <div class="row justify-content-center">
                                <button type="submit" class="btn btn-primary">Зберегти
                                    <i class="material-icons right" style="font-size: small">send</i>
                                </button>
                            </div>
                        </div>
                    </form:form>

            </div>
        </div>
    </div>
</div>

<hr>

<%@include file="./include/footer.jsp" %>