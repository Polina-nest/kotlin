import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.Info

enum class Screen(val title: String, val icon: ImageVector) {
    ImageDownloader("Image Downloader", Icons.Default.CloudDownload),
    About("About", Icons.Default.Info)
}

@Composable
fun ImageDownloaderApp() {
    val scaffoldState = rememberScaffoldState()
    var currentScreen by remember { mutableStateOf(Screen.ImageDownloader) }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(title = { Text(currentScreen.title) })
        },
        bottomBar = {
            BottomAppBar {
                BottomNavigation {
                    Screen.values().forEach { screen ->
                        BottomNavigationItem(
                            icon = { Icon(screen.icon, contentDescription = null) },
                            label = { Text(screen.title) },
                            selected = currentScreen == screen,
                            onClick = { currentScreen = screen }
                        )
                    }
                }
            }
        }
    ) {
        when (currentScreen) {
            Screen.ImageDownloader -> ImageDownloaderScreen()
            Screen.About -> AboutScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ImageDownloaderApp()
}
