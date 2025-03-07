package br.com.loboneto.coinapi.ui.exchange

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.loboneto.coinapi.domain.provider.ExchangeProvider
import br.com.loboneto.coinapi.ui.exchange.composable.ExchangeListItem
import br.com.loboneto.coinapi.ui.theme.CoinApiTheme

@Composable
fun ExchangeListScreen(
    modifier: Modifier = Modifier,
    state: ExchangeListState = ExchangeListState(),
) {

    Surface(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()

    ) {
        LazyColumn {
            item {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Exchanges"
                )
            }
            items(state.exchanges.size) { index ->
                ExchangeListItem(
                    exchange = state.exchanges[index]
                )
            }
        }
    }
}

@Composable
@PreviewLightDark
fun ExchangeListScreenPreview() {
    CoinApiTheme {
        ExchangeListScreen(
            state = ExchangeListState(
                isLoading = false,
                exchanges = ExchangeProvider.list
            )
        )
    }
}