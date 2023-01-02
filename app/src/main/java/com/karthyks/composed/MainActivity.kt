package com.karthyks.composed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.karthyks.composed.ui.theme.ComposedTheme
import com.karthyks.composed.usecase.FullScreenDialog
import com.karthyks.composed.usecase.ToastMessage
import io.github.karthyks.composed.LocalOverlayProvider
import io.github.karthyks.composed.ProvideOverlay

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

                        LaunchedEffect(key1 = Unit) {
                            overlay.showOverlay(ToastMessage())
                        }
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
