package com.example.ap04sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import java.util.prefs.Preferences;

import static android.content.Context.MODE_PRIVATE;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private SharedPreferences pref;
    Button btnDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDemo = findViewById(R.id.btn_demo);
        btnDemo.setVisibility(View.INVISIBLE);
        btnDemo.setClickable(false);

        // cách 1 ko cần quan tâm đến tên file
        //pref = PreferenceManager.getDefaultSharedPreferences(this);


        // cách 2 tạo file với tên file
        pref = this.getSharedPreferences("demo", Context.MODE_PRIVATE);

        // đăng ký lắng nghe sự kiện thay đôi

        pref.registerOnSharedPreferenceChangeListener(this);

        checkFirstTime();

    }

    private void checkFirstTime() {

        SharedPreferences.Editor editor = pref.edit();
        if (pref.getBoolean("is_first",true)){
            editor.putBoolean("is_first",false);

            btnDemo.setVisibility(View.VISIBLE);
            btnDemo.setClickable(true);

            editor.apply();
        }


    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }

    @Override
    protected void onDestroy() {
        pref.unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
    }
}
