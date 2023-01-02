package io.github.karthyks.composed

import androidx.compose.foundation.layout.LayoutScopeMarker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

@LayoutScopeMarker
@Immutable
interface OverlayScope

object OverlayScopeInstance : OverlayScope

@Suppress("unused")
@Composable
internal fun OverlayScope.OverlayManager() {
    val currentOverlayProvider = LocalOverlayProvider.current
    currentOverlayProvider.backstack.forEach {
        ComposeWrapper(viewHolder = it)
    }
}

@Composable
private fun ComposeWrapper(
    viewHolder: ViewHolder,
) {
    viewHolder.composableContent()
}
