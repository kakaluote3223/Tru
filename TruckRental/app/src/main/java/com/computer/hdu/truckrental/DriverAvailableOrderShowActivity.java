package com.computer.hdu.truckrental;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.computer.hdu.truckrental.beans.Order;
import com.computer.hdu.truckrental.utils.MyAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjt on 2017/2/8.
 */

public class DriverAvailableOrderShowActivity extends AppCompatActivity {
    //侧滑菜单
    private Toolbar driverToolbar;
    private DrawerLayout driverDrawerLayout;
    private ActionBarDrawerToggle driverDrawerToggle;
    private ListView driverLeftMenu;
    private String[] leftList = {"我的信息", "我的等级", "密码修改"};
    private ArrayAdapter leftAdapter;
    //switch
    private Switch mySwitch;    //private SwitchCompat mySwitchCompat;
    //下拉刷新
    private static final int REFRESH_COMPLETE = 0X110;
    private SwipeRefreshLayout driverSwipeLayout;
    //订单详情
    private MyAdapter myAdapter;
    private ListView myListview;
    private List<Order> totalList = new ArrayList<>();
    private int totalNum,pageNum;
    private int currentPage = 1;
    private int pageSize = 10;
    private boolean isDivPage;
    private static final String TAG = "DriverMainActivity";

    private Handler mHandler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
            switch (msg.what)
            {
                case REFRESH_COMPLETE:
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

                    myAdapter.notifyDataSetChanged();
                    driverSwipeLayout.setRefreshing(false);
                    break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_show_available_order_driver);

        driverToolbar = (Toolbar) findViewById(R.id.toolbar_driver);
        driverLeftMenu = (ListView) findViewById(R.id.driver_left_menu);
        driverDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout_driver);
        mySwitch = (Switch) findViewById(R.id.switch1);     //mySwitchCompat = (SwitchCompat)findViewById(R.id.switch1);

        myListview = (ListView) findViewById(R.id.main_driver_list);
        driverSwipeLayout = (SwipeRefreshLayout)findViewById(R.id.SwipeRefreshLayout_driver);
        myAdapter = new MyAdapter(this, totalList);
        myListview.setAdapter(myAdapter);


        //driverToolbar.setLogo(R.drawable.phone1);
        driverToolbar.setTitle("driverToolbar");
        driverToolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        //driverToolbar.setSubtitle("sub title");
        this.setSupportActionBar(driverToolbar);
        //driverToolbar.setNavigationIcon(R.drawable.phone2);

        getSupportActionBar().setHomeButtonEnabled(true);//设置返回键可用
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true); //创建返回键，并实现打开关/闭监听
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
        leftAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, leftList);
        driverLeftMenu.setAdapter(leftAdapter);

        /**
         * switch点击事件
         */
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    put_info_list();
                    myAdapter.notifyDataSetChanged();
                } else {
                    currentPage = 1;
                    totalList.clear();
                    myAdapter.notifyDataSetChanged();
                }
            }
        });

        /**
         * 下拉刷新
         */
        driverSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 1500);
            }
        });
        driverSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);

        driverLeftMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i)
                {
                    case 0:
                        Toast.makeText(getApplicationContext(),"我的信息",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(),"我的等级",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(),"密码修改",Toast.LENGTH_SHORT).show();
                        break;
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
                Intent intent = new Intent(DriverAvailableOrderShowActivity.this, DriverOrderDetailsShowActivity.class);
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
                Intent intent = new Intent(DriverAvailableOrderShowActivity.this, DriverRunningOrdersShowActivity.class);
                startActivity(intent);
                break;
            case R.id.driver_record_btn:
                intent = new Intent(DriverAvailableOrderShowActivity.this,DriverAllOrdersShowActivity.class);
                startActivity(intent);
                break;
        }
    }

    /*public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.driver_left_menu, menu);
        *//*MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sub_menu, menu);*//*
        return true;
    }*/

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
    }
}
