<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<c:set var="title" value="ReFilm"/>
<c:set var="subtitle" value="Деяка інформація про проєкт"/>
<c:set var="picture" value="${pageContext.request.contextPath}/img/bg4.jpg"/>

<%@include file="include/navigation.jsp" %>
<%@include file="include/header.jsp" %>


<!-- Main Content -->
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
            <p>Рецензії до фільмів – важлива складова частина кожної стрічки. Адже абсолютно будь-який фільм потребує рецензії, оскільки коротка, проте досить ємна інформація дозволяє потенційному глядачеві зрозуміти суть кінострічки. Всі рецензії написані професійними рецензентами сайту, тому читати їх не лише пізнавально, але і цікаво.</p>
            <p>Рецензії до фільмів дають можливість вникнути в жанр фільму, що рецензується. Така інформація спочатку вводить кіномана в суть та історію обраного кіно. Рецензії на фільми надає більш повну та розгорнуту інформацію, що містить конкретні факти з кінострічки. Знайомитись з рецензіями фільмів завжди практично та зручно. Адже навіть не переглянувши кіно, ви зможете мати уявлення про цю кінострічку.</p>
            <p>За будь-якими питаннями звертатися на пошту: <strong>smevlk2014@gmail.com</strong></p>
        </div>
    </div>
</div>

<hr>

<%@include file="./include/footer.jsp" %>
