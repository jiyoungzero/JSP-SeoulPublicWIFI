package wifiDb;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.lang.*;

public class OpenAPI { // 오픈 api json파일을 읽어 자바 배열로 변환하기

    public ArrayList<List<Object>> getAllWifi(){
        Gson gson = new Gson();

        ArrayList<List<Object>> result = new ArrayList<>();

        // 변수에 들어갈 값
        List x_swifi_inout_door_list = new ArrayList<String>();
        List x_swifi_instl_floor_list = new ArrayList<String>();
        List x_swifi_instl_mby_list = new ArrayList<String>();
        List x_swifi_remars3_list = new ArrayList<String>();
        List x_swifi_instl_ty_list = new ArrayList<String>();
        List x_swifi_mgr_no_list = new ArrayList<String>();
        List x_swifi_wrdofc_list = new ArrayList<String>();
        List x_swifi_adres1_list = new ArrayList<String>();
        List x_swifi_adres2_list = new ArrayList<String>();
        List x_swifi_cmcwr_list = new ArrayList<String>();
        List work_dttm_list = new ArrayList<String>();
        List x_swifi_svc_se_list = new ArrayList<String>();
        List x_swifi_main_nm_list = new ArrayList<String>();
        List lnt_list = new ArrayList<String>();
        List x_swifi_cnstc_year_list = new ArrayList<String>();
        List lat_list = new ArrayList<String>();


        try {
            JsonObject allWifi = gson.fromJson(new FileReader("C://Users//JYLEE//Desktop//Mission1_소스코드_이지영//src//wifi.json"), JsonObject.class);

            JsonArray dataArray = allWifi.getAsJsonArray("DATA");


            JSONParser jsonParser = new JSONParser();
            for(int i=0;i<dataArray.size();i++){
                JsonObject data = (JsonObject) dataArray.get(i);
                x_swifi_inout_door_list.add(data.get("x_swifi_inout_door").getAsString());
                x_swifi_instl_floor_list.add(data.get("x_swifi_instl_floor").getAsString());
                x_swifi_instl_mby_list.add(data.get("x_swifi_instl_mby").getAsString());
                x_swifi_remars3_list.add(data.get("x_swifi_remars3").getAsString());
                x_swifi_instl_ty_list.add(data.get("x_swifi_instl_ty").getAsString());
                x_swifi_mgr_no_list.add(data.get("x_swifi_mgr_no").getAsString());
                x_swifi_wrdofc_list.add(data.get("x_swifi_wrdofc").getAsString());
                x_swifi_adres1_list.add(data.get("x_swifi_adres1").getAsString());
                x_swifi_adres2_list.add(data.get("x_swifi_adres2").getAsString());
                x_swifi_cmcwr_list.add(data.get("x_swifi_cmcwr").getAsString());
                work_dttm_list.add(data.get("work_dttm").getAsString());
                x_swifi_svc_se_list.add(data.get("x_swifi_svc_se").getAsString());
                x_swifi_main_nm_list.add(data.get("x_swifi_main_nm").getAsString());
                lnt_list.add(data.get("lat").getAsDouble());
                x_swifi_cnstc_year_list.add(data.get("x_swifi_cnstc_year").getAsString());
                lat_list.add(data.get("lnt").getAsDouble());

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        result.add(x_swifi_inout_door_list);
        result.add(x_swifi_instl_floor_list);
        result.add(x_swifi_instl_mby_list);
        result.add(x_swifi_remars3_list);
        result.add(x_swifi_instl_ty_list);
        result.add(x_swifi_mgr_no_list);
        result.add(x_swifi_wrdofc_list);
        result.add(x_swifi_adres1_list);
        result.add(x_swifi_adres2_list);
        result.add(x_swifi_cmcwr_list);
        result.add(work_dttm_list);
        result.add(x_swifi_svc_se_list);
        result.add(x_swifi_main_nm_list);
        result.add(lnt_list);
        result.add(x_swifi_cnstc_year_list);
        result.add(lat_list);
//        System.out.println(result.get(10));

        return result;

    }


}

