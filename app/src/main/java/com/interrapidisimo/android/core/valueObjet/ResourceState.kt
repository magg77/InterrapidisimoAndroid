package com.interrapidisimo.android.core.valueObjet

sealed class ResourceState<out T> {

    class LoadingState<T>: ResourceState<T>()

    data class SuccessState<out T>(val data: T): ResourceState<T>()

    data class FailureState(val message: String): ResourceState<Nothing>()

}