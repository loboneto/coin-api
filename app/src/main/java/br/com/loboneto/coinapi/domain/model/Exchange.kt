package br.com.loboneto.coinapi.domain.model

data class Exchange(
    val id: String,
    val website: String,
    val name: String,
    val usdDailyVolume: Double,
    val icon: String?
)
