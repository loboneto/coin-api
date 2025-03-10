package br.com.loboneto.coinapi.ui.exchange.list.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.loboneto.coinapi.domain.model.Exchange
import br.com.loboneto.coinapi.domain.provider.ExchangeProvider
import br.com.loboneto.coinapi.ui.ds.DSTextStyle
import br.com.loboneto.coinapi.ui.theme.CoinApiTheme
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun ExchangeListItem(
    exchange: Exchange,
    onClick: (Exchange) -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(0.dp),
        onClick = {
            onClick(exchange)
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)

                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(14.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.padding(8.dp),
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current).data(exchange.icon)
                            .crossfade(true).build()
                    ),
                    contentDescription = null
                )
            }
            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = exchange.name.orEmpty(),
                    style = DSTextStyle.primary,
                )
                Text(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .fillMaxWidth(),
                    text = "${exchange.usdDailyVolume} (24h)",
                    style = DSTextStyle.secondary,
                )
            }
        }
    }
}

@Composable
@PreviewLightDark
fun ExchangeListItemPreview() {
    CoinApiTheme {
        ExchangeListItem(
            exchange = ExchangeProvider.binance,
            onClick = {}
        )
    }
}