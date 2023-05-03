<%@ page import="wifiDb.WifiService" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="historyDb.HistoryService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script> <!--jQuery 불러오기-->
<script>
    function getLocation(){
        // 현재 위치 가져오기
        navigator.geolocation.getCurrentPosition(getSuccess, getError);
        // 가져오기 성공
        function getSuccess(position) {
            let inputlat = document.getElementById("latValue");
            let inputlnt = document.getElementById("lntValue");


            const lat = position.coords.latitude;

            const lnt = position.coords.longitude;

            document.getElementById("latValue").value = lat;
            document.getElementById("lntValue").value = lnt;
        }

        // 가지오기 실패(거부)
        function getError() {
            alert('Geolocation Error');
        }
    }

    function submitLocation(){
        // 현재 위치 가져오기
        navigator.geolocation.getCurrentPosition(getSuccess, getError);
        result = []
        // 가져오기 성공
        function getSuccess(position) {
            let inputlat = document.getElementById("latValue");
            let inputlnt = document.getElementById("lntValue");


            const lat = position.coords.latitude;
            result.push(lat);

            const lnt = position.coords.longitude;
            result.push(lnt);

        }

        // 가지오기 실패(거부)
        function getError() {
            alert('Geolocation Error');
        }
        return result;
    }
</script>
<head>
    <style>
        #hitory_list {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #hitory_list td, #hitory_list th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #hitory_list tr:nth-child(even){background-color: #f2f2f2;}

        #hitory_list tr:hover {background-color: #ddd;}

        #hitory_list th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #04AA6D;
            color: white;
        }
    </style>
    <title>와이파이 정보 구하기</title>
</head>
<body>

<h2>와이파이 정보 구하기</h2>
<a href="index.jsp">홈 |</a>
<a href="history.jsp">위치 히스토리 목록 |</a>
<a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> <br>

<form action="near-wifi.jsp">
    LAT : <input type="text" id = "latValue" name="lat" value="" >
    LNT : <input type="text" id = "lntValue" name="lnt" value="">

    <input onclick="getLocation()" type="button" value="내 위치 가져오기">
    <input type="submit" value="근처 WIFI 정보 열기">
</form>
<br>
<table id="hitory_list">
    <tr id="firstRow">
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
    <tr>
            <%
                HistoryService historyService = new HistoryService();
                List<Object[]> history = historyService.historydbList();
                for(Object[] r:history){
             %>
    <tr>
        <td><%=r[0]%></td>
        <td><%=r[1]%></td>
        <td><%=r[2]%></td>
        <td><%=r[3]%></td>
        <td><input type="button" value="삭제"  onclick="location.href='history-delete.jsp?id=<%=r[0]%>'"></td>
    </tr>
    <%
        }
    %>

    </tr>
</table>

</body>
</html>

