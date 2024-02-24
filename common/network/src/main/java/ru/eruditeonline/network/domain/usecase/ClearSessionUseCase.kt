package ru.eruditeonline.network.domain.usecase

import ru.eruditeonline.usecase.UseCaseUnary

/**
 * Очистка данных приложения и запуск с нуля
 */
interface ClearSessionUseCase : UseCaseUnary<Unit, Unit>
