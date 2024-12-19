package com.example.yourapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material.MaterialTheme

// Определение цветов
private val LightColorPalette = lightColors(
    primary = Blue,
    primaryVariant = DarkBlue,
    secondary = LightBlue
)

private val DarkColorPalette = darkColors(
    primary = DarkBlue,
    primaryVariant = Blue,
    secondary = LightBlue
)

// Определение типографики
val Typography = Typography(
    defaultFontFamily = FontFamily.Default,
    h1 = TextStyle(
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold
    ),
    body1 = TextStyle(
        fontSize = 20.sp
    )
)

@Composable
fun YourAppTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
