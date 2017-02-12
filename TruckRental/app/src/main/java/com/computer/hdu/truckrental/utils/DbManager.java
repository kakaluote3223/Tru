package com.computer.hdu.truckrental.utils;

import com.computer.hdu.truckrental.beans.*;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by yjt on 2017/2/3.
 */

public class DbManager {
    private static MySqliteHelper helper;
    private SQLiteDatabase db;
    private String tag = "DbManger";

    /**
     * 获得数据库对象
     * @param context 上下文对象
     * @return
     */
    public DbManager (Context context){
        if(helper == null){
            helper = new MySqliteHelper(context);
        }
    }

    /**************************Users表单操作**************************/
    /**
     * 用户注册
     * @param user 用户对象
     * @return 0成功，1空号，2重复申请，3其他错误
     */
    public int InsertUser(User user){

        if (false){
            return  1;
        }

        db = helper.getWritableDatabase();
        if (db.isOpen()){
            String sql ="insert into "+ Constant.USERS
                    +" ("+ Constant.USER_PHONE +","+ Constant.USER_LEVEL +") values(?,?)";
            db.execSQL(sql, new Object[]{user.getUser_phone(),5});
            db.close();
            return 0;
        } else{
            return 3;
        }
    }

    public boolean DeleteUser(int user_id) {
        db = helper.getWritableDatabase();
        if (db.isOpen()) {
            String sql = "delete from " + Constant.USERS + " where "+ Constant.USER_ID +" =?";
            db.execSQL(sql, new String[]{user_id+""});
            db.close();
            return true;
        }
        else{
            return false;
        }
    }

    public boolean UpdateUserLevel(int user_id,int level){
        if(level<0 ||level>5)
            return false;
        else {
            db = helper.getWritableDatabase();
            if (db.isOpen()){

                String sql ="update "+ Constant.USERS +
                            " set "+ Constant.USER_LEVEL +"=? " +
                            "where "+ Constant.USER_ID +"=?";
                db.execSQL(sql,new Object[]{level, user_id});
                db.close();
                return true;
            }else {
                return false;
            }
        }
    }

    public List<User> SelectUserById(int user_id){
        String sql = "select * from "+ Constant.USERS +" where "+ Constant.USER_ID +"=?";
        String[] selectionArgs = new String[]{user_id+""};
        List<User> userList = SelectUserBySql(sql, selectionArgs);
        return userList;
    }

    public List<User> SelectUserByPhone(String user_phone){
        String sql = "select * from "+ Constant.USERS +" where "+ Constant.USER_PHONE +"=?";
        String[] selectionArgs = new String[]{user_phone};
        List<User> userList = SelectUserBySql(sql, selectionArgs);
        //Log.d(tag,"----SelectUserByPhone----");
        return userList;
    }

    public List<User> SelectAllUser(){
        String sql = "select * from "+Constant.USERS;
        List<User> userList = SelectUserBySql(sql, null);
        return userList;
    }

    private List<User> SelectUserBySql(String sql, String[] selectionArgs){
        List<User> userList = new ArrayList<>();
        db = helper.getWritableDatabase();
        if (db.isOpen()){
            Cursor cursor = db.rawQuery(sql, selectionArgs);
            //Log.d("SelectUserBySql","----SelectUserBySql----");
            while (cursor.moveToNext()){    //从0开始循环
                int user_id = cursor.getInt(cursor.getColumnIndex(Constant.USER_ID));
                String user_phone = cursor.getString(cursor.getColumnIndex(Constant.USER_PHONE));
                int user_level = cursor.getInt(cursor.getColumnIndex(Constant.USER_LEVEL));
                User user = new User(user_id, user_phone, user_level);
                userList.add(user);
            }
            db.close();//必须要放在cursor操作之后
        }
        return userList;
    }

