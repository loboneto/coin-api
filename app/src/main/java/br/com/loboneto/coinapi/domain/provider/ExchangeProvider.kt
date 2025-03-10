package br.com.loboneto.coinapi.domain.provider

import br.com.loboneto.coinapi.domain.model.Exchange

object ExchangeProvider {
    val binance = Exchange(
        id = "BINANCE",
        website = "https://www.binance.com/",
        name = "Binance",
        usdHourlyVolume = "U\$D 1,200,00,000.34",
        usdDailyVolume = "U\$D 1,200,00,000.34",
        usdMonthlyVolume = "U\$D 1,200,00,000.34",
        icon = "https://s3.eu-central-1.amazonaws.com/bbxt-static-icons/type-id/png_512/5503eb9673f9437988702f06cbd7072b.png"
    )

    val coinbase = Exchange(
        id = "COINBASE",
        website = "https://pro.coinbase.com/",
        name = "Coinbase",
        usdHourlyVolume = "U\$D 80,000,000.45",
        usdDailyVolume = "U\$D 80,000,000.45",
        usdMonthlyVolume = "U\$D 80,000,000.45",
        icon = "https://s3.eu-central-1.amazonaws.com/bbxt-static-icons/type-id/png_512/5503eb9673f9437988702f06cbd7072b.png"
    )

    val list = listOf(binance, coinbase)
}
