package br.com.loboneto.coinapi.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ExchangeIconResponse(
    @SerializedName("exchange_id")
    val exchangeId: String? = null,
    @SerializedName("url")
    val url: String? = null,
)
