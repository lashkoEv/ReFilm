<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<div class="row justify-content-md-center" style="margin-top: 30px">
    <c:if test="${page.totalPages > 1}">
        <div class="row">
            <ul class="pagination pagination-sm pagination-lg pagination-control">
                <c:choose>
                    <c:when test="${page.hasPrevious()}">
                        <li style="margin: 5px; padding: 10px 20px 10px 20px" class="waves-effect">
                            <a href="${url}?page=${page.number-1}&size=${page.size}">
                                <i class="material-icons">chevron_left</i>
                            </a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li style="margin: 5px; padding: 10px 20px 10px 20px" class="disabled">
                            <a href="#!"><i class="material-icons">chevron_left</i></a>
                        </li>
                    </c:otherwise>
                </c:choose>
                <c:forEach var="i" begin="1" end="${page.totalPages}">
                    <c:choose>
                        <c:when test="${page.number + 1 == i}">
                            <li style="margin: 5px; padding: 10px 20px 10px 20px" class="active btn-light"><a
                                    href="${url}?page=${i-1}&size=${page.size}">${i}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li style="margin: 5px; padding: 10px 20px 10px 20px" class="waves-effect"><a
                                    href="${url}?page=${i-1}&size=${page.size}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:choose>
                    <c:when test="${page.hasNext()}">
                        <li style="margin: 5px; padding: 10px 20px 10px 20px" class="waves-effect">
                            <a href="${url}?page=${page.number+1}&size=${page.size}">
                                <i class="material-icons">chevron_right</i>
                            </a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li style="margin: 5px; padding: 10px 20px 10px 20px" class="disabled"><a href="#!">
                            <i class="material-icons">chevron_right</i></a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </c:if>
</div>