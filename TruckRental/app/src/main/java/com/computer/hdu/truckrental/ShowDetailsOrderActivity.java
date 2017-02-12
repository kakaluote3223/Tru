package com.computer.hdu.truckrental;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by yjt on 2017/2/10.
 */

public class ShowDetailsOrderActivity extends Activity{
    private TextView tv_show_price,tv_show_startDate,tv_show_distance,tv_show_remarks,tv_show_statement;
    private TextView tv_show_departure,tv_show_destination;
    private TextView tv_show_carry,tv_show_back,tv_show_followers;
    private static final String TAG = "ShowDetailsOrderActivit";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.show_order_details);
        Bundle bundle = getIntent().getExtras();

        Log.d(TAG, "onCreate: getBundle");
        tv_show_price = (TextView)findViewById(R.id.details_price);
        tv_show_startDate = (TextView)findViewById(R.id.details_start_date);
        tv_show_distance = (TextView)findViewById(R.id.details_distance);
        tv_show_remarks = (TextView)findViewById(R.id.details_remarks);
        //tv_show_statement = (TextView)findViewById(R.id.details_statement);
        tv_show_departure = (TextView)findViewById(R.id.details_departure);
        tv_show_destination = (TextView)findViewById(R.id.details_destination);
        tv_show_carry = (TextView)findViewById(R.id.details_carry);
        tv_show_back = (TextView)findViewById(R.id.details_back);
        tv_show_followers = (TextView)findViewById(R.id.details_followers);
        Log.d(TAG, "onCreate: findViewById");
        
        if(bundle.getInt("是否回程") == 1){
            tv_show_back.setText(R.string.back);
        }
        if(bundle.getInt("是否搬运") == 1){
            tv_show_carry.setText(R.string.carry);
        }
        
        tv_show_startDate.setText(bundle.getString("运货时间"));
        tv_show_distance.setText(""+bundle.getFloat("路程数")+"公里");
        tv_show_followers.setText("跟车人数："+Integer.toString(bundle.getInt("跟车人数")));
        tv_show_price.setText(""+bundle.getFloat("金额")+"元");
        tv_show_departure.setText(bundle.getString("出发地址"));
        tv_show_destination.setText(bundle.getString("目的地址"));
        tv_show_remarks.setText("备注："+bundle.getString("备注"));
        Log.d(TAG, "onCreate: setText");
    }

    public void btn_operate(View view){
        switch (view.getId())
        {
            case R.id.btn_price_details:
                Intent intent = new Intent(ShowDetailsOrderActivity.this,ShowDetailsPriceActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_take_order:
                //跳转订单进行页面
                intent = new Intent(ShowDetailsOrderActivity.this,ShowDetailsPriceActivity.class);
                startActivity(intent);
                break;
        }
    }
}
