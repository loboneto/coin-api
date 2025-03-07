package br.com.loboneto.coinapi.data.remote

import br.com.loboneto.coinapi.data.model.ExchangeIconResponse
import br.com.loboneto.coinapi.data.model.ExchangeResponse
import retrofit2.http.GET
import retrofit2.http.Headers

private const val EXCHANGE_RATE_API = "b4666068-ae83-4af1-911e-7aebc3f1ad5c"
private const val MARKET_DATA_API = "726a0868-5e86-4f1b-88e6-77b82c2c1cce"

interface ExchangeApi {
    @Headers("X-CoinAPI-Key: $MARKET_DATA_API")
    @GET("v1/exchanges")
    suspend fun getExchanges(): List<ExchangeResponse>

    @Headers("X-CoinAPI-Key: $MARKET_DATA_API")
    @GET("v1/exchanges/icons/24")
    suspend fun getExchangeIcons(): List<ExchangeIconResponse>
}