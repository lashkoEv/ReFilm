<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<c:set var="title" value="Study helper"/>
<c:set var="subtitle" value="Створіть новий навчальний матеріал..."/>
<c:set var="picture" value='${pageContext.request.contextPath}/img/bg11.jpg'/>


<%@include file="include/navigation.jsp" %>
<%@include file="include/header.jsp" %>


<script src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>

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
                    <form:form method="POST" modelAttribute="post">

                        <form:errors path="*" cssClass="alert alert-danger" element="div"/>

                        <form:input id="title" path="title" name="title" type="text" required="required"
                                    class="form-control" placeholder="Введіть назву матеріалу..."/>
                        <form:errors path="title" cssClass="error"/>

                        <form:select path="category" class="custom-select" style="width:100%">
                            <c:forEach items="${categories}" var="c" varStatus="state">
                                <form:option value="${c.id}" label="${c.getCategory()}"/>
                            </c:forEach>
                        </form:select>
                        <form:errors path="category" cssClass="error"/>

                        <br/>

                        <form:textarea path="body" id="editor1" name="editor1" cols="80" rows="10"/>
                        <form:errors path="body" cssClass="error"/>

                        <script>
                            CKEDITOR.replace('editor1');
                        </script>

                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

<hr>

<%@include file="./include/footer.jsp" %>
