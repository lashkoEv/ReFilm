<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<link href="<spring:url value="/css/header.css"/>" rel="stylesheet">

<!-- Page Header -->
<header class="masthead">
    <div class="overlay"></div>
    <div id="carousel" class="carousel slide" data-ride="carousel" style="height: 600px !important;">
        <!-- Индикаторы -->
        <ol class="carousel-indicators">
            <li data-target="#carousel" data-slide-to="0" class="active"></li>
            <li data-target="#carousel" data-slide-to="1"></li>
            <li data-target="#carousel" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item active item-responsive item-16by9">
                <img class="img-fluid content" src="${pageContext.request.contextPath}../../img/bg2.jpg" alt="..." s>
                <div class="carousel-caption">
                    <div class="wrapper">
                        <h1>${title}</h1>
                        <h3>${subtitle}</h3>
                    </div>
                </div>
            </div>
            <div class="carousel-item item-responsive item-16by9">
                <img class="img-fluid content" src="${pageContext.request.contextPath}../../img/bg1.jpg" alt="...">
                <div class="carousel-caption">
                    <div class="wrapper">
                        <h1>${title}</h1>
                        <h3>${subtitle}</h3>
                    </div>
                </div>
            </div>
            <div class="carousel-item item-responsive item-16by9">
                <img class="img-fluid content" src="${pageContext.request.contextPath}../../img/bg3.jpg" alt="...">
                <div class="carousel-caption">
                    <div class="wrapper">
                        <h1>${title}</h1>
                        <h3>${subtitle}</h3>
                    </div>
                </div>
            </div>
        </div>
        <!-- Элементы управления -->
        <a class="carousel-control-prev" href="#carousel" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Предыдущий</span>
        </a>
        <a class="carousel-control-next" href="#carousel" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Следующий</span>
        </a>
    </div>
    </div>
</header>


<script>
    $('.carousel').carousel('cycle');
</script>
