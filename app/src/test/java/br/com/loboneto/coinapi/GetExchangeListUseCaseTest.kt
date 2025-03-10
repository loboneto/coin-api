package br.com.loboneto.coinapi

import br.com.loboneto.coinapi.data.model.ExchangeIconResponse
import br.com.loboneto.coinapi.data.model.ExchangeResponse
import br.com.loboneto.coinapi.domain.mapper.ExchangeMapper
import br.com.loboneto.coinapi.domain.model.Exchange
import br.com.loboneto.coinapi.domain.repository.ExchangeRepository
import br.com.loboneto.coinapi.domain.use_case.GetExchangeListUseCase
import br.com.loboneto.coinapi.domain.use_case.GetExchangeListUseCaseImpl
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetExchangeListUseCaseTest {

    private val mapper = mockk<ExchangeMapper>()
    private val repository = mockk<ExchangeRepository>()
    private lateinit var useCase: GetExchangeListUseCase

    @Before
    fun init() {
        useCase = GetExchangeListUseCaseImpl(repository, mapper)
    }

    @Test
    fun `use case get exchange list test`() = runBlocking {
        val exchangeResponse = emptyList<ExchangeResponse>()
        coEvery { repository.getExchanges() } returns exchangeResponse

        val exchangeIconResponse = emptyList<ExchangeIconResponse>()
        coEvery { repository.getExchangeIcons() } returns exchangeIconResponse

        coEvery { mapper.map(ExchangeResponse(), ExchangeIconResponse()) } returns Exchange()

        val result = useCase.invoke()
        assertEquals(emptyList<Exchange>(), result)
    }
}
