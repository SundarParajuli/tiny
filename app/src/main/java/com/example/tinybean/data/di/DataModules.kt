package com.example.tinybean.data.di

import android.app.Application
import android.content.Context
import androidx.viewbinding.BuildConfig
import com.example.tinybean.data.constant.API
import com.example.tinybean.data.source.datasource.BrandDataSource
import com.example.tinybean.data.source.datasource.ContentDataSource
import com.example.tinybean.data.source.datasource.ListDataSource
import com.example.tinybean.data.source.datasource.WeddingDataSource
import com.example.tinybean.data.source.defaultRepository.BrandRepositoryImpl
import com.example.tinybean.data.source.defaultRepository.ContentRepositoryImpl
import com.example.tinybean.data.source.defaultRepository.ListRepositoryImpl
import com.example.tinybean.data.source.defaultRepository.WeddingRepositoryImpl
import com.example.tinybean.data.source.remote.api.*
import com.example.tinybean.data.source.repository.BrandRepository
import com.example.tinybean.data.source.repository.ContentRepository
import com.example.tinybean.data.source.repository.ListRepository
import com.example.tinybean.data.source.repository.WeddingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesContext(app: Application): Context = app.applicationContext
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun providesOkHttpClient(cache: Cache): OkHttpClient {
        if (BuildConfig.DEBUG) {
            val logger = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
            return OkHttpClient.Builder()
                .addInterceptor(logger)
                .addNetworkInterceptor(CacheInterceptor())
                .cache(cache)
                .build()
        }

        return OkHttpClient.Builder()
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    private const val CACHE_SIZE = 5 * 1024 * 1024L // 5 MB

    @Singleton
    @Provides
    fun httpCache(application: Application): Cache {
        return Cache(application.applicationContext.cacheDir, CACHE_SIZE)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideApiBrandRemoteDataSource(apiService:ApiService): BrandDataSource = MockApiBrandRemoteDataSource(apiService)

    @Singleton
    @Provides
    fun provideApiContentRemoteDataSource(apiService:ApiService): ContentDataSource = MockApiContentRemoteDataSource(apiService)

    @Singleton
    @Provides
    fun provideApiListRemoteDataSource(apiService:ApiService): ListDataSource = MockApiListRemoteDataSource(apiService)

    @Singleton
    @Provides
    fun provideApiWeddingRemoteDataSource(apiService:ApiService): WeddingDataSource = MockApiWeddingRemoteDataSource(apiService)

}


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideBrandRepository(
        brandDataSource: BrandDataSource,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): BrandRepository {
        return BrandRepositoryImpl(brandDataSource, ioDispatcher)
    }

    @Singleton
    @Provides
    fun provideContentRepository(
        contentDataSource: ContentDataSource,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): ContentRepository {
        return ContentRepositoryImpl(contentDataSource, ioDispatcher)
    }

    @Singleton
    @Provides
    fun provideListRepository(
        listDataSource: ListDataSource,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): ListRepository {
        return ListRepositoryImpl(listDataSource, ioDispatcher)
    }

    @Singleton
    @Provides
    fun provideWeddingRepository(
        weddingDataSource: WeddingDataSource,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): WeddingRepository {
        return WeddingRepositoryImpl(weddingDataSource, ioDispatcher)
    }



}


class CacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val originalResponse = chain.proceed(request)

        val shouldUseCache = request.header(CACHE_CONTROL_HEADER) != CACHE_CONTROL_NO_CACHE
        if (!shouldUseCache) return originalResponse

        val cacheControl = CacheControl.Builder()
            .maxAge(10, TimeUnit.MINUTES)
            .build()

        return originalResponse.newBuilder()
            .header(CACHE_CONTROL_HEADER, cacheControl.toString())
            .build()
    }
}





