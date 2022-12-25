package com.karthyks.composed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import kotlin.reflect.KProperty

@Stable
abstract class ViewHolder : InternalViewHolder() {
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

fun viewHolder(content: @Composable () -> Unit): ViewHolderDelegate {
    return ViewHolderDelegate(content)
}

class ViewHolderDelegate(
    private val content: @Composable () -> Unit
) {
    private val viewHolder = object : ViewHolder() {
        override val composableContent by ComposeDelegate(content)
    }

    operator fun getValue(thisRef: Overlay, property: KProperty<*>): ViewHolder {
        return viewHolder
    }
}