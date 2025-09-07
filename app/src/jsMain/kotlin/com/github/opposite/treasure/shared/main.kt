import androidx.compose.ui.window.ComposeViewport
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.ExperimentalBrowserHistoryApi
import androidx.navigation.bindToBrowserNavigation
import com.github.onotoliy.opposite.di.repositoryModule
import com.github.onotoliy.opposite.di.viewModelModule
import com.github.onotoliy.opposite.ui.navigation.WebWindowNavigation
import org.jetbrains.skiko.wasm.onWasmReady
import org.koin.compose.KoinApplication

@OptIn(ExperimentalComposeUiApi::class, ExperimentalBrowserHistoryApi::class)
fun main() {
    onWasmReady {
        ComposeViewport("composeApp") {
            KoinApplication(application = {
                modules(repositoryModule, viewModelModule)
            }) {
                WebWindowNavigation() {
                    it.bindToBrowserNavigation()
                }
            }
        }
    }
}
