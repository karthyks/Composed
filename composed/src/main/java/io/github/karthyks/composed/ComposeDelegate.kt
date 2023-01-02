package io.github.karthyks.composed

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlin.reflect.KProperty

internal class ComposeDelegate(
    private val content: @Composable (DisposableViewHolder) -> Unit,
) {
    lateinit var viewHolder: ViewHolder
    private val composeWrapper: @Composable () -> Unit = {
        // Do animations or other stuffs here.
        Box {
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
