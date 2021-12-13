package xyz.mmixel.hw3

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread


private const val TAG = "GetImage"

class GetImage : AppCompatActivity() {
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_image)
        imageView = findViewById(R.id.imageView)

        findViewById<EditText>(R.id.imageUriForAndroid).apply {
            setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    thread {
                        try {
                            val stream = URL(this.text.toString()).openStream()
                            val bitmap: Bitmap = BitmapFactory.decodeStream(stream)
                            runOnUiThread {
                                imageView.setImageBitmap(bitmap)
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            runOnUiThread {
                                Toast.makeText(
                                    this@GetImage,
                                    "Error. ${e.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                    return@setOnKeyListener true
                }
                false
            }
        }
    }
}