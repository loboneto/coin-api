package br.com.loboneto.coinapi.ui.exchange.detail

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import br.com.loboneto.coinapi.R
import br.com.loboneto.coinapi.domain.provider.ExchangeProvider
import br.com.loboneto.coinapi.ui.ds.button.DSButton
import br.com.loboneto.coinapi.ui.ds.button.DSButtonType
import br.com.loboneto.coinapi.ui.ds.DSTextStyle
import br.com.loboneto.coinapi.ui.exchange.ExchangeViewModel
import br.com.loboneto.coinapi.ui.exchange.list.state_management.ExchangeListState
import br.com.loboneto.coinapi.ui.theme.CoinApiTheme
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun ExchangeDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: ExchangeViewModel,
    navController: NavController
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {}

    ExchangeDetailPage(
        modifier = modifier,
        state = state,
        onBackPressed = {
            viewModel.selectExchange(null)
            navController.navigateUp()
        },
        onOpenWebsite = {
            launcher.launch(
                Intent(Intent.ACTION_VIEW, it.toUri())
            )
        }
    )
}

@Composable
private fun ExchangeDetailPage(
    state: ExchangeListState,
    onBackPressed: () -> Unit,
    onOpenWebsite: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier.fillMaxSize(),
    ) {
        Column {
            Box(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp)
                    .size(40.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(14.dp)
                    )
                    .clickable {
                        onBackPressed()
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.padding(12.dp),
                    painter = painterResource(R.drawable.ic_back),
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSurface),
                    contentDescription = null
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(14.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.padding(8.dp),
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current)
                                .data(state.selectedExchange?.icon)
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
                        text = state.selectedExchange?.name.orEmpty(),
                        style = DSTextStyle.primary
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = state.selectedExchange?.id.orEmpty(),
                        style = DSTextStyle.secondary
                    )
                }
                Image(
                    modifier = Modifier.padding(8.dp),
                    painter = painterResource(
                        R.drawable.ic_search
                    ),
                    contentDescription = null
                )
            }

            state.selectedExchange?.usdHourlyVolume?.let {
                Text(
                    modifier = Modifier
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    text = "Volume de negociação (h)"
                )
                Text(
                    modifier = Modifier
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    text = it,
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            state.selectedExchange?.usdDailyVolume?.let {
                Text(
                    modifier = Modifier
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    text = "Volume de negociação (24h)"
                )
                Text(
                    modifier = Modifier
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    text = it,
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            state.selectedExchange?.usdMonthlyVolume?.let {
                Text(
                    modifier = Modifier
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    text = "Volume de negociação (30d)"
                )
                Text(
                    modifier = Modifier
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    text = it,
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            state.selectedExchange?.website?.let {
                DSButton(
                    modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp),
                    text = "Abrir site da corretora",
                    type = DSButtonType.Secondary,
                    onClick = {
                        onOpenWebsite(it)
                    }
                )
            }
        }
    }
}

@Composable
@PreviewLightDark
fun ExchangeDetailScreenPreview() {
    CoinApiTheme {
        ExchangeDetailPage(
            state = ExchangeListState(
                selectedExchange = ExchangeProvider.binance
            ),
            onBackPressed = {},
            onOpenWebsite = {}
        )
    }
}