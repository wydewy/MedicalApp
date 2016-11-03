package com.wydewy.medicalapp.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by wangyuan on 2016/11/2.
 */

public class User extends BaseObservable {
    String username;//客户名字
    String password;//:密码

    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }
}
