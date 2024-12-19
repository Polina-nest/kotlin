/*Scaffold: Это основной контейнер, который управляет структурой пользовательского интерфейса.

TopAppBar: Отображает название текущего экрана, которое берется из перечисления Screen.

BottomAppBar: Содержит элементы навигации для переключения между экранами. Каждый элемент отображает иконку и название экрана.

DrawerContent: Позволяет пользователю переключаться между экранами через боковое меню (drawer).

В зависимости от выбранного экрана (currentScreen), отображается соответствующий контент ImageDownloaderScreen или AboutScreen
*/


package com.example.imagedownloader
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

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
        },
        drawerContent = {
            DrawerContent { selectedScreen ->
                currentScreen = selectedScreen
                scaffoldState.drawerState.close()
            }
        }
    ) {
        // Основное содержимое, меняется в зависимости от текущего экрана
        when (currentScreen) {
            Screen.ImageDownloader -> ImageDownloaderScreen()
            Screen.About -> AboutScreen()
        }
    }
}
