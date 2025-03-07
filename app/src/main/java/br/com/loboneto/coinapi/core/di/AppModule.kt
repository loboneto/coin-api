package br.com.loboneto.coinapi.core.di

import br.com.loboneto.coinapi.core.IMapper
import br.com.loboneto.coinapi.data.model.ExchangeIconResponse
import br.com.loboneto.coinapi.data.model.ExchangeResponse
import br.com.loboneto.coinapi.data.remote.ExchangeApi
import br.com.loboneto.coinapi.data.repository.ExchangeRepositoryImpl
import br.com.loboneto.coinapi.domain.mapper.ExchangeMapper
import br.com.loboneto.coinapi.domain.model.Exchange
import br.com.loboneto.coinapi.domain.repository.ExchangeRepository
import br.com.loboneto.coinapi.domain.use_case.GetExchangeListUseCase
import br.com.loboneto.coinapi.domain.use_case.GetExchangeListUseCaseImpl
import br.com.loboneto.coinapi.ui.exchange.ExchangeViewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://rest.coinapi.io/")
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
    factory {
        ExchangeViewModel(getExchangeList = get())
    }
}