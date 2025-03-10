package br.com.loboneto.coinapi.domain.mapper

import br.com.loboneto.coinapi.core.IMapper
import br.com.loboneto.coinapi.data.model.ExchangeIconResponse
import br.com.loboneto.coinapi.data.model.ExchangeResponse
import br.com.loboneto.coinapi.domain.model.Exchange
import java.text.NumberFormat
import java.util.Locale

class ExchangeMapper : IMapper<ExchangeResponse, ExchangeIconResponse?, Exchange> {
    override fun map(from: ExchangeResponse, comp: ExchangeIconResponse?): Exchange {
        return Exchange(
            id = from.exchangeId,
            website = from.website,
            name = from.name,
            usdHourlyVolume = NumberFormat.getCurrencyInstance(Locale.US)
                .format(from.oneHourVolumeUsd),
            usdDailyVolume = NumberFormat.getCurrencyInstance(Locale.US)
                .format(from.oneDayVolumeUsd),
            usdMonthlyVolume = NumberFormat.getCurrencyInstance(Locale.US)
                .format(from.oneMonthVolumeUsd),
            icon = comp?.url
        )
    }
}
