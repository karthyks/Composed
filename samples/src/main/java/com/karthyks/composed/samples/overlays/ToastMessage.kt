package com.karthyks.composed.samples.overlays

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.karthyks.composed.samples.test.TestTag
import io.github.karthyks.composed.Overlay
import io.github.karthyks.composed.ViewHolder
import io.github.karthyks.composed.viewHolder
import kotlinx.coroutines.delay

class ToastMessage(private val message: String) : Overlay {
    override val viewHolder: ViewHolder by viewHolder {
        RenderToastMessage(message) {
            it.dispose()
        }
    }
}

@Composable
private fun RenderToastMessage(text: String, onClose: () -> Unit) {
    LaunchedEffect(key1 = Unit) {
        delay(2000L)
        onClose()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .testTag(TestTag.toastMessage)
            .padding(16.dp)
            .wrapContentSize(align = Alignment.BottomCenter)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(color = MaterialTheme.colorScheme.errorContainer)
                .wrapContentSize(align = Alignment.Center)
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onErrorContainer,
                fontStyle = MaterialTheme.typography.bodyMedium.fontStyle,
            )
        }
    }
}
