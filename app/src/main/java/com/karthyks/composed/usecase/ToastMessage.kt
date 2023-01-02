package com.karthyks.composed.usecase

import com.karthyks.composed.views.RenderToastMessage
import io.github.karthyks.composed.Overlay
import io.github.karthyks.composed.ViewHolder
import io.github.karthyks.composed.viewHolder

class ToastMessage : Overlay {
    override val viewHolder: ViewHolder by viewHolder {
        RenderToastMessage(text = "Some message on the toast") {
            it.dispose()
        }
    }
}
