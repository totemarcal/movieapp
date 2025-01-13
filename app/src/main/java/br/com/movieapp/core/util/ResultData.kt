package br.com.movieapp.core.util

import java.lang.Exception

sealed class ResultData<out T> {
    object Loading : ResultData<Nothing>()
    data class Success<out T>(val data: T?) : ResultData<T>()
    data class Failure<out T>(val e: Exception?) : ResultData<T>()
}
