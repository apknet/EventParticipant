package com.ep.eventparticipant.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ep.eventparticipant.MyApplication;
import com.ep.eventparticipant.R;
import com.ep.eventparticipant.others.VerifyCodeManager;

public class FindPsw extends AppCompatActivity {
    private EditText edit_phone_num;
    private EditText edit_code;
    private Button btn_nextstep;
    private Button btn_getcode;
    private EditText edit_password;
    private VerifyCodeManager codeManager;
    private TextView tv_back,tv_tourist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_psw);
        getWindow().setStatusBarColor(Color.WHITE);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        codeManager = new VerifyCodeManager(this, edit_code, btn_nextstep);
        init();
    }


    private void init() {
        edit_code = findViewById(R.id.edit_code);
        edit_phone_num = findViewById(R.id.et_phone_num);
        btn_getcode = findViewById(R.id.get_verificationcode);

       edit_password=findViewById(R.id.edit_psw);

        tv_back=findViewById(R.id.tv_back);
        tv_tourist=findViewById(R.id.tourist);
        tv_tourist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FindPsw.this,MainActivity.class);
                startActivity(intent);
            }
        });
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               FindPsw.this.finish();
            }
        });
      /*  btn_nextstep = findViewById(R.id.nextstep);
        btn_nextstep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FindPsw.this,LoginActivity.class);
                startActivity(intent);
            }
        });*/
       // getVerifiCodeButton = getView(R.id.btn_send_verifi_code);
        //  getVerifiCodeButton.setOnClickListener(this);
        //phoneEdit = getView(R.id.et_phone);
        edit_phone_num.setImeOptions(EditorInfo.IME_ACTION_NEXT);// 下一步
        //  verifyCodeEdit = getView(R.id.et_verifiCode);
        edit_code.setImeOptions(EditorInfo.IME_ACTION_NEXT);// 下一步
        // passwordEdit = getView(R.id.et_password);
        btn_getcode.setImeOptions(EditorInfo.IME_ACTION_DONE);
        btn_getcode.setImeOptions(EditorInfo.IME_ACTION_GO);

        btn_getcode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_DONE
                        || actionId == EditorInfo.IME_ACTION_GO) {
                    commit();
                }
                return false;
            }
        });

    }



    private void commit() {
        String phone = edit_phone_num.getText().toString().trim();
        String password = edit_password.getText().toString().trim();
        String code = edit_code.getText().toString().trim();

        if (checkInput(phone, password, code)) {
            // TODO:请求服务端注册账号
        }
    }

    private boolean checkInput(String phone, String password, String code) {
        if (TextUtils.isEmpty(phone)) { // 电话号码为空
            Toast.makeText(MyApplication.getContext(), "电话号码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            if (phone.length() < 11 || phone.length() > 11) {
                Toast.makeText(MyApplication.getContext(), "电话号码格式不对", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(code)) { // 验证码不正确
                Toast.makeText(MyApplication.getContext(), "验证码错误", Toast.LENGTH_SHORT).show();
            } else if (password.length() < 6 || password.length() > 32
                    || TextUtils.isEmpty(password)) { // 密码格式
                Toast.makeText(MyApplication.getContext(), "密码格式不对", Toast.LENGTH_SHORT).show();
            } else {
                return true;
            }
        }
        return false;
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_verificationcode:
                // TODO 请求接口发送验证码
                codeManager.getVerifyCode(VerifyCodeManager.RESET_PWD);
                break;

            default:
                break;
        }
    }
}
