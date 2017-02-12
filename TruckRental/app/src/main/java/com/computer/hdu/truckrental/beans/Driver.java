package com.computer.hdu.truckrental.beans;

/**
 * Created by yjt on 2017/2/4.
 */

public class Driver {
    private int Driver_id;
    private String Driver_name;
    private String Driver_phone;
    private String Driver_pwd;
    private int Driver_car_type;
    private String Driver_city;
    private String Driver_license_plate;
    private String Driver_license;
    private int Driver_level;
    private int Driver_score;
    private int Driver_state;

    public Driver(int driver_id, String driver_name, String driver_phone, String driver_pwd, int driver_car_type, String driver_city, String driver_license_plate, String driver_license, int driver_level, int driver_score, int driver_state) {
        Driver_id = driver_id;
        Driver_name = driver_name;
        Driver_phone = driver_phone;
        Driver_pwd = driver_pwd;
        Driver_car_type = driver_car_type;
        Driver_city = driver_city;
        Driver_license_plate = driver_license_plate;
        Driver_license = driver_license;
        Driver_level = driver_level;
        Driver_score = driver_score;
        Driver_state = driver_state;
    }

    public int getDriver_id() {
        return Driver_id;
    }


    public String getDriver_name() {
        return Driver_name;
    }

    public void setDriver_name(String driver_name) {
        Driver_name = driver_name;
    }

    public String getDriver_phone() {
        return Driver_phone;
    }

    public void setDriver_phone(String driver_phone) {
        Driver_phone = driver_phone;
    }

    public String getDriver_pwd() {
        return Driver_pwd;
    }

    public void setDriver_pwd(String driver_pwd) {
        Driver_pwd = driver_pwd;
    }

    public int getDriver_car_type() {
        return Driver_car_type;
    }

    public void setDriver_car_type(int driver_car_type) {
        Driver_car_type = driver_car_type;
    }

    public String getDriver_city() {
        return Driver_city;
    }

    public void setDriver_city(String driver_city) {
        Driver_city = driver_city;
    }

    public String getDriver_license_plate() {
        return Driver_license_plate;
    }

    public void setDriver_license_plate(String driver_license_plate) {
        Driver_license_plate = driver_license_plate;
    }

    public String getDriver_license() {
        return Driver_license;
    }

    public void setDriver_license(String driver_license) {
        Driver_license = driver_license;
    }

    public int getDriver_level() {
        return Driver_level;
    }

    public void setDriver_level(int driver_level) {
        Driver_level = driver_level;
    }

    public int getDriver_score() {
        return Driver_score;
    }

    public void setDriver_score(int driver_score) {
        Driver_score = driver_score;
    }

    public int getDriver_state() {
        return Driver_state;
    }

    public void setDriver_state(int driver_state) {
        Driver_state = driver_state;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "Driver_id=" + Driver_id +
                ", Driver_name='" + Driver_name + '\'' +
                ", Driver_phone='" + Driver_phone + '\'' +
                ", Driver_pwd='" + Driver_pwd + '\'' +
                ", Driver_car_type=" + Driver_car_type +
                ", Driver_city='" + Driver_city + '\'' +
                ", Driver_license_plate='" + Driver_license_plate + '\'' +
                ", Driver_license='" + Driver_license + '\'' +
                ", Driver_level=" + Driver_level +
                ", Driver_score=" + Driver_score +
                ", Driver_state=" + Driver_state +
                '}';
    }
}
