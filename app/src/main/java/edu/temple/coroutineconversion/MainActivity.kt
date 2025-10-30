package edu.temple.coroutineconversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.util.Locale
import kotlinx.coroutines.*  // for coroutines

class MainActivity : AppCompatActivity() {

    //TODO (Refactor to replace Thread code with coroutines)

    private val cakeImageView: ImageView by lazy {
        findViewById(R.id.imageView)
    }

    private val currentTextView: TextView by lazy {
        findViewById(R.id.currentTextView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.revealButton).setOnClickListener {

            // ✅ Coroutine replaces Thread + Handler, uses withContext for UI updates
            CoroutineScope(Dispatchers.Default).launch {
                repeat(100) {
                    withContext(Dispatchers.Main) {
                        currentTextView.text = String.format(
                            Locale.getDefault(),
                            "Current opacity: %d",
                            it
                        )
                        cakeImageView.alpha = it / 100f
                    }
                    delay(40)
                }
            }
        }
    }
}
