package br.com.loboneto.coinapi.core.di

import br.com.loboneto.coinapi.data.remote.ExchangeApi
import br.com.loboneto.coinapi.data.repository.ExchangeRepositoryImpl
import br.com.loboneto.coinapi.domain.mapper.ExchangeMapper
import br.com.loboneto.coinapi.domain.repository.ExchangeRepository
import br.com.loboneto.coinapi.domain.use_case.GetExchangeListUseCase
import br.com.loboneto.coinapi.domain.use_case.GetExchangeListUseCaseImpl
import br.com.loboneto.coinapi.ui.exchange.ExchangeViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        Retrofit.Builder()
            .baseUrl("https://rest.coinapi.io/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(ExchangeApi::class.java)
    }

    single {
        ExchangeMapper()
    }

    single<ExchangeRepository> {
        ExchangeRepositoryImpl(api = get())
    }

    single<GetExchangeListUseCase> {
        GetExchangeListUseCaseImpl(
            repository = get(),
            mapper = get()
        )
    }
    viewModel {
        ExchangeViewModel(getExchangeList = get())
    }
}