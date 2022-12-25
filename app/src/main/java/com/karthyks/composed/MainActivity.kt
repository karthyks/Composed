package com.karthyks.composed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.karthyks.composed.ui.theme.ComposedTheme
import com.karthyks.composed.usecase.FullScreenDialog

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposedTheme {
                ProvideOverlay {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.primary
                    ) {
                        val overlay = LocalOverlayProvider.current
                        Button(onClick = {
                            overlay.showOverlay(FullScreenDialog())
                        }) {
                            Text(text = "Show dialog")
                        }
                    }
                }
            }
        }
    }
}
