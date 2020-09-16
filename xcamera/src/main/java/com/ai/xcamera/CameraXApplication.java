package com.ai.xcamera;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.camera.camera2.Camera2Config;
import androidx.camera.core.CameraXConfig;

public class CameraXApplication extends Application implements CameraXConfig.Provider {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("CameraXApp","on Create");
    }

    @NonNull
    @Override
    public CameraXConfig getCameraXConfig() {
        return Camera2Config.defaultConfig();
    }
}
