package br.com.loboneto.coinapi

import app.cash.turbine.test
import br.com.loboneto.coinapi.domain.use_case.GetExchangeListUseCase
import br.com.loboneto.coinapi.ui.exchange.ExchangeViewModel
import br.com.loboneto.coinapi.ui.exchange.list.state_management.ExchangeListState
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ExchangeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private val useCase = mockk<GetExchangeListUseCase>()
    private lateinit var viewModel: ExchangeViewModel


    @Before
    fun init() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ExchangeViewModel(useCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `view model user list state test`() = runTest(testDispatcher) {
        coEvery { useCase.invoke() } returns emptyList()

        viewModel.getExchanges()

        viewModel.state.test {
            assertEquals(
                ExchangeListState(isLoading = true, exchanges = listOf()),
                awaitItem()
            )
            assertEquals(
                ExchangeListState(isLoading = false, exchanges = listOf()),
                awaitItem()
            )
            cancelAndIgnoreRemainingEvents()
        }
    }
}
