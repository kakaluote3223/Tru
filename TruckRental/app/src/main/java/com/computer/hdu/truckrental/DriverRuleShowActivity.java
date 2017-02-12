package com.computer.hdu.truckrental;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Even on 2017/1/24.
 */

public class DriverRuleShowActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_show_rule_driver);
    }
}
