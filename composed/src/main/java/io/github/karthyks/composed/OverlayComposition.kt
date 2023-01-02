package io.github.karthyks.composed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalOverlayProvider =
    staticCompositionLocalOf<OverlayProvider> { error("No overlay provided!") }

@Composable
fun ProvideOverlay(content: @Composable OverlayScope.() -> Unit) {
    CompositionLocalProvider(
        LocalOverlayProvider provides OverlayProviderImpl(),
    ) {
        with(OverlayScopeInstance) {
            content()
            OverlayManager()
        }
    }
}
