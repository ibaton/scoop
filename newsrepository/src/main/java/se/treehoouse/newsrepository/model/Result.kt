package se.treehoouse.newsrepository.model

import java.lang.Exception

sealed interface Result<T> {
    data class Data<T>(
        val value: T,
        val isRemoteData: Boolean = true
    ): Result<T>

    data class Error<T>(val exception: Exception): Result<T>
    data class ParsingError<T>(val exception: Exception): Result<T>
}