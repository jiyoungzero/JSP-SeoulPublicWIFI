<%@ page import="wifiDb.WifiService" %>
<%@ page import="wifiDb.OpenAPI" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="wifiDb.DbTestMain" %><%--
  Created by IntelliJ IDEA.
  User: JYLEE
  Date: 2023-04-24
  Time: 오후 4:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="wifiDb.WifiService" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <style>
        body {
            text-align: center;
        }
    </style>
</head>
<body>
<%

    WifiService wifiService = new WifiService();
    wifiService.dbInsert();
    OpenAPI openAPI = new OpenAPI();
    ArrayList<List<Object>> allWifi = openAPI.getAllWifi();
    int n = allWifi.get(0).size();
%>

<h1><%=n%>개의 WIFI정보를 정상적으로 저장했습니다.</h1>

<a href="index.jsp">홈으로 가기</a>

</body>
</html>
