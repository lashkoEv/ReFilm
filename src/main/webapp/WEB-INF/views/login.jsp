<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<c:set var="title" value="Авторизація"/>
<c:set var="subtitle" value="Авторизація..."/>
<c:set var="picture" value="${pageContext.request.contextPath}/img/4.jpg"/>

<%@include file="include/navigation.jsp" %>
<link href="<spring:url value="/css/login-page.css"/>" rel="stylesheet">

<div class="container-fluid">
    <br/>
    <div class="row">
        <div class="col-lg-4 col-xl-4 mx-auto">
            <div class="card card-signin flex-row my-5">
<%--                <div class="card-img-left d-none d-md-flex" style="background: url('${picture}')">--%>
<%--                </div>--%>
                <div class="card-body">
                    <div class="container text-center ">
                        <img src="${pageContext.request.contextPath}../../img/video-player2.png" style="margin-top: 10px; border-radius: 10%; box-shadow: 0px 20px 18px -11px rgba(77,77,77,0.38);" alt="">
                    </div>
                    <br/><br/>
                    <h5 class="card-title text-center font-weight-bold">АВТОРИЗАЦІЯ</h5>
                    <br/>
                    <form class="form-signin" method="POST" action="<spring:url value="/login"/>">
                        <div class="form-label-group">
                            <input type="text" id="inputUserame" name="username" class="form-control" placeholder="Логин" required autofocus>
                            <label for="inputUserame">Логін</label>
                        </div>

                        <div class="form-label-group">
                            <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Пароль" required>
                            <label for="inputPassword">Пароль</label>
                        </div>

                        <div class="form-check">
                            <input type="checkbox" class="custom-checkbox" id="remember-me" name="remember-me">
                            <label class="form-check-label" for="remember-me">Не виходити із системи</label>
                        </div>


                        <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Авторизуватися</button>

                        <br/><br/><br/><br/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<hr/>

<%@include file="./include/footer.jsp" %>
