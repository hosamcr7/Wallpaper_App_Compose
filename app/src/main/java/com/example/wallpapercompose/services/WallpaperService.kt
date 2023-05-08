package com.example.wallpapercompose.services

import android.app.WallpaperManager
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.os.SystemClock
import android.provider.MediaStore
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream


class WallpaperService {

   fun setWallpaper(context: Context, imageURL:String) : Boolean{

         return   try {
               val p= Picasso.get()
               val result: Bitmap = p.load(imageURL).get()
               val wallpaperManager = WallpaperManager.getInstance(context)
               wallpaperManager.setBitmap(result)
               true
           } catch (e: IOException) {
               e.printStackTrace()
               false
           }

    }


     fun saveImage(context: Context, imageURL: String): Boolean {
        val p= Picasso.get()
        val bitmap: Bitmap = p.load(imageURL).get()
        if (android.os.Build.VERSION.SDK_INT >= 29) {

            val values = ContentValues()
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/png")
            values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000)
            values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis())
            values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/wallpaperx")
            values.put(MediaStore.Images.Media.IS_PENDING, true)
            values.put(MediaStore.Images.Media.DISPLAY_NAME, "img_${SystemClock.uptimeMillis()}")

            val uri: Uri? =
                context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            if (uri != null) {
                saveImageToStream(bitmap, context.contentResolver.openOutputStream(uri))
                values.put(MediaStore.Images.Media.IS_PENDING, false)
                context.contentResolver.update(uri, values, null, null)
                return true
            }
        } else {
            val directory =
                File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString() + File.separator + "test_pictures")
            if (!directory.exists()) {
                directory.mkdirs()
            }
            val fileName =  "img_${SystemClock.uptimeMillis()}"+ ".png"
            val file = File(directory, fileName)
            saveImageToStream(bitmap, FileOutputStream(file))
            val values = ContentValues()
            values.put(MediaStore.Images.Media.DATA, file.absolutePath)
            // .DATA is deprecated in API 29
            context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            return true
        }
        return false
    }

    private fun saveImageToStream(bitmap: Bitmap, outputStream: OutputStream?) {
        if (outputStream != null) {
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                outputStream.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}

