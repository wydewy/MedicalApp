package com.wydewy.medicalapp;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.wydewy.medicalapp.bean.User;
import com.wydewy.medicalapp.databinding.ActivityLoginBinding;
import com.wydewy.medicalapp.util.Constant;
import com.wydewy.medicalapp.util.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private User user;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        setSupportActionBar(binding.toolbar);
        user = new User();
        binding.login.setUser(user);
        binding.login.setActivity(this);
    }

    public void handleClick(View view) {
        username = binding.login.usernameEt.getText().toString();
        password = binding.login.passwordEt.getText().toString();
        if (StringUtils.isEmpty(username)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (StringUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        lgoin(username, password);
    }


    private void lgoin(final String username, final String password) {
        MedicalApplication.getInstance().addLight(LoginActivity.this, 0 - Constant.LIGHT_CHANGE);

        StringRequest request = new StringRequest(Request.Method.POST, MedicalApplication.getInstance().host + Constant.API_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("TAG", "response：" + response);
                MedicalApplication.getInstance().addLight(LoginActivity.this, Constant.LIGHT_CHANGE);
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                SharedPreferences preferences = getApplicationContext().getSharedPreferences(Constant.USER_INFO, MODE_PRIVATE);
                preferences.edit().putBoolean(Constant.IS_LOG_IN, true);
                if(preferences.edit().commit()){
                    LoginActivity.this.finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                MedicalApplication.getInstance().addLight(LoginActivity.this, Constant.LIGHT_CHANGE);
                if (error.networkResponse != null) {
                    Log.d("TAG", "error：" + error.networkResponse.statusCode);
                } else {
                    Log.d("TAG", "error：" + error.toString());
                }

                Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("customername", username);
                map.put("password", password);
                return map;
            }
        };

        MedicalApplication.getInstance().addToRequestQueue(request);
    }

}
