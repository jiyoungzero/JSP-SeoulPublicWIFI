<%@ page import="wifiDb.WifiService" %>
<%@ page import="wifiDb.OpenAPI" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
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
    request.setCharacterEncoding("utf-8");
    // 와이파이 정보 insert
    WifiService wifiService = new WifiService();
    //wifiService.dbInsert();
    // 와이파이 개수 구하기
    OpenAPI openAPI = new OpenAPI();
    ArrayList<List<Object>> allWifi = openAPI.getAllWifi();
    int n = allWifi.get(0).size();
%>

<h1><%=n%>개의 WIFI정보를 정상적으로 저장했습니다.</h1>

<a href="index.jsp">홈으로 가기</a>

</body>
</html>
