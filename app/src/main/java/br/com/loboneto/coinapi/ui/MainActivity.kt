package br.com.loboneto.coinapi.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.loboneto.coinapi.core.Routes
import br.com.loboneto.coinapi.ui.exchange.ExchangeViewModel
import br.com.loboneto.coinapi.ui.exchange.detail.ExchangeDetailScreen
import br.com.loboneto.coinapi.ui.exchange.list.ExchangeListScreen
import br.com.loboneto.coinapi.ui.theme.CoinApiTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoinApiTheme {
                val viewModel = koinViewModel<ExchangeViewModel>()
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = Routes.ExchangeList.route
                    ) {
                        composable(Routes.ExchangeList.route) {
                            ExchangeListScreen(
                                viewModel = viewModel,
                                navController = navController
                            )
                        }
                        composable(Routes.ExchangeDetail.route) {
                            ExchangeDetailScreen(
                                viewModel = viewModel,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}
