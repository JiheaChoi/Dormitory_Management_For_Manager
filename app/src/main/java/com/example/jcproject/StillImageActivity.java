package com.example.jcproject;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;

public class StillImageActivity extends AppCompatActivity {

    /*
    private static final String TAG = "StillImageActivity";

    private static final String CLOUD_LABEL_DETECTION = "Cloud Label";
    private static final String CLOUD_LANDMARK_DETECTION = "Landmark";
    private static final String CLOUD_TEXT_DETECTION = "Cloud Text";
    private static final String CLOUD_DOCUMENT_TEXT_DETECTION = "Doc Text";

    private static final String SIZE_PREVIEW = "w:max"; // Available on-screen width.
    private static final String SIZE_1024_768 = "w:1024"; // ~1024*768 in a normal ratio
    private static final String SIZE_640_480 = "w:640"; // ~640*480 in a normal ratio

    private static final String KEY_IMAGE_URI = "com.googletest.firebase.ml.demo.KEY_IMAGE_URI";
    private static final String KEY_IMAGE_MAX_WIDTH =
            "com.googletest.firebase.ml.demo.KEY_IMAGE_MAX_WIDTH";
    private static final String KEY_IMAGE_MAX_HEIGHT =
            "com.googletest.firebase.ml.demo.KEY_IMAGE_MAX_HEIGHT";
    private static final String KEY_SELECTED_SIZE =
            "com.googletest.firebase.ml.demo.KEY_SELECTED_SIZE";

    private static final int REQUEST_IMAGE_CAPTURE = 1001;
    private static final int REQUEST_CHOOSE_IMAGE = 1002;

    private Button getImageButton;
    private ImageView preview;
    private GraphicOverlay graphicOverlay;
    private String selectedMode = CLOUD_LABEL_DETECTION;
    private String selectedSize = SIZE_PREVIEW;

    boolean isLandScape;

    private Uri imageUri;
    // Max width (portrait mode)
    private Integer imageMaxWidth;
    // Max height (portrait mode)
    private Integer imageMaxHeight;
    private Bitmap bitmapForDetection;
    private VisionImageProcessor imageProcessor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_still_image);

        getImageButton = (Button) findViewById(R.id.getImageButton);
        getImageButton.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Menu for selecting either: a) take new photo b) select from existing
                        PopupMenu popup = new PopupMenu(StillImageActivity.this, view);
                        popup.setOnMenuItemClickListener(
                                new OnMenuItemClickListener() {
                                    @Override
                                    public boolean onMenuItemClick(MenuItem menuItem) {
                                        switch (menuItem.getItemId()) {
                                            case R.id.select_images_from_local:
                                                startChooseImageIntentForResult();
                                                return true;
                                            case R.id.take_photo_using_camera:
                                                startCameraIntentForResult();
                                                return true;
                                            default:
                                                return false;
                                        }
                                    }
                                });

                        MenuInflater inflater = popup.getMenuInflater();
                        inflater.inflate(R.menu.camera_button_menu, popup.getMenu());
                        popup.show();
                    }
                });
        preview = (ImageView) findViewById(R.id.previewPane);
        if (preview == null) {
            Log.d(TAG, "Preview is null");
        }
        graphicOverlay = (GraphicOverlay) findViewById(R.id.previewOverlay);
        if (graphicOverlay == null) {
            Log.d(TAG, "graphicOverlay is null");
        }

        populateFeatureSelector();
        populateSizeSelector();

        createImageProcessor();

        isLandScape =
                (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);

        if (savedInstanceState != null) {
            imageUri = savedInstanceState.getParcelable(KEY_IMAGE_URI);
            imageMaxWidth = savedInstanceState.getInt(KEY_IMAGE_MAX_WIDTH);
            imageMaxHeight = savedInstanceState.getInt(KEY_IMAGE_MAX_HEIGHT);
            selectedSize = savedInstanceState.getString(KEY_SELECTED_SIZE);

            if (imageUri != null) {
                tryReloadAndDetectInImage();
            }
        }

    }
    */

}