<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<c:set var="title" value="ReFilm"/>
<c:set var="subtitle" value="Додайте запис про фільм..."/>
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
                    <form:form method="POST" modelAttribute="newFilm">

                    <form:errors path="*" cssClass="alert alert-danger" element="div"/>


                    <div class="control-group">
                        <div class="form-group floating-label-form-group controls">
                            <form:label path="name">Назва</form:label>
                            <form:input id="name" path="name" name="name" type="text" required="required"
                                        class="form-control" placeholder="Назва"/>

                            <form:errors path="name" cssClass="error"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <div class="form-group floating-label-form-group controls">
                            <form:label path="premiere">Прем'єра</form:label>
                            <form:input id="premiere" path="premiere" name="premiere" type="date" required="required"
                                        class="datepicker" placeholder="Прем'єра"/>
                            <form:errors path="premiere" cssClass="error"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <div class="form-group floating-label-form-group controls">
                            <form:label path="directors">Режисери</form:label>
                            <form:input id="directors" path="directors" name="directors" type="text" required="required"
                                        class="form-control" placeholder="Режисери"/>

                            <form:errors path="directors" cssClass="error"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <div class="form-group floating-label-form-group controls">
                            <form:label path="screenwriters">Сценаристи</form:label>
                            <form:input id="screenwriters" path="screenwriters" name="screenwriters" type="text" required="required"
                                        class="form-control" placeholder="Сценаристи"/>

                            <form:errors path="screenwriters" cssClass="error"/>
                        </div>
                    </div>

                        <div class="control-group">
                            <div class="form-group floating-label-form-group controls">
                                <form:label path="actors">Актори</form:label>
                                <form:input id="actors" path="actors" name="actors" type="text" required="required"
                                            class="form-control" placeholder="Актори"/>

                                <form:errors path="actors" cssClass="error"/>
                            </div>
                        </div>
                        <h3 class="text-center" style="margin-top: 15px; color: rgb(145, 35, 112)">Жанри</h3>
                        <hr/>
                        <c:forEach items="${genres}" var="c">
                            <div><input type="checkbox" id="${c.id}" value="${c.id}" name="genres"/> ${c.genre}</div>
                        </c:forEach>
                        <hr/>
                        <h3 class="text-center" style="color: rgb(145, 35, 112)">Країни</h3>
                        <hr/>
                        <c:forEach items="${countries}" var="c">
                            <div><input type="checkbox" id="${c.id}" value="${c.id}" name="countries"/> ${c.country}</div>
                        </c:forEach>
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