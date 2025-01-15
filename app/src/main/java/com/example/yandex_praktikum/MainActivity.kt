package com.example.yandex_praktikum

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.yandex_praktikum.ui.theme.YandexpraktikumTheme
import com.example.yandex_praktikum.ui.views.CustomViewGroup

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
                CustomViewGroupContainer()
            }
        }
    }
}
@Preview
@Composable
fun CustomViewGroupContainer() {
    AndroidView(
        factory = { context ->
            CustomViewGroup(context).apply {
                val childView1 = TextView(context).apply {
                    text = "Первый элемент"
                }
                val childView2 = TextView(context).apply {
                    text = "Второй элемент"
                }

                addView(childView1)
                addView(childView2)
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

