package br.com.loboneto.coinapi.ui.exchange.list.state_management

import br.com.loboneto.coinapi.domain.model.Exchange

data class ExchangeListState(
    val isLoading: Boolean = true,
    val searchQuery: String = "",
    val searchedExchanges: List<Exchange> = listOf(),
    val exchanges: List<Exchange> = listOf(),
    val selectedExchange: Exchange? = null,
    val error: Throwable? = null
)
