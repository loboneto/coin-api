package br.com.loboneto.coinapi.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ExchangeResponse(
    @SerializedName("exchange_id")
    val exchangeId: String?,
    @SerializedName("website")
    val website: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("volume_1day_usd")
    val oneDayVolumeUsd: Double?,
)
