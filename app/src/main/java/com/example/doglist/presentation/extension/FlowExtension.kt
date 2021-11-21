package com.example.doglist.presentation.extension

import androidx.compose.runtime.MutableState
import com.example.doglist.domain.model.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

fun <T, U> Flow<T>.emitStates(
    state: MutableState<ResultState<U>>,
    mapper: (T) -> U
) = this
    .onStart { state.value = ResultState.Loading() }
    .catch { state.value = ResultState.Error(it) }
    .onEach { state.value = ResultState.Success(mapper(it)) }