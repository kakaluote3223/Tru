package com.computer.hdu.truckrental;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by yjt on 2017/2/11.
 */

public class ShowDetailsPriceActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.show_price_details);
    }
}
