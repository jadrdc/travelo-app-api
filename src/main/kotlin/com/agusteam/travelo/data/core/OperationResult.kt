package com.agusteam.travelo.data.core

sealed interface OperationResult<T> {
    data class Success<T>(val data: T) : OperationResult<T>
    data class Error<T>(val exception: Exception) : OperationResult<T>
}

