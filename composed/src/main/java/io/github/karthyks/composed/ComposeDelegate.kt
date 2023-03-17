package io.github.karthyks.composed

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import kotlin.reflect.KProperty

internal class ComposeDelegate(
    private val content: @Composable (DisposableViewHolder) -> Unit,
) {
    lateinit var viewHolder: ViewHolder
    private val composeWrapper: @Composable () -> Unit = {
        // Do animations or other stuffs here.
        Box(modifier = Modifier.testTag(OVERLAY_TEST_TAG)) {
            val provider = LocalOverlayProvider.current
            val disposable = remember {
                DisposableViewHolder { provider.remove(viewHolder) }
            }
            content(disposable)
        }
    }

    operator fun getValue(thisRef: ViewHolder, property: KProperty<*>): @Composable () -> Unit {
        this.viewHolder = thisRef
        return composeWrapper
    }
}

const val OVERLAY_TEST_TAG = "composed-overlay"