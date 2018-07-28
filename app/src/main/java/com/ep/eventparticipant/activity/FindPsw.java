package com.ep.eventparticipant.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.ep.eventparticipant.R;
public class FindPsw extends AppCompatActivity {
  private EditText edit_phone_num;
    private EditText edit_code;
    private Button btn_nextstep;
    private Button btn_getcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_psw);

        init();
    }

    private void init() {
        edit_code=findViewById(R.id.edit_code);
        edit_phone_num=findViewById(R.id.et_phone_num);
        btn_getcode=findViewById(R.id.get_verificationcode);
        btn_nextstep=findViewById(R.id.nextstep);
    }
}
