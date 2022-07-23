package com.example.tinybean.ui.main.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.tinybean.MainCoroutineRule
import com.example.tinybean.data.Result
import com.example.tinybean.data.succeeded
import com.example.tinybean.getOrAwaitValue
import com.example.tinybean.repository.FakeListRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.hamcrest.MatcherAssert.assertThat


@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class DetailViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    lateinit var viewModel: DetailViewModel


    private val repository: FakeListRepository = FakeListRepository()
    @Before
    fun setUp() {
        viewModel = DetailViewModel(repository)
    }

    @Test
    fun getImagesLiveData() {
        val response = viewModel.imagesLiveData.getOrAwaitValue()
        when(response){
            is Result.Error ->  assertThat(response.message, equals("something went wrong"))
            is Result.Loading -> assertThat(response.message,equals(""))
            is Result.Success -> assertThat(response.data?.images?.size,equalTo(2))
        }

    }

}