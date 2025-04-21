package com.example.myapplication
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ipAddressTextView = findViewById<android.widget.TextView>(R.id.ipAddressTextView)
        val progressBar = findViewById<android.widget.ProgressBar>(R.id.progressBar)
        val refreshButton = findViewById<android.widget.Button>(R.id.refreshButton)

        fun loadIpAddress() {
            CoroutineScope(Dispatchers.Main).launch {
                progressBar.visibility = android.view.View.VISIBLE
                try {
                    val ipAddress = withContext(Dispatchers.IO) {
                        RetrofitClient.instance.getIpAddress()
                    }
                    ipAddressTextView.text = ipAddress
                } catch (e: Exception) {
                    ipAddressTextView.text = "Error: ${e.message}"
                } finally {
                    progressBar.visibility = android.view.View.GONE
                }
            }
        }

        // Загрузить IP при старте
        loadIpAddress()

        // Обновить по нажатию кнопки
        refreshButton.setOnClickListener {
            loadIpAddress()
        }
    }
}