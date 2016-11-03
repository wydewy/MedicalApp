package com.wydewy.medicalapp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.wydewy.medicalapp.util.Constant;

import org.litepal.LitePalApplication;

/**
 * Created by wangyuan on 2016/11/2.
 */

public class MedicalApplication extends LitePalApplication {
    public String host = "http://172.20.18.123:8080";

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public boolean checkLogin() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(Constant.USER_INFO, MODE_PRIVATE);
        return preferences.getBoolean(Constant.IS_LOG_IN, false);
    }

    private static final String TAG = MedicalApplication.class.getSimpleName();

    private RequestQueue mRequestQueue;

    private static MedicalApplication mInstance;


    public static synchronized MedicalApplication getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public Context getGlobalContext() {
        return getApplicationContext();
    }


    public void addLight(Activity context, int brightness) {
        mInstance.setLight(context, (int) (mInstance.getLight(context)+100));
    }

    public void setLight(Activity context, int brightness) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.screenBrightness = Float.valueOf(brightness) * (1f / 255f);
        context.getWindow().setAttributes(lp);
    }

    public float getLight(Activity context) {
        return context.getWindow().getAttributes().screenBrightness;
    }
}
