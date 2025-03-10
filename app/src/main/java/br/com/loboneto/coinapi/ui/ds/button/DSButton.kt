package br.com.loboneto.coinapi.ui.ds.button

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
fun DSButtonPreview() {
    CoinApiTheme {
        Column {
            DSButton("Button", onClick = {})
            DSButton("Button", onClick = {}, type = DSButtonType.Secondary)
        }
    }
}

