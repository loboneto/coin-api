package br.com.loboneto.coinapi.ui.exchange

import br.com.loboneto.coinapi.domain.model.Exchange

data class ExchangeListState(
    val isLoading: Boolean = true,
    val exchanges: List<Exchange> = listOf(),
    val error: Throwable? = null
)
