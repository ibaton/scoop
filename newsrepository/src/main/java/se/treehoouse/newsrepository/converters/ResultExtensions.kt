package se.treehoouse.newsrepository.converters

import se.treehoouse.newsrepository.model.Result
import java.lang.Exception
import java.net.UnknownHostException

fun <T> T.toSuccess(): Result.Data<T> = Result.Data(this)

fun <T> Exception.toFailure(): Result<T> {
    return when (this) {
        is IllegalArgumentException -> {
            Result.ParsingError(this)
        }
        is UnknownHostException -> {
            Result.NoNetworkError(this)
        }
        else -> {
            Result.Error(this)
        }
    }
}