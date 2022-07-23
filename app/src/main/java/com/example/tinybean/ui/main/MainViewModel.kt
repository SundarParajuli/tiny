package com.example.tinybean.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tinybean.data.Result
import com.example.tinybean.data.model.dto.ContentDto
import com.example.tinybean.data.source.repository.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val contentRepository: ContentRepository) : ViewModel() {
    private val _content = MutableLiveData<List<ContentDto>>()
    val content: LiveData<List<ContentDto>> = _content

    fun fetchContents() {
        viewModelScope.launch {
            contentRepository.fetchContents().apply {
                when (this) {
                    is Result.Error -> println(this.message)
                    is Result.Success -> {
                        var list = this.data?.content?.sortedBy { it.type } ?: listOf()

                        list = list.map {
                            if (it.type == "carousel") {
                                val image = contentRepository.fetchCarouselImage(it.url)
                                if (image != null) {
                                    it.images = image.images
                                }
                            }
                            it
                        }
                        _content.postValue(list)
                    }
                    is Result.Loading -> {}
                }
            }
        }
    }
}