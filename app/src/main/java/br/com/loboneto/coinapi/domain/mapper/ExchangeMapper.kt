package br.com.loboneto.coinapi.domain.mapper

import br.com.loboneto.coinapi.core.IMapper
import br.com.loboneto.coinapi.data.model.ExchangeIconResponse
import br.com.loboneto.coinapi.data.model.ExchangeResponse
import br.com.loboneto.coinapi.domain.model.Exchange

class ExchangeMapper : IMapper<ExchangeResponse, ExchangeIconResponse?, Exchange> {
    override fun map(from: ExchangeResponse, comp: ExchangeIconResponse?): Exchange {
        return Exchange(
            id = from.exchangeId,
            website = from.website,
            name = from.name,
            usdDailyVolume = from.oneDayVolumeUsd,
            icon = comp?.url
        )
    }
}