    /**************************Drivers表单操作**************************/
    /**
     * 司机注册
     * @param driver 司机对象
     * @return 0成功，1空号，2重复申请，3其他错误
     * 宏定义！！
     * Driver_state():0审核中，1休息中,2工作中，3被除名
     */
    public int InsertDriver(Driver driver){
        if(false){
            //电话号码是否存在
            return 1;
        }

        if(driver.getDriver_car_type()<1 || 4<driver.getDriver_car_type()){
            return 2;
        }
        //信用等级合法检验
        if(driver.getDriver_level()<1 || 5<driver.getDriver_level()){
            return 2;
        }
        //评分合法检验
        if(driver.getDriver_score()<1 || 100<driver.getDriver_score()){
            return 2;
        }
        //状态检验
        if(driver.getDriver_state()<0 || 3<driver.getDriver_state()){
            return 2;
        }
        /*
        有问题，什么判身份证重复，电话重复，每个单独写类是不明智的
        采用数据库表单操作抛出异常处理验证是否有效
         */
        db = helper.getWritableDatabase();
        if (db.isOpen()){
            String sql = "insert into "+ Constant.DRIVERS + " ("+
                    Constant.DRIVER_NAME + "," +
                    Constant.DRIVER_PHONE + "," +
                    Constant.DRIVER_PWD +"," +
                    Constant.DRIVER_CAR_TYPE +"," +
                    Constant.DRIVER_CITY +"," +
                    Constant.DRIVER_LICENSE_PLATE +"," +
                    Constant.DRIVER_LICENSE +"," +
                    Constant.DRIVER_LEVEL +"," +
                    Constant.DRIVER_SCORE +"," +
                    Constant.DRIVER_STATE +") " +
                    "values(?,?,?,?,?,?,?,?,?,?,)";
            db.execSQL(sql, new Object[]{
                    driver.getDriver_name(),
                    driver.getDriver_phone(),
                    driver.getDriver_pwd(),//MD5加密过后的数据
                    driver.getDriver_car_type(),
                    driver.getDriver_city(),
                    driver.getDriver_license_plate(),
                    driver.getDriver_license(),
                    5,//driver.getDriver_level(),
                    driver.getDriver_score(),
                    0,//driver.getDriver_state()
            });
            db.close();
            return 0;
        }else {
            return 3;
        }
    }

    public boolean DeleteDriver(int driver_id){
        db = helper.getWritableDatabase();
        if (db.isOpen()){
            String sql = "delete from "+ Constant.DRIVERS +" where " + Constant.DRIVER_ID + " =?";
            db.execSQL(sql, new Object[]{driver_id+""});
            db.close();
            return true;
        }else {
            return false;
        }
    }

    //这里后续可能用返回值返回错误类型
    public boolean UpdateDriverCarType(int driver_id, int driver_car_type){
        if (driver_car_type < 1 || 4 < driver_car_type) {
            return false;
        }
        String sql = "update "+ Constant.DRIVERS  +" set "+ Constant.DRIVER_CAR_TYPE  +"=? " +
                "where "+ Constant.DRIVER_ID +"=?";
        Object[] updateArgs = new Object[]{driver_car_type, driver_id};
        return UpdateDriverBySql(sql, updateArgs);
    }

    public boolean UpdateDriverLevel(int driver_id, int driver_level){
        if (driver_level < 1 || driver_level > 5){
            return false;
        }
        String sql = "update "+ Constant.DRIVERS +" set "+  Constant.DRIVER_LEVEL +" =? " +
                "where "+ Constant.DRIVER_ID +" =?";
        Object[] updateArgs = new Object[]{driver_level, driver_id};
        return UpdateDriverBySql(sql, updateArgs);
    }

    public boolean UpdateDriverScore(int driver_id, int driver_score){
        if (driver_score < 0 || driver_score >100){
            return false;
        }
        String sql = "update "+ Constant.DRIVERS +" set "+ Constant.DRIVER_SCORE +" =? " +
                "where "+ Constant.DRIVER_ID +" =?";
        Object[] updateArgs = new Object[]{driver_score, driver_id};
        return UpdateDriverBySql(sql, updateArgs);
    }

    public boolean UpdateDriverState(int driver_id, int driver_state){
        if (driver_state < 0 || 3 < driver_state){
            return false;
        }
        String sql = "update "+ Constant.DRIVERS +" set "+ Constant.DRIVER_STATE +" =? " +
                "where "+ Constant.DRIVER_STATE +" =?";
        Object[] updateArgs = new Object[]{driver_state, driver_id};
        return UpdateDriverBySql(sql,updateArgs);
    }

    private boolean UpdateDriverBySql(String sql, Object[] updateArgs){
        db = helper.getWritableDatabase();
        if (db.isOpen()){
            db.execSQL(sql,updateArgs);
            db.close();
            return true;
        }
        else {
            return false;
        }
    }

    public List<Driver> SelectDriverByPhone(String driver_phone){
        String sql = "select * from "+ Constant.DRIVERS +" where "+ Constant.DRIVER_PHONE +" =?";
        String[] selectionArgs = new String[]{driver_phone};
        List<Driver> driverList = SelectDriverBySql(sql, selectionArgs);
        return driverList;
    }

    public List<Driver> SelectDriverById(int driver_id){
        String sql = "select * from "+ Constant.DRIVERS +" where "+ Constant.DRIVER_ID +" =?";
        String[] selectionArgs = new String[]{driver_id+""};
        List<Driver> driverList = SelectDriverBySql(sql, selectionArgs);
        return driverList;
    }

