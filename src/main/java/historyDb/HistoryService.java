package historyDb;
import com.sun.corba.se.pept.transport.InboundConnectionCache;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class HistoryService {

    public void historydbInsert(double lat, double lnt, String date){
        String url = "jdbc:mariadb://localhost:3306/testdb1";
        String dbUserId = "root";
        String dbPassword = "0000";

        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try{
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = "insert into history ( lnt, lat,date) "+
                    " values(?,?,?);"; // insert하는 쿼리

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDouble(1, lat);
            preparedStatement.setDouble(2, lnt);
            preparedStatement.setString(3, date);

            int affected = preparedStatement.executeUpdate();

            if(affected > 0){
                System.out.println("저장 성공");
            }else{
                System.out.println("저장 실패");
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

    public List<Object[]> historydbList(){
        String url = "jdbc:mariadb://localhost:3306/testdb1";
        String dbUserId = "root";
        String dbPassword = "0000";

        List<Object[]> result = new ArrayList<>();

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

            String sql = " select *" +
                    " from history;";

            rs = statement.executeQuery(sql);

            while (rs.next()) {
                Object[] tmp = new Object[4];
                tmp[0] = rs.getInt("id");
                tmp[1] = rs.getDouble("lnt");
                tmp[2] = rs.getDouble("lat");
                tmp[3] = rs.getString("date");

                result.add(tmp);
            }

//            for(Object[] r:result){
//                for(Object ele:r) {
//                    System.out.println(ele);
//                }
//            }


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

    public void historydbDelete(int id){
        String url = "jdbc:mariadb://localhost:3306/testdb1";
        String dbUserId = "root";
        String dbPassword = "0000";

        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try{
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = " delete from history" +
                    " where id = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            int affected = preparedStatement.executeUpdate();

            if(affected > 0){
                System.out.println("삭제 완료");
            }else{
                System.out.println("삭제 실패");
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
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();

            }
            try{
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
