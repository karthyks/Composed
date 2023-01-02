package io.github.karthyks.composed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import kotlin.reflect.KProperty

/**
 * ViewHolderDelegate takes in a composable content to render.
 * @param content - Composable to render in the overlay.
 * @return ViewHolder through the ViewHolderDelegate.
 * @sample - <code><pre>val customViewHolder by viewHolder { Content(onClose = { it.dispose() }) }</pre></code>
 */
@Suppress("unused")
fun Overlay.viewHolder(
    content: @Composable (dispose: DisposableViewHolder) -> Unit
): ViewHolderDelegate {
    return ViewHolderDelegate(content)
}

@Stable
abstract class ViewHolder internal constructor() : InternalViewHolder() {
    abstract val composableContent: @Composable () -> Unit
}

fun interface DisposableViewHolder {
    fun dispose()
}

open class InternalViewHolder {
    internal var isReleased: Boolean = false
        private set
    internal var attachedToParent: Boolean = false
        private set

    internal fun release() {
        isReleased = true
    }

    internal fun attachToParent() {
        attachedToParent = true
    }
}

class ViewHolderDelegate internal constructor(
    private val content: @Composable (DisposableViewHolder) -> Unit
) {
    private val viewHolder = object : ViewHolder() {
        override val composableContent by ComposeDelegate(content)
    }

    operator fun getValue(thisRef: Overlay, property: KProperty<*>): ViewHolder {
        return viewHolder
    }
}
