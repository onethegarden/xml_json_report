<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>Test</title>
</head>
<style>
h1{
text-align: center;
}
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

.center {
	text-align: center;
}

table {
	width: 500px;
}

.header {
	width: 500px;
	text-align: center;
	margin-bottom: 10px;
}
.xmlWrapper{
	width: 700px;
	margin: 0 auto;
}
.jsonWrapper{
	width: 700px;
	margin: 0 auto;
}
.reportApp {
	border: 1px solid black;
	width: 500px;
	height: 800px;
	margin: 0 auto;
	margin-top: 10px;
	padding: 100px;
}
</style>
<body>
	<h1>xml json 보고서</h1>

	<!-- <P>  The time on the server is ${serverTime}. </P> -->
	<div class="xmlWrapper">
		<textarea rows="10" cols="50" class="setXML"></textarea>
		<button onClick="getXMLData()">getXML</button>
	</div>
	<br />
	<div class="jsonWrapper">
		<textarea rows="10" cols="50" class="setJSON"></textarea>
		<button onClick="getJSONData()">getJSON</button>
	</div>
	<div class="reportApp"></div>
</body>
<script>
	
</script>
<script src="<c:url value="resources/js/convertJSON.js" />"></script>
<script src="<c:url value="resources/js/convertXML.js" />"></script>
</html>
