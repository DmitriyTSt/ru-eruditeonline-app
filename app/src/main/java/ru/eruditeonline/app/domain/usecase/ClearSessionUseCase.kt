package ru.eruditeonline.app.domain.usecase

import android.content.Context
import android.content.Intent
import kotlinx.coroutines.withContext
import ru.eruditeonline.app.di.module.DispatcherProvider
import ru.eruditeonline.app.domain.usecase.auth.LocalLogoutUseCase
import ru.eruditeonline.app.domain.usecase.base.UseCaseUnary
import ru.eruditeonline.app.presentation.ui.mainactivity.MainActivity
import javax.inject.Inject
import kotlin.system.exitProcess

/**
 * Очистка данных приложения и запуск с нуля
 */
class ClearSessionUseCase @Inject constructor(
    private val context: Context,
    private val localLogoutUseCase: LocalLogoutUseCase,
    private val dispatcherProvider: DispatcherProvider,
) : UseCaseUnary<Unit, Unit>() {

    override suspend fun execute(params: Unit) {
        localLogoutUseCase.execute(Unit)
        withContext(dispatcherProvider.main) {
            val startActivity = MainActivity.createStartIntent(context, from401Error = true).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            context.startActivity(startActivity)
            exitProcess(0)
        }
    }
}
