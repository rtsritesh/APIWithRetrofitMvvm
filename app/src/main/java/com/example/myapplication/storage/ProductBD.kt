package com.example.myapplication.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.model.Product

@Database(entities = [Product::class], version = 1)
abstract class ProductBD : RoomDatabase() {

    abstract fun getProductDao(): ProductDAO
}