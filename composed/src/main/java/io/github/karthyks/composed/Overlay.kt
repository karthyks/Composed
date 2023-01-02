package io.github.karthyks.composed

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

interface Overlay {
    val viewHolder: ViewHolder
}

abstract class OverlayProvider : InternalOverlayProvider() {
    abstract fun showOverlay(overlay: Overlay, attachToParent: Boolean = false)
    abstract fun remove(viewHolder: ViewHolder)
    abstract fun remove(overlay: Overlay)
}

abstract class InternalOverlayProvider {
    internal abstract val backstack: SnapshotStateList<ViewHolder>
}

internal class OverlayProviderImpl : OverlayProvider() {
    override val backstack: SnapshotStateList<ViewHolder> = mutableStateListOf()

    override fun showOverlay(overlay: Overlay, attachToParent: Boolean) {
        backstack.add(overlay.viewHolder.also { if (attachToParent) it.attachToParent() })
    }

    override fun remove(overlay: Overlay) {
        remove(overlay.viewHolder)
    }

    override fun remove(viewHolder: ViewHolder) {
        val startIndexToRemove = backstack.indexOf(viewHolder)
        val iterator = backstack.iterator()
        iterator.withIndex().forEach {
            if (it.index == startIndexToRemove) {
                it.value.release()
            } else if (it.index > startIndexToRemove && it.value.attachedToParent) {
                it.value.release()
            }
        }
        cleanUpBackStack()
    }

    private fun cleanUpBackStack() {
        val iterator = backstack.asReversed().iterator()
        while (iterator.hasNext()) {
            val current = iterator.next()
            if (current.isReleased) {
                iterator.remove()
            } else {
                break
            }
        }
    }
}
