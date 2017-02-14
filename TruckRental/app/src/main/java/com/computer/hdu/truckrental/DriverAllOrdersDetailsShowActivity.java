package com.computer.hdu.truckrental;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DriverAllOrdersDetailsShowActivity extends Activity {

    private TextView ShowPriceTextView,ShowStartDateTextView,ShowDistanceTextView,ShowRemarksTextView,ShowStatementTextView;
    private TextView ShowDepartureTextView,ShowDestinationTextView;
    private TextView ShowCarryTextView,ShowBackTextView,ShowFollowersTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_all_orders_details_show);
        Bundle bundle = getIntent().getExtras();

        ShowPriceTextView = (TextView) findViewById(R.id.all_details_price);
        ShowStartDateTextView = (TextView) findViewById(R.id.all_details_start_date);
        ShowDistanceTextView = (TextView) findViewById(R.id.all_details_distance);
        ShowRemarksTextView = (TextView) findViewById(R.id.all_details_remarks);
        ShowStatementTextView = (TextView)findViewById(R.id.all_order_state);
        ShowDepartureTextView = (TextView) findViewById(R.id.all_details_departure);
        ShowDestinationTextView = (TextView) findViewById(R.id.all_details_destination);
        ShowCarryTextView = (TextView) findViewById(R.id.all_details_carry);
        ShowBackTextView = (TextView) findViewById(R.id.all_details_back);
        ShowFollowersTextView = (TextView) findViewById(R.id.all_details_followers);

        if (bundle.getInt("是否回程") == 1) {
            ShowBackTextView.setText(R.string.back);
        }
        if (bundle.getInt("是否搬运") == 1) {
            ShowCarryTextView.setText(R.string.carry);
        }

        //0进行中，1用户取消，2司机取消，3已完成
        switch (bundle.getInt("订单状态"))
        {
            case 0:
                ShowStatementTextView.setText(R.string.prompt_order_running);
                break;
            case 1:
                ShowStatementTextView.setText(R.string.prompt_order_user_cancel);
                break;
            case 2:
                ShowStatementTextView.setText(R.string.prompt_order_driver_cancel);
                break;
            case 3:
                ShowStatementTextView.setText(R.string.prompt_order_finished);
                break;
        }

        //tv_show_statement.setText(bundle.getInt("订单状态")+"");
        ShowStartDateTextView.setText(bundle.getString("运货时间"));
        ShowDistanceTextView.setText("" + bundle.getFloat("路程数") + "公里");
        ShowFollowersTextView.setText("跟车人数：" + Integer.toString(bundle.getInt("跟车人数")));
        ShowPriceTextView.setText("" + bundle.getFloat("金额") + "元");
        ShowDepartureTextView.setText(bundle.getString("出发地址"));
        ShowDestinationTextView.setText(bundle.getString("目的地址"));
        ShowRemarksTextView.setText("备注：" + bundle.getString("备注"));
    }
}
