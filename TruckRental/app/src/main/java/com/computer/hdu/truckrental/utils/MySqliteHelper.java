package com.computer.hdu.truckrental.utils;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by yjt on 2017/2/3.
 */

public class MySqliteHelper extends SQLiteOpenHelper{

    //sql语句
    private static final String CREATE_USERS =  "create table if not exists "+ Constant.USERS +
            "(" + Constant.USER_ID +" Integer primary key autoincrement not null,"
            + Constant.USER_PHONE +" String not null unique,"
            + Constant.USER_LEVEL +" Integer not null)";
    private static final String CREATE_DRIVERS = "create table if not exists "+ Constant.DRIVERS +
            "(" + Constant.DRIVER_ID +" Integer primary key autoincrement not null,"
            + Constant.DRIVER_NAME +" String,"
            + Constant.DRIVER_PHONE +" String not null unique,"
            + Constant.DRIVER_PWD +" String not null,"
            + Constant.DRIVER_CAR_TYPE +" Integer not null,"
            + Constant.DRIVER_CITY +" String not null,"
            + Constant.DRIVER_LICENSE_PLATE +" String not null unique,"
            + Constant.DRIVER_LICENSE +" String not null ,"
            + Constant.DRIVER_LEVEL +" Integer not null,"
            + Constant.DRIVER_SCORE +" Integer not null,"
            + Constant.DRIVER_STATE +" Integer not null)";
    private static final String CREATE_ORDERS = "create table if not exists "+ Constant.ORDERS +
            "(" + Constant.ORDER_ID +" integer primary key autoincrement not null,"
            + Constant.ORDER_NUMBER +" string not null unique,"
            + Constant.FK_USER_ID +" integer not null,"
            + Constant.FK_DRIVER_ID +" integer not null,"
            + Constant.ORDER_DEPARTURE +" string not null,"
            + Constant.ORDER_DESTINATION +" string not null,"
            + Constant.ORDER_REMARKS +" string,"
            + Constant.ORDER_DISTANCE +" float not null,"
            + Constant.ORDER_PRICE +" float not null,"
            + Constant.ORDER_STATE +" integer not null,"
            + Constant.ORDER_SCORE +" integer,"
            + Constant.ORDER_DATE +" datetime not null,"
            + Constant.ORDER_CARRY +" int not null,"
            + Constant.ORDER_BACK +" int not null,"
            + Constant.ORDER_FOLLOWERS +" integer not null,"
            + Constant.ORDER_CAR_TYPE +" integer not null,"
            + Constant.ORDER_START_DATE + " datetime not null,"
            + "foreign key("+ Constant.FK_DRIVER_ID +") references "+ Constant.DRIVERS +"("+ Constant.DRIVER_ID+"),"
            + "foreign key("+ Constant.FK_USER_ID +") references "+ Constant.USERS +"("+ Constant.USER_ID +"))";
    private static final String DROP_USERS = "drop table if exists "+ Constant.USERS;
    private static final String DROP_DRIVERS = "drop table if exists "+ Constant.DRIVERS;
    private static final String DROP_ORDERS = "drop table if exists "+ Constant.ORDERS;

    /**
     *  构造函数
     */
    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySqliteHelper(Context context){
        super(context, Constant.DATABASE_NAME, null, Constant.DATABASE_VERSION);
    }

    /**
     * 创建数据库时调用
     * @param db 数据库对象
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS);
        db.execSQL(CREATE_DRIVERS);
        db.execSQL(CREATE_ORDERS);
    }

    /**
     * 数据库版本更新时使用
     * @param db 数据库对象
     * @param oldVersion 旧版本
     * @param newVersion 新版本
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,int  newVersion) {
        db.execSQL(DROP_USERS);
        db.execSQL(DROP_DRIVERS);
        db.execSQL(DROP_ORDERS);
        onCreate(db);
    }

    /**
     * 打开数据库时调用
     * @param db 数据库对象
     */
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
