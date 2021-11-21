package com.example.doglist.data.utils

import com.example.doglist.domain.model.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

fun <T> resultStateFlow(request: suspend () -> T): Flow<ResultState<T>> = flow {
    emit(ResultState.Loading<T>())
    try {
        val response = request()
        emit(ResultState.Success(response))
    } catch (e: Exception) {
        emit(ResultState.Error<T>(e))
    }
}