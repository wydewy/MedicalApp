package com.wydewy.medicalapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.wydewy.medicalapp.LoginActivity;
import com.wydewy.medicalapp.MedicalApplication;
import com.wydewy.medicalapp.R;

/**
 * Created by wangyuan on 2016/10/27.
 */

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}
