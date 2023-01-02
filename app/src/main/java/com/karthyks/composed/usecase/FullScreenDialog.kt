package com.karthyks.composed.usecase

import com.karthyks.composed.views.RenderFullScreenDialogContent
import io.github.karthyks.composed.Overlay
import io.github.karthyks.composed.ViewHolder
import io.github.karthyks.composed.viewHolder

class FullScreenDialog : Overlay {
    override val viewHolder: ViewHolder by viewHolder { disposable ->
        RenderFullScreenDialogContent {
            disposable.dispose()
        }
    }
}
