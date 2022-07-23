package com.example.tinybean.ui.main.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tinybean.data.Result
import com.example.tinybean.data.model.dto.Images
import com.example.tinybean.data.source.repository.ListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val listRepository: ListRepository) : ViewModel() {
    private val _images = MutableLiveData<Result<Images?>>()

    val imagesLiveData: LiveData<Result<Images?>>
        get() = _images

    init {
        fetchList()
    }

    fun fetchList(){
        viewModelScope.launch {
            _images.value = listRepository.fetchLists()
        }
    }
}