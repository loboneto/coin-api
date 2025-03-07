package br.com.loboneto.coinapi.ui.exchange.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.loboneto.coinapi.domain.model.Exchange
import br.com.loboneto.coinapi.domain.provider.ExchangeProvider
import br.com.loboneto.coinapi.ui.theme.CoinApiTheme

@Composable
fun ExchangeListItem(
    exchange: Exchange,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surface)
            .border(width = 0.dp, color = Color.Transparent, shape = RoundedCornerShape(14.dp))
            .fillMaxWidth()
    ) {


        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = exchange.name,
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "${exchange.usdDailyVolume} (24h)",
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
@PreviewLightDark
fun ExchangeListItemPreview() {
    CoinApiTheme {
        ExchangeListItem(
            exchange = ExchangeProvider.binance
        )
    }
}