<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@attribute name="menu" required="true" type="de.dentrassi.pm.storage.web.menu.Menu"%>
<%@attribute name="brand" fragment="true"%>

<c:if test="${not empty menu }">

<c:set var="currentUrl" value="${pageContext.request.servletPath}" />

<nav class="navbar navbar-default">
<div class="container-fluid">

    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#main-menu-navbar-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <c:if test="${not empty brand }"><jsp:invoke fragment="brand"/></c:if>
    </div>
    
    <div class="collapse navbar-collapse" id="main-menu-navbar-collapse">
    <ul class="nav navbar-nav">
    <c:forEach items="${menu.nodes }" var="entry">
        <c:choose>
            <c:when test="${entry.getClass().simpleName eq 'Entry'}">
                <c:set var="url" value="${entry.target.renderFull(pageContext)}" />
                <li <c:if test="${currentUrl eq url}" >class="active"</c:if>><a href="<c:url value="${url }" />" <c:if test="${entry.newWindow }"> target="_blank"</c:if> >${fn:escapeXml(entry.label) }</a></li>
            </c:when>
            <c:when test="${entry.getClass().simpleName eq 'SubMenu' }">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">${fn:escapeXml(entry.label)} <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                         <c:forEach items="${entry.nodes }" var="subEntry">
                            <c:choose>
                                <c:when test="${subEntry.getClass().simpleName eq 'Entry'}">
                                    <c:set var="url" value="${subEntry.target.renderFull(pageContext)}" />
                                    <li <c:if test="${currentUrl eq url}" >class="active"</c:if>><a href="<c:url value="${url }" />" <c:if test="${subEntry.newWindow }"> target="_blank"</c:if> >${fn:escapeXml(subEntry.label) }</a></li>
                                </c:when>
                            </c:choose>
                         </c:forEach>
                    </ul>
                </li>
            </c:when>
        </c:choose>
    </c:forEach>
    </ul>
    </div>

</div>
</nav>
</c:if>
