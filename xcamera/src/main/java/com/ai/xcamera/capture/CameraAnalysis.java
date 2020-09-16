package com.ai.xcamera.capture;

import android.util.Size;

import androidx.camera.core.ImageAnalysis;

public class CameraAnalysis {
    public static ImageAnalysis buildAnalysis(){
    ImageAnalysis imageAnalysis =
            new ImageAnalysis.Builder()
                    .setTargetResolution(new Size(1280, 720))
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build();

    return imageAnalysis;
    }


}
