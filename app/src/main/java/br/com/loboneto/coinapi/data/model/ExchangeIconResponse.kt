package br.com.loboneto.coinapi.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExchangeIconResponse(
    @SerialName("exchange_id")
    val exchangeId: String,
    @SerialName("url")
    val url: String,
)
