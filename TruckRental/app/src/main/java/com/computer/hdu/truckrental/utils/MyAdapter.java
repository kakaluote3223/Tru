package com.computer.hdu.truckrental.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.computer.hdu.truckrental.R;
import com.computer.hdu.truckrental.beans.Order;
import java.util.List;

/**
 * Created by yjt on 2017/2/10.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Order> mOrder;
    private static final String TAG = "MyAdapter";

    public MyAdapter(Context context, List<Order> order) {
        this.context = context;
        mOrder = order;
    }

    @Override
    public int getCount() {
        return mOrder.size();
    }

    @Override
    public Object getItem(int position) {
        return mOrder.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.listveiw_driver,null);
            holder = new ViewHolder();
            holder.tv_departure_destination = (TextView) convertView.findViewById(R.id.departure_destination_view);
            holder.tv_start_date = (TextView) convertView.findViewById(R.id.order_start_date_view);
            holder.tv_user_level = (TextView) convertView.findViewById(R.id.user_level_view);
            holder.view_btn =  (ImageButton) convertView.findViewById(R.id.phone_btn);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_departure_destination.setText(mOrder.get(position).getOrder_departure()+"--->"+mOrder.get(position).getOrder_destination());
        holder.tv_start_date.setText(mOrder.get(position).getOrder_start_date());
        holder.tv_user_level.setText("用户id:"+mOrder.get(position).getFk_user_id()+"");

        holder.view_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*DriverMainActivity.call_user(mOrder.get(position).getFk_user_id()+"");

                //打电话给用户,电话号码未传进来
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+mOrder.get(position).getFk_user_id()+""));
                startActivity(intent);*/
                Toast.makeText(context,"点击了电话按钮",Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onClick:this is phone_btn"+position);
            }
        });

        return convertView;
    }

    static class ViewHolder{
        TextView tv_departure_destination,tv_start_date,tv_user_level;
        ImageButton view_btn;
    }
}
