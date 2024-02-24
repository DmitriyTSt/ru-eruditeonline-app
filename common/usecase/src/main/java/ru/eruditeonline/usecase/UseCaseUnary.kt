package ru.eruditeonline.usecase

interface UseCaseUnary<in Params, Result> {

    suspend fun execute(params: Params): Result
}
