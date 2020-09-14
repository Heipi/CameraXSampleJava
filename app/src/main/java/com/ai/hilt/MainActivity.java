package com.ai.hilt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ai.hilt.databinding.ActivityMainBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding  =  ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);



    }








}
