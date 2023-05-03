package wifiDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class WifiService {
    public void dbInsert(){
        String url = "jdbc:mariadb://localhost:3306/testdb1";
        String dbUserId = "root";
        String dbPassword = "0000";
        // 넣어줄 와이파이 값 (openAPI)
        OpenAPI openAPI = new OpenAPI();
        ArrayList<List<Object>> allWifi = openAPI.getAllWifi();

        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;


        // 변수에 들어갈 값
        List<Object> x_swifi_inout_door_value = allWifi.get(0);
        List<Object> x_swifi_instl_floor_value = allWifi.get(1);
        List<Object> x_swifi_instl_mby_value = allWifi.get(2);
        List<Object> x_swifi_remars3_value = allWifi.get(3);
        List<Object> x_swifi_instl_ty_value = allWifi.get(4);
        List<Object> x_swifi_mgr_no_value = allWifi.get(5);
        List<Object> x_swifi_wrdofc_value = allWifi.get(6);
        List<Object> x_swifi_adres1_value = allWifi.get(7);
        List<Object> x_swifi_adres2_value = allWifi.get(8);
        List<Object> x_swifi_cmcwr_value = allWifi.get(9);
        List<Object> work_dttm_value = allWifi.get(10);
        List<Object> x_swifi_svc_se_value = allWifi.get(11);
        List<Object> x_swifi_main_nm_value = allWifi.get(12);
        List<Object> lnt_value = allWifi.get(13);
        List<Object> x_swifi_cnstc_year_value = allWifi.get(14);
        List<Object> lat_value = allWifi.get(15);
        int n = allWifi.get(0).size();


        try{//work_dttm_value.get(i)


            connection = DriverManager.getConnection(url, dbUserId, dbPassword);
            for(int i=0;i<n;i++){
                String sql = "insert into wifi (x_swifi_inout_door,x_swifi_instl_floor,x_swifi_instl_mby, x_swifi_remars3, x_swifi_instl_ty,x_swifi_mgr_no,x_swifi_wrdofc,x_swifi_adres1, x_swifi_adres2,x_swifi_cmcwr,work_dttm,x_swifi_svc_se,x_swifi_main_nm,lnt,x_swifi_cnstc_year,lat) "+
                        " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);"; // insert하는 쿼리

                long ldata = Long.parseLong(String.valueOf(work_dttm_value.get(i)));
                String dateFormatStringTime;
                Date date = new Date(ldata);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                dateFormatStringTime = dateFormat.format(date);


                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setObject(1, x_swifi_inout_door_value.get(i));
                preparedStatement.setObject(2, String.valueOf(x_swifi_instl_floor_value.get(i)));
                preparedStatement.setObject(3, String.valueOf(x_swifi_instl_mby_value.get(i)));
                preparedStatement.setObject(4, String.valueOf(x_swifi_remars3_value.get(i)));
                preparedStatement.setObject(5, String.valueOf(x_swifi_instl_ty_value.get(i)));
                preparedStatement.setObject(6, String.valueOf(x_swifi_mgr_no_value.get(i)));
                preparedStatement.setObject(7, String.valueOf(x_swifi_wrdofc_value.get(i)));
                preparedStatement.setObject(8, String.valueOf(x_swifi_adres1_value.get(i)));
                preparedStatement.setObject(9, String.valueOf(x_swifi_adres2_value.get(i)));
                preparedStatement.setObject(10,String.valueOf(x_swifi_cmcwr_value.get(i)));
                preparedStatement.setString(11, dateFormatStringTime);
                preparedStatement.setObject(12, String.valueOf(x_swifi_svc_se_value.get(i)));
                preparedStatement.setObject(13, String.valueOf(x_swifi_main_nm_value.get(i)));
                preparedStatement.setDouble(14, Double.parseDouble(String.valueOf(lnt_value.get(i))));
                preparedStatement.setObject(15, String.valueOf(x_swifi_cnstc_year_value.get(i)));
                preparedStatement.setDouble(16, Double.parseDouble(String.valueOf(lat_value.get(i))));



                int affected = preparedStatement.executeUpdate();

                if(affected > 0){
                    System.out.println("저장 성공");
                }else{
                    System.out.println("저장 실패");
                }
            }


        }catch (SQLException e){
            e.printStackTrace();

        }finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
    }

    public List<String[]> dbSelect(double lat, double lnt){
        String url = "jdbc:mariadb://localhost:3306/testdb1";
        String dbUserId = "root";
        String dbPassword = "0000";

        List<String[]> result = new ArrayList<String[]>();

        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try{
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);
            statement = connection.createStatement();

            String sql = " select * , ST_DISTANCE_SPHERE(point("+lat +","+lnt +"), POINT(lnt, lat)) AS dist" +
                    " from wifi "+
                    " where -90 <= lat and lat <= 90 "+
                    " order by dist limit 20;";

            rs = statement.executeQuery(sql);

            while (rs.next()) {
                String[] tmp = new String[17];
                tmp[0] = rs.getString("dist");
                tmp[1] = rs.getString("x_swifi_mgr_no");
                tmp[2] = rs.getString("x_swifi_wrdofc");
                tmp[3] = rs.getString("x_swifi_main_nm");
                tmp[4] = (rs.getString("x_swifi_adres1"));
                tmp[5] = (rs.getString("x_swifi_adres2"));
                tmp[6] = (rs.getString("x_swifi_instl_floor"));
                tmp[7] = (rs.getString("x_swifi_instl_ty"));
                tmp[8] = (rs.getString("x_swifi_instl_mby"));
                tmp[9] = (rs.getString("x_swifi_svc_se"));
                tmp[10] = (rs.getString("x_swifi_cmcwr"));
                tmp[11] = (rs.getString("x_swifi_cnstc_year"));
                tmp[12] = (rs.getString("x_swifi_inout_door"));
                tmp[13] = (rs.getString("x_swifi_remars3"));
                tmp[14] = (rs.getString("lat"));
                tmp[15] = (rs.getString("lnt"));
                tmp[16] = (rs.getString("work_dttm"));

                result.add(tmp);
            }


        }catch (SQLException e){
            e.printStackTrace();

        }finally {
            try{
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
        return result;
    }

}
