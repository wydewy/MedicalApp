package com.wydewy.medicalapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.wydewy.medicalapp.databinding.ActivitySettingsBinding;
import com.wydewy.medicalapp.util.StringUtils;

public class SettingsActivity extends AppCompatActivity {

    ActivitySettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings);
        setSupportActionBar(binding.toolbar);
        binding.settings.setActivity(this);
    }

    public void handleClick(View view) {
        final EditText editText = new EditText(this);
        new AlertDialog.Builder(this).setTitle(getString(R.string.app_name))
                .setIcon(R.mipmap.ic_launcher)
                .setTitle(getString(R.string.app_name))
                .setView(editText)
                .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String host = editText.getText().toString();
                        if(StringUtils.isEmpty(host)){
                           MedicalApplication.getInstance().host = host;
                        }
                    }
                }).
                setNegativeButton(getString(R.string.cancel), null).show();
    }

}
