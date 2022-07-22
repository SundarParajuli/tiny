package com.example.tinybean.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tinybean.data.Result
import com.example.tinybean.data.source.repository.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val contentRepository: ContentRepository) : ViewModel() {
    init {

    }
    fun fetchContents(){
        println("inside viewmodel")
        viewModelScope.launch {
            contentRepository.fetchContents().apply {
                when(this){
                    is Result.Error -> println(this.message)
                    is Result.Success -> this.data?.content?.forEach {
                        println(it)
                    }
                }
            }
        }
    }
}