    public List<Driver> SelectALLDrivers(){
        String sql = "select * from "+ Constant.DRIVERS;
        List<Driver> driverList = SelectDriverBySql(sql, null);
        return driverList;
    }

    private List<Driver> SelectDriverBySql(String sql, String[] selectionArgs){
        List<Driver> userList = new ArrayList<>();
        db = helper.getWritableDatabase();
        if (db.isOpen()){
            Cursor cursor = db.rawQuery(sql, selectionArgs);
            while (cursor.moveToNext()){
                int driver_id = cursor.getInt(cursor.getColumnIndex(Constant.DRIVER_ID));
                String driver_name = cursor.getString(cursor.getColumnIndex(Constant.DRIVER_NAME));
                String driver_phone = cursor.getString(cursor.getColumnIndex(Constant.DRIVER_PHONE));
                String driver_pwd = cursor.getString(cursor.getColumnIndex(Constant.DRIVER_PWD));
                int driver_car_type = cursor.getInt(cursor.getColumnIndex(Constant.DRIVER_CAR_TYPE));
                String driver_city = cursor.getString(cursor.getColumnIndex(Constant.DRIVER_CITY));
                String driver_license_plate = cursor.getString(cursor.getColumnIndex(Constant.DRIVER_LICENSE_PLATE));
                String driver_license = cursor.getString(cursor.getColumnIndex(Constant.DRIVER_LICENSE));
                int driver_level = cursor.getInt(cursor.getColumnIndex(Constant.DRIVER_LEVEL));
                int driver_score = cursor.getInt(cursor.getColumnIndex(Constant.DRIVER_SCORE));
                int driver_state = cursor.getInt(cursor.getColumnIndex(Constant.DRIVER_STATE));
                Driver driver = new Driver(driver_id, driver_name, driver_phone, driver_pwd,
                                        driver_car_type, driver_city, driver_license_plate, driver_license,
                                            driver_level, driver_score, driver_state);
                userList.add(driver);
                db.close();
            }
        }
        return userList;
    }

    /**************************Orders表单操作**************************/
    /**
     * 订单插入表单
     * @param order
     * @return 对应错误处理,暂定0为成功，1为失败
     */
    public int InsertOrder(Order order){
        //状态检验
        if(order.getOrder_state() < 0||4 < order.getOrder_state()){
            return 1;
        }
        //评分检验
        if(order.getOrder_score() < 1||100 < order.getOrder_score()){
            return 1;
        }
        //回城检验
        if(order.getOrder_back() < 0||1 < order.getOrder_back()){
            return 1;
        }
        //搬运检查
        if(order.getOrder_carry() < 0||1 < order.getOrder_carry()){
            return 1;
        }
        //车型检验,跟车人数检验
        switch (order.getOrder_car_type()){
            case 0://小型面包车
                if(order.getOrder_followers()!=0){
                    return 1;
                }
            case 1://中型面包车
                if (order.getOrder_followers()!=0){
                    return 1;
                }
            case 2://小型货车
                if(order.getOrder_followers()<0||2<order.getOrder_followers()){
                    return 1;
                }
            case 3://中型货车
                if(order.getOrder_followers()<0||3<order.getOrder_followers()){
                    return 1;
                }
        }

        //把String类型的日期转换成datetime类型的

        db = helper.getWritableDatabase();
        if (db.isOpen()){
            String sql = "insert into "+ Constant.ORDERS +" (" +
                    Constant.ORDER_NUMBER +"," +
                    Constant.FK_USER_ID +"," +
                    Constant.FK_DRIVER_ID +"," +
                    Constant.ORDER_DEPARTURE +"," +
                    Constant.ORDER_DESTINATION +"," +
                    Constant.ORDER_REMARKS +"," +
                    Constant.ORDER_DISTANCE +"," +
                    Constant.ORDER_PRICE +"," +
                    Constant.ORDER_STATE +"," +
                    Constant.ORDER_SCORE +"," +
                    Constant.ORDER_DATE +"," +
                    Constant.ORDER_BACK +"," +
                    Constant.ORDER_CARRY +"," +
                    Constant.ORDER_FOLLOWERS +"," +
                    Constant.ORDER_CAR_TYPE +"," +
                    Constant.ORDER_START_DATE +")";
            db.execSQL(sql, new Object[]{
                    order.getOrder_number(),
                    order.getFk_user_id(),
                    order.getFk_driver_id(),
                    order.getOrder_departure(),
                    order.getOrder_destination(),
                    order.getOrder_remarks(),
                    order.getOrder_distance(),
                    order.getOrder_price(),
                    order.getOrder_state(),
                    order.getOrder_score(),
                    order.getOrder_date(),//转换后的datetime型变量
                    order.getOrder_back(),
                    order.getOrder_carry(),
                    order.getOrder_followers(),
                    order.getOrder_car_type(),
                    order.getOrder_start_date()} );//转换后的datetime型变量}
            db.close();
            return 0;
        }
        return 1;
    }

