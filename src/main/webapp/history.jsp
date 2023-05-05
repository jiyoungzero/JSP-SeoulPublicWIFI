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
    function historyDetailLocation(){
        let lat = document.getElementById("latValue_detail").innerText;
        let lnt = document.getElementById("lntValue_detail").innerText;
        console.log(lat, lnt);

        location.href = "http://localhost:8080/Mission1_v3_war_exploded/near-wifi.jsp?lat="+lat+"&lnt="+lnt;
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

<h4>⛳ 히스토리 목록 중 하나를 클릭하면 해당 위치에서 가장 가까운 20개의 와이파이 목록으로 넘어갑니다 !</h4>

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
        <td onclick="historyDetailLocation()"><%=r[0]%></td>
        <td id = "latValue_detail" onclick="historyDetailLocation()"><%=r[1]%></td>
        <td id = "lntValue_detail" onclick="historyDetailLocation()"><%=r[2]%></td>
        <td onclick="historyDetailLocation()"><%=r[3]%></td>
        <td><input type="button" value="삭제"  onclick="location.href='history-delete.jsp?id=<%=r[0]%>'"></td>
    </tr>
    <%
        }
    %>

    </tr>
</table>

</body>
</html>
