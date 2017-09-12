package com.example.android.emojify;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;


/**
 * Created by Darren on 09/09/2017.
 */

public class Emojifier {

    public static final String TAG = Emojifier.class.getSimpleName();

    static void detectFaces(Context context, Bitmap picture) {

        // Create the face detector, disable tracking and enable classifications
        FaceDetector detector = new FaceDetector.Builder(context)
                .setTrackingEnabled(false)
                .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                .build();

  if (detector.isOperational()) {

            Log.i(TAG, "DETECTOR DETECTED");
            // Build the frame
            Frame frame = new Frame.Builder().setBitmap(picture).build();

            // Detect the faces
            SparseArray<Face> faces = detector.detect(frame);

            // Log the number of faces
            Log.d(TAG, "detectFaces: number of faces = " + faces.size());

            // If there are no faces detected, show a Toast message
            if (faces.size() == 0) {
                Toast.makeText(context, R.string.no_faces_message, Toast.LENGTH_SHORT).show();
            }

            // Release the detector

        } else {

            Log.i(TAG, "DETECTOR NOT DOWNLOADED");
        }

        detector.release();
    }
}