    public boolean DeleteOrder(int order_id){
        db = helper.getWritableDatabase();
        if (db.isOpen()){
            String sql = "delete from "+ Constant.ORDERS +" where "+ Constant.ORDER_ID +" =?";
            db.execSQL(sql, new String[]{order_id+""});
            db.close();
            return true;
        }else{
            return false;
        }
    }

    /**
        0已接单，1为未接单，2用户取消，3司机取消，4已完成
        一.只改状态，二.改状态时添加司机信息
     */
    public boolean UpdateOrderState(int order_id, int state, int driver_id){
        String sql;
        Object[] updateArgs;
        if(state == 1){         //接单
            sql = "update "+ Constant.ORDERS +" set "+ Constant.ORDER_STATE+"=?,"+ Constant.DRIVER_ID +"=? " +
                    "where "+ Constant.DRIVER_ID +"=?";
            updateArgs = new Object[]{state, driver_id, order_id};
        }
        else {
            sql = "update "+ Constant.ORDERS +" set "+ Constant.DRIVER_STATE +" =? " +
                    "where "+ Constant.DRIVER_ID +" =?";
            updateArgs = new Object[]{state, order_id};
        }
        return UpdateDriverBySql(sql, updateArgs);
    }

    public boolean UpdateOrdersBySql(String sql, Object[] updateArgs){
        db = helper.getWritableDatabase();
        if (db.isOpen()){
            db.execSQL(sql, updateArgs);
            db.close();
            return true;
        }else {
            return false;
        }
    }

    public List<Order> selectOrderById(int order_id){
        String sql = "select * from "+ Constant.ORDERS +" where "+ Constant.ORDER_ID +" =?";
        String[] selectionArgs = new String[]{order_id+""};
        List<Order> orderList = SelectOrderBySql(sql, selectionArgs);
        return orderList;

    }

    public List<Order> SelectOrderBySql(String sql, String[] selectionArgs){
        List<Order> orderList = new ArrayList<>();
        db = helper.getWritableDatabase();
        if (db.isOpen()){
            Cursor cursor = db.rawQuery(sql, selectionArgs);
            //Log.d("SelectUserBySql","----SelectUserBySql----");
            while (cursor.moveToNext()){    //从0开始循环
                int order_id = cursor.getInt(cursor.getColumnIndex(Constant.ORDER_ID));
                String order_number = cursor.getString(cursor.getColumnIndex(Constant.ORDER_NUMBER));
                int fk_user_id = cursor.getInt(cursor.getColumnIndex(Constant.FK_USER_ID));
                int fk_driver_id = cursor.getInt(cursor.getColumnIndex(Constant.FK_DRIVER_ID));
                String order_departure = cursor.getString(cursor.getColumnIndex(Constant.ORDER_DEPARTURE));
                String order_destination = cursor.getString(cursor.getColumnIndex(Constant.ORDER_DESTINATION));
                String order_remarks = cursor.getString(cursor.getColumnIndex(Constant.ORDER_REMARKS));
                float order_distance = cursor.getFloat(cursor.getColumnIndex(Constant.ORDER_DISTANCE));
                float order_price = cursor.getFloat(cursor.getColumnIndex(Constant.ORDER_PRICE));
                int order_state = cursor.getInt(cursor.getColumnIndex(Constant.ORDER_STATE));
                int order_score = cursor.getInt(cursor.getColumnIndex(Constant.ORDER_SCORE));
                String order_date = cursor.getString(cursor.getColumnIndex(Constant.ORDER_DATE));
                int order_back = cursor.getInt(cursor.getColumnIndex(Constant.ORDER_BACK));
                int order_carry = cursor.getInt(cursor.getColumnIndex(Constant.ORDER_CARRY));
                int order_followers = cursor.getInt(cursor.getColumnIndex(Constant.ORDER_FOLLOWERS));
                int order_car_type = cursor.getInt(cursor.getColumnIndex(Constant.ORDER_CAR_TYPE));
                String order_start_date = cursor.getString(cursor.getColumnIndex(Constant.ORDER_START_DATE));
                Order order = new Order(order_id, order_number, fk_user_id, fk_driver_id, order_departure, order_destination, order_remarks, order_distance, order_price, order_state, order_score, order_date, order_back, order_carry, order_followers, order_car_type, order_start_date);
                orderList.add(order);
            }
            db.close();
        }
        return orderList;
    }

}
