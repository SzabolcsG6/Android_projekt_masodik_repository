package com.tasty.recipesapp.repositories

import android.content.Context

interface IGenericRepository<T> {
    fun getAll(context: Context): List<T>

}