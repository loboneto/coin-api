package br.com.loboneto.coinapi.ui.exchange.composable

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import br.com.loboneto.coinapi.domain.model.Exchange
import br.com.loboneto.coinapi.domain.provider.ExchangeProvider
import br.com.loboneto.coinapi.ui.theme.CoinApiTheme
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun ExchangeListItem(
    exchange: Exchange,
    modifier: Modifier = Modifier
) {
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {}

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(0.dp),
        onClick = {
            exchange.website?.let {
                val intent = Intent(Intent.ACTION_VIEW, it.toUri())
                launcher.launch(intent)
            }
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
                    modifier = Modifier,
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current).data(exchange.icon)
                            .crossfade(true).build()
                    ),
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSurface),
                    contentDescription = null
                )
            }
            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = exchange.name.orEmpty(),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "${exchange.usdDailyVolume} (24h)",
                    color = MaterialTheme.colorScheme.onSurface
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
            exchange = ExchangeProvider.binance
        )
    }
}