package br.com.loboneto.coinapi.domain.model

data class Exchange(
    val id: String? = null,
    val website: String? = null,
    val name: String? = null,
    val usdHourlyVolume: String? = null,
    val usdDailyVolume: String? = null,
    val usdMonthlyVolume: String? = null,
    val icon: String? = null
)
