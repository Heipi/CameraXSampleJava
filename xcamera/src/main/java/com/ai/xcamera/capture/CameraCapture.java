package com.ai.xcamera.capture;

import android.util.Size;
import android.view.Surface;

import androidx.camera.core.AspectRatio;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraControl;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.FocusMeteringResult;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.MeteringPoint;
import androidx.camera.core.MeteringPointFactory;
import androidx.camera.core.SurfaceOrientedMeteringPointFactory;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * 图片拍摄拍摄
 */
public class CameraCapture {



    public static  ImageCapture buildCapture() {
        ImageCapture.Builder builder =
                new ImageCapture.Builder();
        //设置预期目标的高宽比
        builder.setTargetAspectRatio(AspectRatio.RATIO_4_3);
        builder.setTargetRotation(Surface.ROTATION_0);
        /* builder.setTargetResolution(new Size(1920,1089));*/
        //设置闪光灯模式
        builder.setFlashMode(ImageCapture.FLASH_MODE_AUTO);
        return builder.build();
    }

    /**
     * CameraControl 提供各种异步操作，如缩放，聚焦和
     * 计量
     * @param camera
     * @return
     */
    public CameraControl getCameraControl(Camera camera) {
        return camera.getCameraControl();
    }

//    点按对焦

    public static void pointPressToFocus(CameraControl cameraControl, float width, float height, float x, float y, Executor executor){
        MeteringPointFactory factory = new SurfaceOrientedMeteringPointFactory(width,height);
        MeteringPoint point =  factory.createPoint(x,y);
        FocusMeteringAction action = new FocusMeteringAction.Builder(point,FocusMeteringAction.FLAG_AF)/*.addPoint(point2,FocusMeteringAction.FLAG_AE)*/
               .setAutoCancelDuration(5, TimeUnit.SECONDS).build(); //   // auto calling cancelFocusAndMetering in 5 seconds.build();
       ListenableFuture future = cameraControl.startFocusAndMetering(action);
        future.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    FocusMeteringResult result  = (FocusMeteringResult) future.get();
                    //process the result
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //
            }
        },executor);




    }


}
