import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


@OptIn(ExperimentalMaterialApi::class)
fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        DesktopSpotlightExperiment()
    }
}
