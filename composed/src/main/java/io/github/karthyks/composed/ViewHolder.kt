package io.github.karthyks.composed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import kotlin.reflect.KProperty

/**
 * ViewHolderDelegate takes in a composable content.
 * [content] - Composable to render the overlay.
 * Returns ViewHolder through the ViewHolderDelegate.
 */
@Suppress("unused")
fun Overlay.viewHolder(
    content: @Composable (disposable: DisposableViewHolder) -> Unit
): ViewHolderDelegate {
    return ViewHolderDelegate(content)
}

/**
 * Creates an instance of ViewHolder.
 * @see Overlay.viewHolder for easy usage.
 */
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

/**
 * Call dispose to remove the overlay from the screen.
 */
fun interface DisposableViewHolder {
    fun dispose()
}

/**
 * ViewHolder holds the composable function
 * @property composableContent - Composable to render in the overlay.
 */
@Stable
abstract class ViewHolder internal constructor() : InternalViewHolder() {
    abstract val composableContent: @Composable () -> Unit
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
