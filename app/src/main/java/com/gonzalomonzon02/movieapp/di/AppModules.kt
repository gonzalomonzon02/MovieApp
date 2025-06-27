package com.gonzalomonzon02.movieapp.di

import androidx.room.Room
import com.gonzalomonzon02.movieapp.BuildConfig
import com.gonzalomonzon02.movieapp.data.local.MovieDatabase
import com.gonzalomonzon02.movieapp.data.remote.MovieService
import com.gonzalomonzon02.movieapp.data.remote.interceptor.AddAuthorizationHeaderInterceptor
import com.gonzalomonzon02.movieapp.data.remote.interceptor.AddSettingHeaderInterceptor
import com.gonzalomonzon02.movieapp.data.remote.interceptor.LanguageInterceptor
import com.gonzalomonzon02.movieapp.data.repository.MovieRepositoryImpl
import com.gonzalomonzon02.movieapp.domain.repository.MovieRepository
import com.gonzalomonzon02.movieapp.domain.usecase.GetMovieDetailUseCase
import com.gonzalomonzon02.movieapp.domain.usecase.GetPopularMoviesUseCase
import com.gonzalomonzon02.movieapp.domain.usecase.GetRelatedMoviesUseCase
import com.gonzalomonzon02.movieapp.presentation.screens.main.MainViewModel
import com.gonzalomonzon02.movieapp.presentation.screens.moviedetail.MovieDetailViewModel
import com.gonzalomonzon02.movieapp.presentation.screens.splash.SplashViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val localDBModule = module {
    single {
        Room.databaseBuilder(
            get(),
            MovieDatabase::class.java,
            MovieDatabase.LOCAL_DB_NAME
        ).build()
    }

    single { get<MovieDatabase>().movieDao() }
}

val networkingModule = module {
    single {
        OkHttpClient.Builder()
            .addNetworkInterceptor(AddAuthorizationHeaderInterceptor())
            .addNetworkInterceptor(AddSettingHeaderInterceptor())
            .addNetworkInterceptor(LanguageInterceptor())
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }
}

val serviceModule = module {
    single { get<Retrofit>().create(MovieService::class.java) }
}

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
}

val useCaseModule = module {
    single { GetPopularMoviesUseCase(movieRepository = get()) }
    single { GetMovieDetailUseCase(movieRepository = get()) }
    single { GetRelatedMoviesUseCase(movieRepository = get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { MovieDetailViewModel(get(), get()) }
}

val appModules = listOf(
    localDBModule,
    networkingModule,
    serviceModule,
    repositoryModule,
    useCaseModule,
    viewModelModule
)
