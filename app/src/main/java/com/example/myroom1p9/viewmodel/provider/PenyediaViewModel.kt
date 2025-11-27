package com.example.myroom1p9.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myroom1p9.repositori.OfflineRepositoriSiswa
import com.example.myroom1p9.room.DatabaseSiswa

object PenyediaViewModel {

    fun provideFactory(context: Context): ViewModelProvider.Factory {

        val dao = DatabaseSiswa.getDatabase(context).siswaDao()
        val repo = OfflineRepositoriSiswa(dao)

        return object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return when {
                    modelClass.isAssignableFrom(EntryViewModel::class.java) ->
                        EntryViewModel(repo) as T

                    modelClass.isAssignableFrom(HomeViewModel::class.java) ->
                        HomeViewModel(repo) as T

                    else -> throw IllegalArgumentException(
                        "Unknown ViewModel class: $modelClass"
                    )
                }
            }
        }
    }
}
