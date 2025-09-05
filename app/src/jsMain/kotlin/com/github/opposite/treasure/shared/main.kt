import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeViewport
import androidx.compose.ui.ExperimentalComposeUiApi
import com.github.opposite.treasure.shared.EventCacheRepository
import com.github.opposite.treasure.shared.EventsListScreen
import com.github.opposite.treasure.shared.EventsListScreen
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {
        ComposeViewport("composeApp") {
            Column(modifier = Modifier.fillMaxSize()) {
                val store = EventCacheRepository()
                EventsListScreen(store.getAll(name = null, offset = 0, numberOfRows = 10))
            }
        }
    }
}
