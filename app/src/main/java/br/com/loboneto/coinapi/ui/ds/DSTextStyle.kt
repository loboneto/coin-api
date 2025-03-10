package br.com.loboneto.coinapi.ui.ds

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data object DSTextStyle {
    val header: TextStyle
        @Composable get() = TextStyle(
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

    val primary: TextStyle
        @Composable get() = TextStyle(
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

    val secondary: TextStyle
        @Composable get() = TextStyle(
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 14.sp
        )
}