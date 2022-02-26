# Photo-Picker-Demo-Android-13

- Pick single Image or Video
- Pick multiple Images and Videos
- Pick single Video
- Pick multiple Videos

<img src="https://user-images.githubusercontent.com/16267564/155839542-720cfd4d-4d5b-432f-94be-20f16b8c4b0a.png" width="320" height="700">  <img src="https://user-images.githubusercontent.com/16267564/155836627-36e4cb1e-2d72-4219-8981-33cdafb8c4af.png" width="320" height="700">  <img src="https://user-images.githubusercontent.com/16267564/155836631-8e06fe31-d2c8-4d80-8958-dcb4fff2bf63.png" width="320" height="700">  <img src="https://user-images.githubusercontent.com/16267564/155836632-5cbf896e-7bc2-471a-94d7-d2c5c5464743.png" width="320" height="700">  <img src="https://user-images.githubusercontent.com/16267564/155836638-7df443c7-5aad-4716-b2f1-857914193834.png" width="320" height="700">

- Pick single Image or Video
```kotlin
val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
startActivityForResult(intent, PHOTO_PICKER_SINGLE_SELECT_REQUEST_CODE)
```
- Pick multiple Images and Videos
```kotlin
val maxNumPhotosAndVideos = 2
val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
intent.putExtra(MediaStore.EXTRA_PICK_IMAGES_MAX, maxNumPhotosAndVideos)
startActivityForResult(intent, PHOTO_PICKER_MULTI_SELECT_REQUEST_CODE)
```
- Pick single Video
```kotlin
val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
intent.type = "video/*" // images only - intent.type = "images/*"
startActivityForResult(intent, PHOTO_PICKER_VIDEO_SINGLE_SELECT_REQUEST_CODE)
```
- Pick multiple Videos
```kotlin
val maxNumPhotosAndVideos = 2
val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
intent.type = "video/*" // images only - intent.type = "images/*"
intent.putExtra(MediaStore.EXTRA_PICK_IMAGES_MAX, maxNumPhotosAndVideos)
startActivityForResult(intent, PHOTO_PICKER_VIDEO_MULTI_SELECT_REQUEST_CODE)
```
- onActivityResult() handles callbacks from the photo picker.
```kotlin
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
                return
            }
            PHOTO_PICKER_MULTI_SELECT_REQUEST_CODE -> {
                // Get photo picker response for multi select.
                var mediaCount = 0
                while (mediaCount < data?.clipData!!.itemCount) {
                    val currentUri = data.clipData!!.getItemAt(mediaCount).uri
                    // Do stuff with uri
                    mediaCount++
                }
                return
        }
    }
```
