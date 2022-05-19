package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.storage.ProductBD
import com.example.myapplication.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun provideProductDB(@ApplicationContext context: Context): ProductBD {
        return Room.databaseBuilder(context, ProductBD::class.java, Constants.PRODUCTS_DB).build()
    }
}