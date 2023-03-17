package com.karthyks.composed.samples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.karthyks.composed.samples.overlays.FullScreenOverlay
import com.karthyks.composed.samples.test.TestTag
import io.github.karthyks.composed.LocalOverlayProvider
import io.github.karthyks.composed.ProvideOverlay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenderSampleScreen() {
    ProvideOverlay {
        Scaffold(
            topBar = {
                RenderTopBar()
            }
        ) { padding ->
            val overlayProvider = LocalOverlayProvider.current
            RenderContent(modifier = Modifier.padding(padding)) {
                overlayProvider.showOverlay(FullScreenOverlay())
            }
        }
    }
}

@Composable
@Preview
private fun RenderTopBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(16.dp)
            .wrapContentSize(align = Alignment.CenterStart)
    ) {
        Text(
            "Samples",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
@Preview
private fun RenderContent(
    modifier: Modifier = Modifier,
    onShowDialog: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    ) {
        Button(modifier = Modifier.testTag(TestTag.showDialog), onClick = { onShowDialog() }) {
            Text("Show full screen overlay")
        }
    }
}
