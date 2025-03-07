package br.com.loboneto.coinapi.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.loboneto.coinapi.ui.exchange.ExchangeListScreen
import br.com.loboneto.coinapi.ui.exchange.ExchangeViewModel
import br.com.loboneto.coinapi.ui.theme.CoinApiTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val viewModel by inject<ExchangeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoinApiTheme {

                val state = viewModel.state.collectAsStateWithLifecycle().value
                LaunchedEffect(Unit) { // Executa apenas uma vez
                    viewModel.getExchanges()
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    ExchangeListScreen(
                        modifier = Modifier.padding(innerPadding),
                        state = state,
                    )
                }
            }
        }
    }
}
