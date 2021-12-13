package xyz.mmixel.hw3

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

private const val TAG = "GetImage"

class GetImage : AppCompatActivity() {
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_image)
        imageView = findViewById(R.id.imageView)

        findViewById<EditText>(R.id.imageUriForPicasso).apply {
            setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    Picasso.get()
                        .load(Uri.parse(this.text.toString()))
                        .into(imageView, object : Callback {
                            override fun onSuccess() {
                                Log.d(TAG, "Done.")
                            }

                            override fun onError(e: Exception?) {
                                Toast.makeText(
                                    this@GetImage,
                                    "Error. ${e?.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        })
                    return@setOnKeyListener true
                }
                false
            }
        }
    }
}