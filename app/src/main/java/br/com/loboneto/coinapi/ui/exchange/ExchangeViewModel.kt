package br.com.loboneto.coinapi.ui.exchange

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.loboneto.coinapi.domain.use_case.GetExchangeListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExchangeViewModel(
    private val getExchangeList: GetExchangeListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ExchangeListState())
    val state = _state.asStateFlow()

    fun getExchanges() {
        viewModelScope.launch {
            runCatching {
                _state.update { it.copy(isLoading = true) }
                val exchanges = getExchangeList()
                println("---> exchanges: $exchanges")
                _state.update { it.copy(isLoading = false, exchanges = exchanges) }
            }.onFailure { error ->
                println("---> error: $error")
                _state.update { it.copy(isLoading = false, error = error) }
            }
        }
    }
}
