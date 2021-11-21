package com.example.doglist.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doglist.domain.model.ResultState
import com.example.doglist.domain.usecase.GetDogsUseCase
import com.example.doglist.presentation.extension.emitStates
import com.example.doglist.presentation.mapper.DogUiMapper
import com.example.doglist.presentation.model.DogUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class DogOverviewViewModel @Inject constructor(
    private val getDogsUseCase: GetDogsUseCase
) : ViewModel() {

    private val _dogs = mutableStateOf<ResultState<List<DogUi>>>(ResultState.Loading())
    val dogs: State<ResultState<List<DogUi>>>
        get() = _dogs

    fun getDogs() {
        getDogsUseCase()
            .emitStates(_dogs, DogUiMapper::toUi)
            .launchIn(viewModelScope)
    }
}
