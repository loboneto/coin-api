package br.com.loboneto.coinapi.data.remote

import br.com.loboneto.coinapi.core.Constants
import br.com.loboneto.coinapi.data.model.ExchangeIconResponse
import br.com.loboneto.coinapi.data.model.ExchangeResponse
import retrofit2.http.GET
import retrofit2.http.Headers



interface ExchangeApi {
    @Headers("X-CoinAPI-Key: ${Constants.API_KEY}")
    @GET("v1/exchanges")
    suspend fun getExchanges(): List<ExchangeResponse>

    @Headers("X-CoinAPI-Key: ${Constants.API_KEY}")
    @GET("v1/exchanges/icons/24")
    suspend fun getExchangeIcons(): List<ExchangeIconResponse>
}