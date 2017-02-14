package com.computer.hdu.truckrental;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DriverChangePwdActivity extends AppCompatActivity {

    private EditText oldPwdEditText,newPwdEditText,confirmPwdEditText;
    private Button  confirmChangeButton;
    private String password= "123456789";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_change_pwd);

        oldPwdEditText = (EditText)findViewById(R.id.driver_old_pwd);
        newPwdEditText = (EditText)findViewById(R.id.driver_new_pwd);
        confirmPwdEditText = (EditText)findViewById(R.id.driver_confirm_pwd);
        confirmChangeButton = (Button)findViewById(R.id.confirm_change_btn);

        confirmPwdEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    //验证函数
                    attemptChange();
                    return true;
                }
                return false;
            }
        });

        confirmChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //验证函数
                attemptChange();
            }
        });

    }

    private void attemptChange(){

        oldPwdEditText.setError(null);
        newPwdEditText.setError(null);
        confirmPwdEditText.setError(null);


        String oldPwd = oldPwdEditText.getText().toString();
        String newPwd = newPwdEditText.getText().toString();
        String confirmPwd = confirmPwdEditText.getText().toString();
        View focusView = null;
        boolean cancel = false;

        if (TextUtils.isEmpty(confirmPwd)){
            confirmPwdEditText.setError(getString(R.string.prompt_input_pwd));
            focusView = confirmPwdEditText;
            cancel = true;
        }else if (!newPwd.equals(confirmPwd)){
            confirmPwdEditText.setError(getString(R.string.prompt_confirm_pwd_error));
            focusView = confirmPwdEditText;
            cancel = true;
        }

        if (TextUtils.isEmpty(newPwd)){
            newPwdEditText.setError(getString(R.string.prompt_input_pwd));
            focusView = newPwdEditText;
            cancel = true;
        }else if(!isPasswordValid(newPwd)){
            newPwdEditText.setError(getString(R.string.prompt_pwd_invalid));
            focusView = newPwdEditText;
            cancel = true;
        }

        if (TextUtils.isEmpty(oldPwd)){
            oldPwdEditText.setError(getString(R.string.prompt_input_pwd));
            focusView = oldPwdEditText;
            cancel = true;
        }

        if (cancel){
            focusView.requestFocus();
        }else {
            if(password.equals(oldPwd)){
                password = newPwd;
                Toast.makeText(getApplicationContext(),"修改成功",Toast.LENGTH_SHORT).show();
                mHandler.postDelayed(r, 100);//延时
                finish();
            }else {
                oldPwdEditText.setError(getString(R.string.prompt_old_pwd_error));
                oldPwdEditText.requestFocus();
            }
        }
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    Handler mHandler = new Handler();
    Runnable r = new Runnable() {
        @Override
        public void run() {
            //do something
            //每隔1s循环执行run方法
            mHandler.postDelayed(this, 1500);
        }
    };
}
