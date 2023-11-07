package com.example.wallpaperchanger

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.WallpaperManager
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContract
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URI

class MainActivity : AppCompatActivity() {
    var selectedImgId = R.drawable.img1
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        val wallpaperManager = WallpaperManager.getInstance(this)
        var selectedImgId = R.drawable.img1
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.changeWallpaperButton)
        val drawableResourcesIds = mutableListOf<Int>()
        val drawableResourcesName = mutableListOf<String>()
        val packageName = resources.getResourcePackageName(R.drawable.img1)
        val resourcesField = R.drawable::class.java.fields

        for (field in resourcesField) {
            if (field.name.startsWith("img")) {
                val resourceId = resources.getIdentifier(field.name, "drawable", packageName)
                if (resourceId != 0) {
                    drawableResourcesIds.add(resourceId)
                    drawableResourcesName.add(field.name)
                }
            }
        }
        val ll = findViewById<LinearLayout>(R.id.imageContainer)
        var i =0
        for (resourceId in drawableResourcesIds) {
            val imageView = ImageView(ll.context)
            imageView.setImageResource(resourceId)
            imageView.tag = drawableResourcesName[i++]
            imageView.maxHeight = ViewGroup.LayoutParams.WRAP_CONTENT
            imageView.maxWidth = ViewGroup.LayoutParams.MATCH_PARENT
            imageView.setOnClickListener {
                selectedImgId = resourceId
            }
            ll.addView(imageView)
        }

        //btn click to change the wallpapper.....
        button.setOnClickListener {

            //show the dialog to provide the options whether in  screen wallpapper like...lock screen OR Home screen or Both Screen
            showWallpaperDialog(selectedImgId)
        }
    }
      ///show the Alert dialog builder .....
    private fun showWallpaperDialog(Id: Int) {
        val options = arrayOf("Home Screen", "Lock Screen", "Both")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Set Wallpaper")
            .setItems(options) { _, index: Int ->

                when (index) {
                    0 -> {setWallpaper(Id, WallpaperManager.FLAG_SYSTEM)
                    Toast.makeText(this,"Home screen will changed",Toast.LENGTH_LONG).show()}
                    1 ->{ setWallpaper(Id, WallpaperManager.FLAG_LOCK)
                    Toast.makeText(this,"Lock screen will changed",Toast.LENGTH_LONG).show()}
                    2 -> {setWallpaper(
                        Id,
                        WallpaperManager.FLAG_SYSTEM or WallpaperManager.FLAG_LOCK
                    )
                    Toast.makeText(this,"Home and Lock screen will changed",Toast.LENGTH_LONG).show()}

                }
            }
            .show()
    }

       /// this fun is used to set the wallpapper accounting to dialog input
        private fun setWallpaper(resourceId: Int, flags: Int) {
            val wallpaperManager = WallpaperManager.getInstance(this)
            try {
                wallpaperManager.setResource(resourceId, flags)
            } catch (e:IOException) {
                e.printStackTrace()
            }
        }
    }
