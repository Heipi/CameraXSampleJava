package com.ai.xcamera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.ai.xcamera.capture.CameraAnalysis;
import com.ai.xcamera.capture.CameraCapture;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CameraActivity extends AppCompatActivity {
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    PreviewView previewView;
    ImageAnalysis analysis;
    ImageCapture capture;
    String imgPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        previewView  = findViewById(R.id.preview_view);
        Executor executor = Executors.newSingleThreadExecutor();

        Log.d("SAVE SUCCESS =","===="+imgPath);
//        Log.d("SAVE SUCCESS =","===="+file.exists());
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);

        analysis = CameraAnalysis.buildAnalysis();
        capture = CameraCapture.buildCapture();
        analysis.setAnalyzer(executor, image -> {
            Log.d("analysis","width ="+image.getWidth() + "height ="+image.getHeight());
            int rotationDegrees = image.getImageInfo().getRotationDegrees();
            //
        });

        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindPreview(cameraProvider);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, ContextCompat.getMainExecutor(this));
    }

    private void bindPreview(ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder().build();
        CameraSelector cameraSelector = new CameraSelector.Builder().
                requireLensFacing(CameraSelector.LENS_FACING_BACK).build();
        preview.setSurfaceProvider(previewView.createSurfaceProvider());
        Camera camera = cameraProvider.bindToLifecycle(this,cameraSelector,preview,analysis,capture);
    }

    public void takePicture(View view) {
        imgPath = Environment.getExternalStorageDirectory()+"/APicture";
        File file = new File(imgPath);
        if (!file.exists()){
            file.mkdirs();
        }
        File imgFile = new File(imgPath,"123.png");
        ImageCapture.OutputFileOptions outputFileOptions =
                new ImageCapture.OutputFileOptions.Builder(imgFile).build();
        capture.takePicture(outputFileOptions,
                ContextCompat.getMainExecutor(this), new ImageCapture.OnImageSavedCallback() {
                    @Override
                    public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                        Log.d("SAVE SUCCESS =",Thread.currentThread().getName());
                     Log.d("SAVE SUCCESS",outputFileResults.toString());
                    }
                    @Override
                    public void onError(@NonNull ImageCaptureException exception) {
                        Log.d("SAVE error",exception.toString());
                    }
        });


    }
}
