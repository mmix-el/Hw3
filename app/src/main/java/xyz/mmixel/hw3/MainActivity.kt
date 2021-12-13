package xyz.mmixel.hw3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.flags_button).apply {
            setOnClickListener {
                startActivity(Intent(this@MainActivity, FlagsActivity::class.java))
            }
        }
        findViewById<Button>(R.id.get_image_button).apply {
            setOnClickListener {
                startActivity(Intent(this@MainActivity, GetImage::class.java))
            }
        }
    }
}