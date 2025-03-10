package br.com.loboneto.coinapi

import br.com.loboneto.coinapi.data.model.ExchangeIconResponse
import br.com.loboneto.coinapi.data.model.ExchangeResponse
import br.com.loboneto.coinapi.data.remote.ExchangeApi
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ExchangeApiTest {

    private val api = mockk<ExchangeApi>(relaxed = true)

    @Test
    fun `api get exchanges test`() = runBlocking {
        val expected = emptyList<ExchangeResponse>()
        coEvery { api.getExchanges() } returns expected
        val exchanges = api.getExchanges()
        assertEquals(expected, exchanges)
    }

    @Test
    fun `api get exchange icons test`() = runBlocking {
        val expected = emptyList<ExchangeIconResponse>()
        coEvery { api.getExchangeIcons() } returns expected
        val icons = api.getExchangeIcons()
        assertEquals(expected, icons)
    }
}
