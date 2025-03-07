package br.com.loboneto.coinapi.ui.exchange

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.loboneto.coinapi.R
import br.com.loboneto.coinapi.domain.provider.ExchangeProvider
import br.com.loboneto.coinapi.ui.exchange.composable.ExchangeListItem
import br.com.loboneto.coinapi.ui.theme.CoinApiTheme

@Composable
fun ExchangeListScreen(
    modifier: Modifier = Modifier,
    state: ExchangeListState = ExchangeListState(),
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    val query = remember { mutableStateOf("") }

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
        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Column {
                    Header()
                    SearchBar(
                        text = query.value,
                        onTextChange = {
                            query.value = it
                        },
                        focusManager = focusManager,
                        focusRequester = focusRequester
                    )
                }
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
fun Header() {
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
            style = TextStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun SearchBar(
    text: String,
    onTextChange: (String) -> Unit,
    focusRequester: FocusRequester,
    focusManager: FocusManager
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        prefix = {
            Image(
                painter = painterResource(R.drawable.ic_search),
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSurface),
                contentDescription = null
            )
        },
        placeholder = {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = "Search exchange",
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        shape = CircleShape,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.secondary,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
            focusedTextColor = MaterialTheme.colorScheme.onBackground
        ),
        value = text,
        onValueChange = onTextChange,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        )
    )
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