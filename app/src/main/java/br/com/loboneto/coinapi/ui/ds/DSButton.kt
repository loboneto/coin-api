package br.com.loboneto.coinapi.ui.ds

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.loboneto.coinapi.ui.theme.CoinApiTheme

sealed interface DSButtonType {
    data object Primary : DSButtonType
    data object Secondary : DSButtonType
}

@Composable
fun DSButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    type: DSButtonType = DSButtonType.Primary,
) {
    when (type) {
        DSButtonType.Primary -> {
            Button(
                modifier = modifier.fillMaxWidth(),
                onClick = onClick,
                shape = RoundedCornerShape(14.dp),
            ) {
                Text(modifier = Modifier.padding(4.dp), text = text)
            }
        }

        DSButtonType.Secondary -> {
            OutlinedButton(
                modifier = modifier.fillMaxWidth(),
                onClick = onClick,
                shape = RoundedCornerShape(14.dp),
            ) {
                Text(modifier = Modifier.padding(4.dp), text = text)
            }
        }
    }

}

@Composable
@PreviewLightDark
fun DSButtonPreview(modifier: Modifier = Modifier) {
    CoinApiTheme {
        Column {
            DSButton("Teste", onClick = {})
            DSButton("Teste", onClick = {}, type = DSButtonType.Secondary)
        }
    }
}

