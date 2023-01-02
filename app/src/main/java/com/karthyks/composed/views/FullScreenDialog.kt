package com.karthyks.composed.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.karthyks.composed.usecase.ToastMessage
import io.github.karthyks.composed.LocalOverlayProvider

@Composable
fun RenderFullScreenDialogContent(
    onClose: () -> Unit
) {
    val overlay = LocalOverlayProvider.current

    LaunchedEffect(key1 = Unit) {
        overlay.showOverlay(ToastMessage())
    }
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.inversePrimary)
    ) {
        val number = remember { mutableStateOf(0) }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.TopEnd)
        ) {
            Button(
                onClick = onClose
            ) {
                Text(text = "Close")
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center)
        ) {
            Button(
                onClick = {
                    number.value = number.value + 1
                }
            ) {
                Text(text = "Increment ${number.value}")
            }
        }
    }
}