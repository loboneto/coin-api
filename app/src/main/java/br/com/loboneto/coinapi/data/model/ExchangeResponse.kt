package br.com.loboneto.coinapi.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExchangeResponse(
    @SerialName("exchange_id")
    val exchangeId: String,
    @SerialName("website")
    val website: String,
    @SerialName("name")
    val name: String,
    @SerialName("volume_1day_usd")
    val oneDayVolumeUsd: Double,
)
