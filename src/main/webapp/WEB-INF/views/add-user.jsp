<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<c:set var="title" value="Study helper"/>
<c:set var="subtitle" value="Створіть новий обліковий запис експерта..."/>
<c:set var="picture" value='${pageContext.request.contextPath}/img/bg9.jpg'/>


<%@include file="include/navigation.jsp" %>
<%@include file="include/header.jsp" %>


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
                    <form:form method="POST" modelAttribute="user">

                    <form:errors path="*" cssClass="alert alert-danger" element="div"/>


                    <div class="control-group">
                        <div class="form-group floating-label-form-group controls">
                            <form:label path="username">Логін</form:label>
                            <form:input id="username" path="username" name="username" type="text" required="required"
                                        class="form-control" placeholder="Логін"/>

                            <form:errors path="username" cssClass="error"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <div class="form-group floating-label-form-group controls">
                            <form:label path="password">Пароль</form:label>
                            <form:input id="password" path="password" name="password" type="password"
                                        required="required"
                                        class="form-control" placeholder="Пароль"/>
                            <form:errors path="password" cssClass="error"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <div class="form-group floating-label-form-group controls">
                            <form:label path="name">ПІБ</form:label>
                            <form:input id="name" path="name" name="name" type="text" required="required"
                                        class="form-control" placeholder="ПІБ"/>
                            <form:errors path="name" cssClass="error"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <div class="form-group floating-label-form-group controls">
                            <form:label path="email">Email</form:label>
                            <form:input id="email" path="email" name="email" type="text" required="required"
                                        class="form-control"
                                        placeholder="Email"/>
                            <form:errors path="email" cssClass="error"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <div class="form-group floating-label-form-group controls">
                            <form:label path="birthDate">Дата народження</form:label>
                            <form:input id="birthDate" path="birthDate" name="birthDate" type="date" required="required"
                                        class="datepicker" placeholder="Дата народження"/>
                            <form:errors path="birthDate" cssClass="error"/>
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