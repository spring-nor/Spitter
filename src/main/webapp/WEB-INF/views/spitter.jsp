<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
  <head>
    <title>Spitter</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <div class="listTitle">
      <h1>Recent Spitter</h1>
      <ul class="spittleList">
        <c:forEach items="${spitterList}" var="spitter" >
          <li id="spitter_<c:out value="spitter.id"/>">
            <div class="spitterUsername"><c:out value="${spitter.username}" /></div>
            <div>
              <span class="spittleEmail"><c:out value="${spitter.email}" /></span>
              <span class="spittleName">(<c:out value="${spitter.firstName}" />, <c:out value="${spitter.lastName}" />)</span>
            </div>
          </li>
        </c:forEach>
      </ul>
      <%--<c:if test="${fn:length(spittleList) gt 20}">--%>
        <%--<hr />--%>
        <%--<s:url value="/spittles?count=${nextCount}" var="more_url" />--%>
        <%--<a href="${more_url}">Show more</a>--%>
      <%--</c:if>--%>
    </div>
  </body>
</html>