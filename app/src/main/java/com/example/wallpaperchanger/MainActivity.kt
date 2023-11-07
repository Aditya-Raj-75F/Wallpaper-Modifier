package com.example.wallpaperchanger

import android.annotation.SuppressLint
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
import java.net.URI

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        var selectedImgId = R.drawable.img1
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.changeWallpaperButton)
        val drawableResourcesIds = mutableListOf<Int>()
        val packageName = resources.getResourcePackageName(R.drawable.img1)
        val resourcesField = R.drawable::class.java.fields

        for(field in resourcesField) {
            if(field.name.startsWith("img")) {
                val resourceId = resources.getIdentifier(field.name, "drawable", packageName)
                if (resourceId != 0) {
                    drawableResourcesIds.add(resourceId)
                }
            }
        }
        val ll = findViewById<LinearLayout>(R.id.imageContainer)
        for (resourceId in drawableResourcesIds) {
            val imageView = ImageView(ll.context)
            imageView.setImageResource(resourceId)
            imageView.maxHeight = ViewGroup.LayoutParams.WRAP_CONTENT
            imageView.maxWidth = ViewGroup.LayoutParams.MATCH_PARENT
            imageView.setOnClickListener {
                Toast.makeText(this, "field.name", Toast.LENGTH_LONG).show()
                selectedImgId = resourceId
            }
            ll.addView(imageView)
        }
        button.setOnClickListener {
            val wallpaperManager = WallpaperManager.getInstance(this)
            wallpaperManager.setResource(selectedImgId)
        }
    }
}