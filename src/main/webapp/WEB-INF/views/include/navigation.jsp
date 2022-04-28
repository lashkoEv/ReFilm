<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="secure" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>
<html lang="ukr">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="StudyHelper">
    <meta name="author" content="Lashko E.L.">

    <title>${title}</title>
    <link rel="icon" type="image/x-icon" href="<spring:url value="img/icon1.ico"/>" />
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Bootstrap core CSS -->
    <link href="<spring:url value="/vendor/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">

    <link href="<spring:url value="/vendor/fontawesome-free/css/all.min.css"/>" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet'
          type='text/css'>

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
          rel='stylesheet' type='text/css'>

    <link href="<spring:url value="/css/clean-blog.css"/>" rel="stylesheet">
    <link href="<spring:url value="/css/error.css"/>" rel="stylesheet">
    <link href="<spring:url value="/css/style.css"/>" rel="stylesheet">

</head>

<body>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark fixed-top navbar-bg" id="mainNav">
    <div class="container">
        <a class="navbar-brand" href="<spring:url value="/"/>"><img src="${pageContext.request.contextPath}../../img/video-player.png" alt="/" style="margin-top: -4px"> ReFilm</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
                data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
                aria-label="Toggle navigation">
            Меню
            <i class="fas fa-bars"></i>
        </button>


        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Рецензії
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="<spring:url value="/"/>">Всі рецензії</a>
                        <sec:authorize access="hasAnyRole('ROLE_EXPERT', 'ROLE_ADMIN')">
                            <a class="dropdown-item" href="<spring:url value="/posts/create"/>">Створити нову рецензію</a>
                        </sec:authorize>
                    </div>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Фільми
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="<spring:url value="/films"/>">Усі фільми</a>
                        <a class="dropdown-item" href="<spring:url value="/films"/>">Фільми за жанрами</a>
                        <a class="dropdown-item" href="<spring:url value="/films"/>">Фільми за країною</a>
                        <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                            <a class="dropdown-item" href="<spring:url value="/films/create"/>">Додати новий фільм</a>
                        </sec:authorize>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Рецензенти
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="<spring:url value="/experts"/>">Усі рецензенти</a>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <a class="dropdown-item" href="<spring:url value="/experts/create"/>">Сформувати новий обліковий запис</a>
                        </sec:authorize>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<spring:url value="/about"/>">Про нас</a>
                </li>
                <li class="nav-item">
                    <sec:authorize access="isAnonymous()">
                        <a class="nav-link" href="<spring:url value="/login"/>">Авторизація</a>
                    </sec:authorize>
                    <sec:authorize access="!isAnonymous()">
                        <a class="nav-link"
                           href="<spring:url value="/logout"/>">Выйти '${pageContext.request.userPrincipal.name}' </a>
                    </sec:authorize>
                </li>

                <li class="nav-item">
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <a class="nav-link"
                           href="<spring:url value="/admin"/>">Admin</a>
                    </sec:authorize>
                </li>

            </ul>

        </div>
    </div>
</nav>
