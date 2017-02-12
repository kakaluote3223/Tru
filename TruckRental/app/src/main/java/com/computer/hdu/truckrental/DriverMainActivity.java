package com.computer.hdu.truckrental;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.computer.hdu.truckrental.beans.Driver;
import com.computer.hdu.truckrental.beans.Order;
import com.computer.hdu.truckrental.utils.MyAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjt on 2017/2/8.
 */

public class DriverMainActivity extends AppCompatActivity {
    private Toolbar driverToolbar;
    private DrawerLayout driverDrawerLayout;
    private ActionBarDrawerToggle driverDrawerToggle;
    private ListView driverLeftMenu;
    private String[] driverList = {"List Item 01", "List Item 02", "List Item 03", "List Item 04"};
    private ArrayAdapter arrayAdapter;
    private Switch mySwitch;    //private SwitchCompat mySwitchCompat;
    private MyAdapter adapter;
    private ListView myListview;
    private List<Order> totalList = new ArrayList<>();
    private int totalNum,pageNum;
    private int currentPage = 1;
    private int pageSize = 10;
    private boolean isDivPage;
    private static final String TAG = "DriverMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_driver);

        driverToolbar = (Toolbar) findViewById(R.id.toolbar_driver);
        driverLeftMenu = (ListView) findViewById(R.id.driver_left_menu);
        driverDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout_driver);
        mySwitch = (Switch) findViewById(R.id.switch1);     //mySwitchCompat = (SwitchCompat)findViewById(R.id.switch1);
        myListview = (ListView) findViewById(R.id.main_driver_list);

        adapter = new MyAdapter(this, totalList);
        myListview.setAdapter(adapter);

        setSupportActionBar(driverToolbar);
        //driverToolbar.setLogo(R.drawable.ic_register_van);
        /*driverToolbar.setTitle("driverToolbar");//设置Toolbar标题
        Log.d(TAG, "onCreate: setTitle");
        Log.d(TAG, "onCreate: setSupportActionBar");
        getSupportActionBar().setHomeButtonEnabled(true);//设置返回键可用
        Log.d(TAG, "onCreate: setHomeButtonEnabled");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true); //创建返回键，并实现打开关/闭监听
        Log.d(TAG, "onCreate: getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);");
        driverDrawerToggle = new ActionBarDrawerToggle(this,driverDrawerLayout,driverToolbar,R.string.drawer_open,R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        driverDrawerToggle.syncState();
        driverDrawerLayout.addDrawerListener(driverDrawerToggle);
        //设置菜单列表
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, driverList);
        driverLeftMenu.setAdapter(arrayAdapter);*/

        /**
         * switch点击事件
         */
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    put_info_list();
                    adapter.notifyDataSetChanged();
                } else {
                    currentPage = 1;
                    totalList.clear();
                    adapter.notifyDataSetChanged();
                }
            }
        });

        /**
         * listview点击事件
         */
        myListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                ListView listView = (ListView) parent;
                Order order = (Order) listView.getItemAtPosition(position);

                Bundle bundle = new Bundle();
                put_info_Bundle(bundle, order);
                Intent intent = new Intent(DriverMainActivity.this, ShowDetailsOrderActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

                /*
                    //当前传入的是错误数据,传回电话号码
                    final String phone_num = order.getOrder_departure();

                    //1.在item点击里面写按钮监听事件
                    //2.在myadapter里面写按钮监听时间,这个方法不是很灵敏，不知道为什么放弃先

                    view.findViewById(R.id.phone_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(),"打电话功能",Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onClick: 打电话功能"+position);
                        //call_user(phone_num);
                    }
                });*/
            }
        });

        //需要从数据库获得符合条件订单条数
        /*totalNum = DbManager.getDataCount(db,Constant.TABLE_NAME);
        pageNum = (int) Math.ceil(totalNum/(double)pageSize);
        if(currentPage == 1){
            //添加数据
            totalList = DbManager.getListByCurrentPage(db,Constant.TABLE_NAME,currentPage,pageSize);
        }*/
        /**
         * listview滚动监听,应添加在switch的checked中
         */
        /*myListview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                if(isDivPage && AbsListView.OnScrollListener.SCROLL_STATE_IDLE == scrollState){
                    if (currentPage < pageNum){
                        currentPage++;
                        //数据添加
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                isDivPage = ((firstVisibleItem+visibleItemCount) == totalItemCount);
            }
        });*/
    }

    /*public void call_user(String user_phone) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + user_phone));
        try {
            startActivity(intent);
            //需要的操作
            //DoSomething();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }*/

    public void show_orders(View view){
        switch (view.getId())
        {
            case R.id.driver_orders_btn:
                Intent intent = new Intent(DriverMainActivity.this, ShowDriverAllOrdersActivity.class);
                startActivity(intent);
                break;
            case R.id.driver_record_btn:
                intent = new Intent(DriverMainActivity.this, ShowDriverCompletedOrdersActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void put_info_Bundle(Bundle bundle, Order order){
        bundle.putString("运货时间", order.getOrder_start_date());
        bundle.putString("下单时间", order.getOrder_date());
        bundle.putString("订单号", order.getOrder_number());
        bundle.putString("出发地址", order.getOrder_departure());
        bundle.putString("目的地址", order.getOrder_destination());
        bundle.putString("备注", order.getOrder_remarks());
        bundle.putFloat("路程数", order.getOrder_distance());
        bundle.putFloat("金额", order.getOrder_price());
        bundle.putInt("是否回程", order.getOrder_back());
        bundle.putInt("是否搬运", order.getOrder_carry());
        bundle.putInt("跟车人数", order.getOrder_followers());
    }

    private void put_info_list(){
        Order order1 = new Order();
        order1.setOrder_start_date("2017/2/10");
        order1.setFk_user_id(1);
        order1.setOrder_departure("兰溪");
        order1.setOrder_destination("金华");
        order1.setOrder_date("2017/2/10");
        order1.setOrder_number("111");
        order1.setOrder_remarks("无");
        order1.setOrder_distance(12);
        order1.setOrder_price(54);
        order1.setOrder_back(1);
        order1.setOrder_carry(1);
        order1.setOrder_followers(2);
        totalList.add(order1);

        Order order2 = new Order();
        order2.setOrder_start_date("2017/2/11");
        order2.setFk_user_id(2);
        order2.setOrder_departure("杭州");
        order2.setOrder_destination("江西");
        order2.setOrder_date("2017/2/10");
        order2.setOrder_number("111");
        order2.setOrder_remarks("无");
        order2.setOrder_distance(12);
        order2.setOrder_price(54);
        order2.setOrder_back(1);
        order2.setOrder_carry(1);
        order2.setOrder_followers(2);
        totalList.add(order2);
        return;
    }
}
