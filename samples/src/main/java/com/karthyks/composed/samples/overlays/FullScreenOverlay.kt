package com.karthyks.composed.samples.overlays

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.karthyks.composed.samples.R
import com.karthyks.composed.samples.test.TestTag
import io.github.karthyks.composed.LocalOverlayProvider
import io.github.karthyks.composed.Overlay
import io.github.karthyks.composed.ViewHolder
import io.github.karthyks.composed.viewHolder

class FullScreenOverlay : Overlay {
    override val viewHolder: ViewHolder by viewHolder {
        RenderFullScreenOverlayContent {
            it.dispose()
        }
    }
}

@Composable
private fun RenderFullScreenOverlayContent(onClose: () -> Unit) {
    val overlayProvider = LocalOverlayProvider.current
    LaunchedEffect(key1 = Unit) {
        overlayProvider.showOverlay(ToastMessage("FullScreen displayed"), attachToParent = true)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .testTag(TestTag.fullScreenDialog)
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Box {
            Text(
                text = stringResource(id = R.string.body),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .wrapContentSize(align = Alignment.TopEnd)
                .background(shape = CircleShape, color = Color.Gray)
                .testTag(TestTag.closeDialog)
                .clickable {
                    onClose()
                }
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Close,
                modifier = Modifier.size(32.dp),
                contentDescription = "CloseOverlayBtn"
            )
        }
    }
}
