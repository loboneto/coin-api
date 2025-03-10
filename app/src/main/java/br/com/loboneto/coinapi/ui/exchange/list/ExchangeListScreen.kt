package br.com.loboneto.coinapi.ui.exchange.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import br.com.loboneto.coinapi.R
import br.com.loboneto.coinapi.domain.model.Exchange
import br.com.loboneto.coinapi.domain.provider.ExchangeProvider
import br.com.loboneto.coinapi.ui.Routes
import br.com.loboneto.coinapi.ui.ds.DSSearchBar
import br.com.loboneto.coinapi.ui.ds.DSTextStyle
import br.com.loboneto.coinapi.ui.exchange.ExchangeViewModel
import br.com.loboneto.coinapi.ui.exchange.list.component.ExchangeListItem
import br.com.loboneto.coinapi.ui.exchange.list.state_management.ExchangeListState
import br.com.loboneto.coinapi.ui.theme.CoinApiTheme

@Composable
fun ExchangeListScreen(
    modifier: Modifier = Modifier,
    viewModel: ExchangeViewModel,
    navController: NavController
) {

    val state = viewModel.state.collectAsStateWithLifecycle().value
    println("---> state: $state")

    LaunchedEffect(Unit) {
        viewModel.getExchanges()
    }

    ExchangeListPage(
        modifier = modifier,
        state = state,
        onListItemClick = {
            viewModel.selectExchange(it)
            navController.navigate(Routes.ExchangeDetail.route)
        },
        onSearchQueryChanged = {
            viewModel.onQueryChange(it)
        }
    )
}

@Composable
private fun ExchangeListPage(
    state: ExchangeListState,
    modifier: Modifier = Modifier,
    onListItemClick: (Exchange) -> Unit,
    onSearchQueryChanged: (String) -> Unit,
) {
    val exchanges = state.searchedExchanges.takeIf {
        state.searchQuery.isNotEmpty()
    } ?: state.exchanges

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .clickable {
                focusManager.clearFocus()
            },
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    focusRequester.requestFocus()
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onBackground
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_search),
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onPrimary),
                    contentDescription = null
                )
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
        ) {
            Header()
            DSSearchBar(
                text = state.searchQuery,
                onTextChange = {
                    onSearchQueryChanged(it)
                },
                focusManager = focusManager,
                focusRequester = focusRequester
            )

            LazyColumn(
                modifier = Modifier.padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(exchanges.size) { index ->
                    ExchangeListItem(
                        exchange = exchanges[index],
                        onClick = {
                            onListItemClick(it)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun Header() {
    Box(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            text = "Exchanges",
            style = DSTextStyle.header
        )
    }
}

@Composable
@PreviewLightDark
fun ExchangeListScreenPreview() {
    CoinApiTheme {
        ExchangeListPage(
            state = ExchangeListState(
                exchanges = ExchangeProvider.list
            ),
            onListItemClick = {},
            onSearchQueryChanged = {}
        )
    }
}
