package br.com.loboneto.coinapi.domain.use_case

import br.com.loboneto.coinapi.domain.mapper.ExchangeMapper
import br.com.loboneto.coinapi.domain.model.Exchange
import br.com.loboneto.coinapi.domain.repository.ExchangeRepository

interface GetExchangeListUseCase {
    suspend operator fun invoke(): List<Exchange>
}

class GetExchangeListUseCaseImpl(
    private val repository: ExchangeRepository,
    private val mapper: ExchangeMapper
) : GetExchangeListUseCase {
    override suspend fun invoke(): List<Exchange> {
        val exchanges = repository.getExchanges()
        println("---> exchanges: $exchanges")
        val icons = repository.getExchangeIcons()
        println("---> icons: $icons")
        return exchanges.map {
            mapper.map(
                it,
                icons.first { icon -> icon.exchangeId == it.exchangeId }
            )
        }
    }
}
