package com.example.jcproject;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.example.jcproject.FrameMetadata;
import com.example.jcproject.GraphicOverlay;
import com.example.jcproject.VisionProcessorBase;

import java.io.IOException;
import java.util.List;

public class BarcodeScanningProcessor extends VisionProcessorBase<List<FirebaseVisionBarcode>> {

    private static final String TAG = "BarcodeScanningProcessor";

    private final FirebaseVisionBarcodeDetector detector;

    public BarcodeScanningProcessor() {
        // Note that if you know which format of barcode your app is dealing with, detection will be
        // faster to specify the supported barcode formats one by one, e.g.
        // new FirebaseVisionBarcodeDetectorOptions.Builder()
        //     .setBarcodeFormats(irebaseVisionBarcode.FORMAT_QR_CODE)
        //     .build();
        detector = FirebaseVision.getInstance().getVisionBarcodeDetector();
    }
        @SuppressLint("LongLogTag")
        @Override
        public void stop() {
            try {
                detector.close();
            } catch (IOException e) {
                Log.e(TAG, "Exception thrown while trying to close Barcode Detector: " + e);
            }
        }

        @Override
        protected Task<List<FirebaseVisionBarcode>> detectInImage(FirebaseVisionImage image) {
            return detector.detectInImage(image);
        }

        @Override
        protected void onSuccess(
                @NonNull List<FirebaseVisionBarcode> barcodes,
                @NonNull FrameMetadata frameMetadata,
                @NonNull GraphicOverlay graphicOverlay) {
            graphicOverlay.clear();
            for (int i = 0; i < barcodes.size(); ++i) {
                FirebaseVisionBarcode barcode = barcodes.get(i);
                BarcodeGraphic barcodeGraphic = new BarcodeGraphic(graphicOverlay, barcode);
                graphicOverlay.add(barcodeGraphic);
            }
        }

        @SuppressLint("LongLogTag")
        @Override
        protected void onFailure(@NonNull Exception e) {
            Log.e(TAG, "Barcode detection failed " + e);
        }
    }
