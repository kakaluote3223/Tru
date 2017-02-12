package com.computer.hdu.truckrental.beans;

/**
 * Created by yjt on 2017/2/4.
 */

public class Order {
    private int Order_id;
    private String Order_number;
    private int Fk_user_id;
    private int Fk_driver_id;
    private String Order_departure;
    private String Order_destination;
    private String Order_remarks;
    private float Order_distance;
    private float Order_price;
    private int Order_state;
    private int Order_score;
    private String Order_date;
    private int Order_back;
    private int Order_carry;
    private int Order_followers;
    private int Order_car_type;
    private String Order_start_date;

    public Order() {
    }

    public Order(int order_id, String order_number, int fk_user_id, int fk_driver_id, String order_departure, String order_destination, String order_remarks, float order_distance, float order_price, int order_state, int order_score, String order_date, int order_back, int order_carry, int order_followers, int order_car_type, String order_start_date) {
        Order_id = order_id;
        Order_number = order_number;
        Fk_user_id = fk_user_id;
        Fk_driver_id = fk_driver_id;
        Order_departure = order_departure;
        Order_destination = order_destination;
        Order_remarks = order_remarks;
        Order_distance = order_distance;
        Order_price = order_price;
        Order_state = order_state;
        Order_score = order_score;
        Order_date = order_date;
        Order_back = order_back;
        Order_carry = order_carry;
        Order_followers = order_followers;
        Order_car_type = order_car_type;
        Order_start_date = order_start_date;
    }

    public int getOrder_id() {
        return Order_id;
    }


    public String getOrder_number() {
        return Order_number;
    }

    public void setOrder_number(String order_number) {
        Order_number = order_number;
    }

    public int getFk_user_id() {
        return Fk_user_id;
    }

    public void setFk_user_id(int fk_user_id) {
        Fk_user_id = fk_user_id;
    }

    public int getFk_driver_id() {
        return Fk_driver_id;
    }

    public void setFk_driver_id(int fk_driver_id) {
        Fk_driver_id = fk_driver_id;
    }

    public String getOrder_departure() {
        return Order_departure;
    }

    public void setOrder_departure(String order_departure) {
        Order_departure = order_departure;
    }

    public String getOrder_destination() {
        return Order_destination;
    }

    public void setOrder_destination(String order_destination) {
        Order_destination = order_destination;
    }

    public String getOrder_remarks() {
        return Order_remarks;
    }

    public void setOrder_remarks(String order_remarks) {
        Order_remarks = order_remarks;
    }

    public float getOrder_distance() {
        return Order_distance;
    }

    public void setOrder_distance(float order_distance) {
        Order_distance = order_distance;
    }

    public float getOrder_price() {
        return Order_price;
    }

    public void setOrder_price(float order_price) {
        Order_price = order_price;
    }

    public int getOrder_state() {
        return Order_state;
    }

    public void setOrder_state(int order_state) {
        Order_state = order_state;
    }

    public int getOrder_score() {
        return Order_score;
    }

    public void setOrder_score(int order_score) {
        Order_score = order_score;
    }

    public String getOrder_date() {
        return Order_date;
    }

    public void setOrder_date(String order_date) {
        Order_date = order_date;
    }

    public int getOrder_back() {
        return Order_back;
    }

    public int getOrder_carry() {
        return Order_carry;
    }

    public void setOrder_back(int order_back) {
        Order_back = order_back;
    }

    public void setOrder_carry(int order_carry) {
        Order_carry = order_carry;
    }

    public int getOrder_followers() {
        return Order_followers;
    }

    public void setOrder_followers(int order_followers) {
        Order_followers = order_followers;
    }

    public void setOrder_id(int order_id) {
        Order_id = order_id;
    }

    public int getOrder_car_type() {
        return Order_car_type;
    }

    public void setOrder_car_type(int order_car_type) {
        Order_car_type = order_car_type;
    }

    public String getOrder_start_date() {
        return Order_start_date;
    }

    public void setOrder_start_date(String order_start_date) {
        Order_start_date = order_start_date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "Order_id=" + Order_id +
                ", Order_number='" + Order_number + '\'' +
                ", Fk_user_id=" + Fk_user_id +
                ", Fk_driver_id=" + Fk_driver_id +
                ", Order_departure='" + Order_departure + '\'' +
                ", Order_destination='" + Order_destination + '\'' +
                ", Order_remarks='" + Order_remarks + '\'' +
                ", Order_distance=" + Order_distance +
                ", Order_price=" + Order_price +
                ", Order_state=" + Order_state +
                ", Order_score=" + Order_score +
                ", Order_date='" + Order_date + '\'' +
                ", Order_back=" + Order_back +
                ", Order_carry=" + Order_carry +
                ", Order_followers=" + Order_followers +
                ", Order_car_type=" + Order_car_type +
                ", Order_start_date='" + Order_start_date + '\'' +
                '}';
    }
}
