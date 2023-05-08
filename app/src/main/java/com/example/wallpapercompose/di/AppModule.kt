package com.example.wallpapercompose.di

import android.content.Context
import androidx.room.Room
import com.example.wallpapercompose.data.api.WallpaperApi
import com.example.wallpapercompose.data.db.AppDB
import com.example.wallpapercompose.repository.WallpaperRepository
import com.example.wallpapercompose.util.Constants
import com.example.wallpapercompose.util.Constants.API_KEY
import com.example.wallpapercompose.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun  provideDatabase(
         @ApplicationContext context: Context
    )= Room.databaseBuilder(context,AppDB::class.java, Constants.Database_Name ).build()

    @Singleton
    @Provides
    fun provideDB(db:AppDB)=db.appDao()



    @Singleton
    @Provides
    fun provideWallRepository(
        api: WallpaperApi
    ) = WallpaperRepository(api)

    @Singleton
    @Provides
    fun provideWallApi(): WallpaperApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(getOkHttpClient())
            .build()
            .create(WallpaperApi::class.java)
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
            .addInterceptor{ chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", API_KEY)
                    .build()
                chain.proceed(request)
            }
            .build()
    }
}