package br.com.loboneto.coinapi.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    background = Black,
    onBackground = GrayLight,
    surface = BlackLight,
    onSurface = Gray,
    primary = Orange,
    onPrimary = White,
    secondary = OrangeDark,
    onSecondary = White,
)

private val LightColorScheme = lightColorScheme(
    background = White,
    onBackground = Black,
    surface = GrayLight,
    onSurface = Black,
    primary = Orange,
    onPrimary = White,
    secondary = OrangeLight,
    onSecondary = White,
)

@Composable
fun CoinApiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}