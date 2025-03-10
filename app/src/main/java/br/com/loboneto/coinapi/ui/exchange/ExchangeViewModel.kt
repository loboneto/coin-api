package br.com.loboneto.coinapi.ui.exchange

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.loboneto.coinapi.domain.model.Exchange
import br.com.loboneto.coinapi.domain.use_case.GetExchangeListUseCase
import br.com.loboneto.coinapi.ui.exchange.list.state_management.ExchangeListState
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
                _state.update { it.copy(isLoading = false, exchanges = exchanges) }
            }.onFailure { error ->
                _state.update { it.copy(isLoading = false, error = error) }
            }
        }
    }

    fun selectExchange(exchange: Exchange?) {
        viewModelScope.launch {
            _state.update { it.copy(selectedExchange = exchange) }
        }
    }

    fun onQueryChange(query: String) {
        viewModelScope.launch {
            _state.update {
                if (query.isNotBlank()) {
                    it.copy(
                        searchQuery = query,
                        searchedExchanges = it.exchanges.filter { exchange ->
                            exchange.name?.contains(
                                query,
                                ignoreCase = true
                            ) == true
                        }
                    )
                } else {
                    it.copy(
                        searchQuery = query,
                        searchedExchanges = listOf()
                    )
                }
            }
        }
    }
}
