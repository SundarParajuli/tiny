package com.example.tinybean.data


/**
 * A generic class that holds a value or error.
 * @param <T>
 */
sealed class Result<out R> {

    data class Success<out T>(val data: T? = null) : Result<T>()
    data class Error(val message:String? = "") : Result<Nothing>()
    data class Loading(val message:String? = "") : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$message]"
            is Loading -> ""
        }
    }
}

/**
 * `true` if [Result] is of type [Success] & holds non-null [Success.data].
 */
val Result<*>.succeeded
    get() = this is Result.Success && data != null
