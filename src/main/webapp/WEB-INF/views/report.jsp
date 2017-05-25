<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<%@ page session="false" %>
<html>
<head>
    <title>Pentaho</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />">
    <script type="text/javascript" src="/resources/script.js"></script>
</head>
<body>
<h1><s:message code="pentaho.report.welcome" text="Welcome"/></h1>


<div id="dashboard-dashboard-frame">
    <iframe id=pentaho-iframe class="dashboard-dashboard-frame" src=""></iframe>
</div>

<%--<div id="dashboard-frame-link">--%>
<%--<a href="<c:url value="http://localhost:8080/pentaho/api/repos/%3Apublic%3ASteel%20Wheels%3AMySalesDashboard.xdash/viewer" />">Dashboard</a>--%>
<%--</div>--%>
<%--<div id="dashboard-frame">--%>
<%--<iframe name="dashboard-frame" class="dashboard-frame"--%>
<%--src="http://localhost:8080/pentaho/api/repos/%3Apublic%3ASteel%20Wheels%3AMySalesDashboard.xdash/generatedContent"/>--%>
<%--</div>--%>
</body>
</html>
