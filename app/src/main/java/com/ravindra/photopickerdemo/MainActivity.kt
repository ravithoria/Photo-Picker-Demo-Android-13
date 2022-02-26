package com.ravindra.photopickerdemo

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


// https://developer.android.com/about/versions/13/features/photopicker#kotlin
class MainActivity : AppCompatActivity() {

    companion object {
        private const val  PHOTO_PICKER_SINGLE_SELECT_REQUEST_CODE = 0
        private const val PHOTO_PICKER_MULTI_SELECT_REQUEST_CODE = 1
        private const val PHOTO_PICKER_VIDEO_SINGLE_SELECT_REQUEST_CODE = 2
        private const val PHOTO_PICKER_VIDEO_MULTI_SELECT_REQUEST_CODE = 3
    }

    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPick1 = findViewById<Button>(R.id.btnPick1)
        val btnPick2 = findViewById<Button>(R.id.btnPick2)
        val btnPick3 = findViewById<Button>(R.id.btnPick3)
        val btnPick4 = findViewById<Button>(R.id.btnPick4)

        btnPick1.setOnClickListener {
            // Launches photo picker in single-select mode.
            // This means that the user can select one photo or video.
            val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
            startActivityForResult(intent, PHOTO_PICKER_SINGLE_SELECT_REQUEST_CODE)
        }

        btnPick2.setOnClickListener {
            // Launches photo picker in multi-select mode.
            // This means that user can select multiple photos/videos, up to the limit
            // specified by the app in the extra (10 in this example).
            val maxNumPhotosAndVideos = 2
            val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
            intent.putExtra(MediaStore.EXTRA_PICK_IMAGES_MAX, maxNumPhotosAndVideos)
            startActivityForResult(intent, PHOTO_PICKER_MULTI_SELECT_REQUEST_CODE)
        }

        btnPick3.setOnClickListener {
            // Launches photo picker for videos only in single select mode.
            val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
            intent.type = "video/*" // images only - intent.type = "images/*"
            startActivityForResult(intent, PHOTO_PICKER_VIDEO_SINGLE_SELECT_REQUEST_CODE)
        }


        btnPick4.setOnClickListener {
            // Launches photo picker for videos only in multi-select mode.
            val maxNumPhotosAndVideos = 2
            val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
            intent.type = "video/*" //// images only - intent.type = "images/*"
            intent.putExtra(MediaStore.EXTRA_PICK_IMAGES_MAX, maxNumPhotosAndVideos)
            startActivityForResult(intent, PHOTO_PICKER_VIDEO_MULTI_SELECT_REQUEST_CODE)
        }
    }

    // onActivityResult() handles callbacks from the photo picker.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            // Handle error
            return
        }
        when (requestCode) {
            Companion.PHOTO_PICKER_SINGLE_SELECT_REQUEST_CODE -> {
                // Get photo picker response for single select.
                val currentUri: Uri? = data?.data
                // Do stuff with the photo/video URI.
                Log.d(TAG, "currentUri: $currentUri")
                //content://media/picker/0/com.android.providers.media.photopicker/media/22337
                return
            }
            PHOTO_PICKER_MULTI_SELECT_REQUEST_CODE -> {
                // Get photo picker response for multi select.
                var mediaCount = 0
                while (mediaCount < data?.clipData!!.itemCount) {
                    val currentUri = data.clipData!!.getItemAt(mediaCount).uri
                    // Do stuff with uri
                    mediaCount++
                    Log.d(TAG, "mediaCount: $mediaCount")
                    Log.d(TAG, "currentUri: $currentUri")
                }

                /*for (i in 0 until data?.clipData!!.itemCount) {
                   val currentUri: Uri = data.clipData!!.getItemAt(i).uri
                   // Do stuff with each photo/video URI.
                   Log.d(TAG, "currentUri: $currentUri")
                   //content://media/picker/0/com.android.providers.media.photopicker/media/22337
                   //content://media/picker/0/com.android.providers.media.photopicker/media/22338
               }*/
                return
            }
            PHOTO_PICKER_VIDEO_SINGLE_SELECT_REQUEST_CODE -> {
                // Get video picker response for single select.
                val currentUri: Uri? = data?.data
                // Do stuff with the photo/video URI.
                Log.d(TAG, "currentUri: $currentUri")
                //currentUri: content://media/picker/0/com.android.providers.media.photopicker/media/22259
                return
            }
            PHOTO_PICKER_VIDEO_MULTI_SELECT_REQUEST_CODE -> {
                // Get photo picker response for multi select.
                var mediaCount = 0
                while (mediaCount < data?.clipData!!.itemCount) {
                    val currentUri = data.clipData!!.getItemAt(mediaCount).uri
                    // Do stuff with uri
                    mediaCount++
                    Log.d(TAG, "mediaCount: $mediaCount")
                    Log.d(TAG, "currentUri: $currentUri")
                }
                return
            }
        }
    }

}