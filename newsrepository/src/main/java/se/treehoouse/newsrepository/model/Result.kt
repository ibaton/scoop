package se.treehoouse.newsrepository.model

import java.lang.Exception

sealed interface Result<T> {
    data class Data<T>(
        val value: T,
        val isRemoteData: Boolean = true
    ): Result<T>

    data class Error<T>(val exception: Exception): ErrorResult, Result<T>
    data class ParsingError<T>(val exception: Exception): ErrorResult, Result<T>
    data class NoNetworkError<T>(val exception: Exception): ErrorResult, Result<T>
}

sealed interface ErrorResult
