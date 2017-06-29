package com.js.graphicdisplay.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.js.graphicdisplay.R;
import com.js.graphicdisplay.activity.base.BaseActivity;
import com.js.graphicdisplay.data.NameValuePair;
import com.js.graphicdisplay.net.HttpManager;
import com.js.graphicdisplay.util.StringUtil;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginActivity extends BaseActivity {

    private final String TAG = "LoginActivity";

    private EditText editAccount;
    private EditText editPassword;
    private CheckBox chkRemember;
    private Button btnLogin;

    private boolean isRemember;

    private String account;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editAccount = (EditText) findViewById(R.id.edit_account);
        editPassword = (EditText) findViewById(R.id.edit_pwd);
        chkRemember = (CheckBox) findViewById(R.id.chk_remember);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account = editAccount.getText().toString();
                password = editPassword.getText().toString();
                isRemember = chkRemember.isChecked();

                if (StringUtil.isNotEmpty(account) && StringUtil.isNotEmpty(password)) {
                    //请求登录
                    //znn 不会工厂模式，先手动new
                    ArrayList<NameValuePair<String, String>> list = new ArrayList<>();
                    list.add(new NameValuePair<>("account", account));
                    list.add(new NameValuePair<>("password", password));
//                    HttpManager.doPost("url", list, ContentType.KVP);

                    //错误还没有处理,callback的调用全部在子线程中
                    //错误信息只能这么处理
                    HttpManager.doGet("http://baidu.com",
                            new Callback() {
                                Message msg;

                                @Override
                                public void onFailure(Call call, IOException e) {
                                    e.printStackTrace();
                                    msg = Message.obtain();
                                    msg.what = MESSAGE_ERROR;
                                    mHandler.sendMessage(msg);
                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    if (response.isSuccessful()) {
                                        Log.d(TAG, "request successful");
                                        msg = Message.obtain();
                                        msg.what = MESSAGE_SUCCESS;
                                        msg.obj = response.body().string();
                                        mHandler.sendMessage(msg);
                                    } else {
                                        msg = Message.obtain();
                                        msg.what = MESSAGE_FAILED;
                                        msg.obj = response.body().string();
                                        mHandler.sendMessage(msg);
                                        //试一下response.message()是什么
                                        throw new IOException("Unexpected code " + response);
                                    }
                                }
                            });
                }
            }
        });

    }

    //activity暂时只根据这三种情况更新UI
    protected void handleUIMessage(Message msg) {
        switch (msg.what) {
            case MESSAGE_ERROR:
                Log.d(TAG, "MESSAGE_ERROR");
                break;
            case MESSAGE_FAILED:
                Log.d(TAG, "MESSAGE_FAILED");
                break;
            case MESSAGE_SUCCESS:
                Log.d(TAG, "MESSAGE_SUCCESS" + msg.obj.toString());
                //如果返回正确, 如果remember, 保存sp
                if (isRemember) {
                    SharedPreferences sp = getSharedPreferences("sp_graphicdisplay", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("account", account);
                    editor.putString("password", password);
                    editor.commit();
                }
                //跳转
                Intent intent = new Intent(this, GraphicActivity.class);
                startActivity(intent);
                break;
        }
    }

}
