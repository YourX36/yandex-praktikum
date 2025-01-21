package com.example.yandex_praktikum

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.yandex_praktikum.ui.jetpack.CustomViewJetpackContainer
import com.example.yandex_praktikum.ui.theme.YandexpraktikumTheme

class XmlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        findViewById<Button>(R.id.button_to_compose).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YandexpraktikumTheme {
                CustomViewJetpackContainer(
                    modifier = Modifier
                        .fillMaxSize(),
                    firstChild = {
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .background(Color.Red)
                        ) {
                            Text("Первый элемент")
                        }
                    },
                    secondChild = {
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .background(Color.Blue)
                        ) {
                            Text("Второй элемент")
                        }
                    }
                )
            }
        }
    }
}

