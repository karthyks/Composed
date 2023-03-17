package io.github.karthyks.composed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

val LocalOverlayProvider =
    staticCompositionLocalOf<OverlayProvider> { error("No overlay provided!") }

@Composable
fun ProvideOverlay(content: @Composable OverlayScope.() -> Unit) {
    val viewModel: InternalViewModel = viewModel()
    CompositionLocalProvider(
        LocalOverlayProvider provides viewModel.overlayProvider,
    ) {
        with(OverlayScopeInstance) {
            content()
            OverlayManager()
        }
    }
}

/**
 * ViewModel to hold overlayProvider, so it can survive on configurationChange.
 */
internal class InternalViewModel : ViewModel() {
    internal val overlayProvider = OverlayProviderImpl()
}
