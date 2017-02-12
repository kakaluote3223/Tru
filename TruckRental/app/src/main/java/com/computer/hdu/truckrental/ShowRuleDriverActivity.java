package com.computer.hdu.truckrental;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by Even on 2017/1/24.
 */

public class ShowRuleDriverActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.show_driver_rule);
    }
}
