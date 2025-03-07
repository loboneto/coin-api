package br.com.loboneto.coinapi.data.repository

import br.com.loboneto.coinapi.data.model.ExchangeIconResponse
import br.com.loboneto.coinapi.data.model.ExchangeResponse
import br.com.loboneto.coinapi.data.remote.ExchangeApi
import br.com.loboneto.coinapi.domain.repository.ExchangeRepository

class ExchangeRepositoryImpl(
    val api: ExchangeApi
) : ExchangeRepository {
    override suspend fun getExchanges(): List<ExchangeResponse> {
        return api.getExchanges()
    }

    override suspend fun getExchangeIcons(): List<ExchangeIconResponse> {
        return api.getExchangeIcons()
    }
}
