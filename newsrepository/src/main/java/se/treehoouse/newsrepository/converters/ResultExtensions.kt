package se.treehoouse.newsrepository.converters

import se.treehoouse.newsrepository.model.Result
import java.lang.Exception

fun <T> T.toSuccess(): Result.Data<T> = Result.Data(this)

fun <T> Exception.toFailure(): Result.Error<T> = Result.Error(this)