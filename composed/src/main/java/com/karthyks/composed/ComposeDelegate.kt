package com.karthyks.composed

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import kotlin.reflect.KProperty

internal class ComposeDelegate(
    private val content: @Composable () -> Unit,
) {
    private val composeWrapper: @Composable () -> Unit = {
        // Do animations or other stuffs here.
        Box {
            content()
        }
    }

    operator fun getValue(thisRef: ViewHolder, property: KProperty<*>): @Composable () -> Unit {
        return composeWrapper
    }
}
