package com.computer.hdu.truckrental;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by yjt on 2017/2/11.
 */

public class DriverPriceDetailsShowActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_show_price_details_driver);
    }
}
