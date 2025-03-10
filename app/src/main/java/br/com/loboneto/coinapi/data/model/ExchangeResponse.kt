package br.com.loboneto.coinapi.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ExchangeResponse(
    @SerializedName("exchange_id")
    val exchangeId: String? = null,
    @SerializedName("website")
    val website: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("volume_1hrs_usd")
    val oneHourVolumeUsd: Double? = null,
    @SerializedName("volume_1day_usd")
    val oneDayVolumeUsd: Double? = null,
    @SerializedName("volume_1mth_usd")
    val oneMonthVolumeUsd: Double? = null,
)
