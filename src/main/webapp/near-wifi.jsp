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
        #near_wifi_list {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #near_wifi_list td, #near_wifi_list th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #near_wifi_list tr:nth-child(even){background-color: #f2f2f2;}

        #near_wifi_list tr:hover {background-color: #ddd;}

        #near_wifi_list th {
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

<table id="near_wifi_list">
    <tr id="firstRow">
        <th>거리(Km)</th>
        <th>관리번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
    </tr>
    <tr>
        <%
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd E요일 HH:mm:ss");
            String strDate = simpleDateFormat.format(date);

            // 현재 위치 받아오기
            double lat = Double.parseDouble(String.valueOf(request.getParameter("lat")));
            double lnt = Double.parseDouble(String.valueOf(request.getParameter("lnt")));
            // 조회 일자 받기
            //System.out.println(strDate);
            // 히스토리 db에 insert
            HistoryService historyService = new HistoryService();
            historyService.historydbInsert(lat,lnt, strDate);

            WifiService wifiService = new WifiService();
            List<String[]> nearWifi = wifiService.dbSelect(lnt, lat);

            for(String[] r : nearWifi){
        %>
            <tr>
                <td><%=Double.parseDouble(String.valueOf(r[0]))/1000%></td>
                <td><%=r[1]%></td>
                <td><%=r[2]%></td>
                <td><%=r[3]%></td>
                <td><%=r[4]%></td>
                <td><%=r[5]%></td>
                <td> </td>
                <td><%=r[7]%></td>
                <td><%=r[8]%></td>
                <td><%=r[9]%></td>
                <td><%=r[10]%></td>
                <td><%=r[11]%></td>
                <td><%=r[12]%></td>
                <td> </td>
                <td><%=r[14]%></td>
                <td><%=r[15]%></td>
                <td><%=r[16]%></td>
            </tr>
        <%
            }
        %>

    </tr>
</table>

</body>
</html>

