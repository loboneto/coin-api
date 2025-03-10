package br.com.loboneto.coinapi

import br.com.loboneto.coinapi.data.model.ExchangeIconResponse
import br.com.loboneto.coinapi.data.model.ExchangeResponse
import br.com.loboneto.coinapi.domain.repository.ExchangeRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ExchangeRepositoryTest {

    private val repository = mockk<ExchangeRepository>(relaxed = true)

    @Test
    fun `repo get exchanges test`() = runBlocking {
        val expected = emptyList<ExchangeResponse>()
        coEvery { repository.getExchanges() } returns expected
        val exchanges = repository.getExchanges()
        assertEquals(exchanges, expected)
    }

    @Test
    fun `repo get exchange icons test`() = runBlocking {
        val expected = emptyList<ExchangeIconResponse>()
        coEvery { repository.getExchangeIcons() } returns expected
        val icons = repository.getExchangeIcons()
        assertEquals(icons, expected)
    }
}
