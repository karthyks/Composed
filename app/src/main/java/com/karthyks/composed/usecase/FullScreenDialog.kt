package com.karthyks.composed.usecase

import com.karthyks.composed.LocalOverlayProvider
import com.karthyks.composed.Overlay
import com.karthyks.composed.ViewHolder
import com.karthyks.composed.viewHolder
import com.karthyks.composed.views.RenderFullScreenDialogContent

class FullScreenDialog : Overlay {
    override val viewHolder: ViewHolder by viewHolder {
        val provider = LocalOverlayProvider.current
        RenderFullScreenDialogContent {
            provider.remove(this)
        }
    }
}
