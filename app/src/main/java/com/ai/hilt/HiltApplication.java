package com.ai.hilt;

import android.app.Application;


import androidx.annotation.NonNull;
import androidx.camera.core.CameraXConfig;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class HiltApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }


}
