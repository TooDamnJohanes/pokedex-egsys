package com.joaootavio.android.pokedex_egsys.common

sealed class ResponseApi<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : ResponseApi<T> (data = data)
    class Error<T> (message: String, data: T? = null): ResponseApi<T>(message = message)
    class Loading<T>(data: T? = null) : ResponseApi<T>(data = data)
}