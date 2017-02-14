package com.computer.hdu.truckrental;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DriverInfoShowActivity extends AppCompatActivity {

    private ListView driverInfoListView;
    private ArrayList<Map<String,Object>> driverInfoList = new ArrayList<Map<String, Object>>();
    private SimpleAdapter driverInfoAdapter;
    private String[] driverInfoTitle ={"我的姓名","手机号码","驾驶车型","所在城市","车牌号","驾驶证号","目前等级"};
    private String[] driverInfoContent ={"yjt","123456789","小汽车","浙江杭州","并没有","我的身份证号","第五等级"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_driver_info_show);

        driverInfoListView = new ListView(this);
        //driverInfoListView = (ListView)findViewById(R.id.driver_info_ListView);
        for(int i=0;i<driverInfoTitle.length;i++){
            Map<String,Object> item = new HashMap<String, Object>();
            item.put("标题", driverInfoTitle[i]);
            item.put("内容", driverInfoContent[i]);
            driverInfoList.add(item);
        }

        driverInfoAdapter = new SimpleAdapter(this, driverInfoList, R.layout.listview_driver_info,
                new String[]{"标题","内容"}, new int[]{R.id.driver_info_title, R.id.driver_info_content});
        driverInfoListView.setAdapter(driverInfoAdapter);
        setContentView(driverInfoListView);
    }
}
