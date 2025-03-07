package br.com.loboneto.coinapi.domain.repository

import br.com.loboneto.coinapi.data.model.ExchangeIconResponse
import br.com.loboneto.coinapi.data.model.ExchangeResponse

interface ExchangeRepository {
    suspend fun getExchanges(): List<ExchangeResponse>
    suspend fun getExchangeIcons(): List<ExchangeIconResponse>
}
