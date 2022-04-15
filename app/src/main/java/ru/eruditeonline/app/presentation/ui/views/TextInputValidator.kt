package ru.eruditeonline.app.presentation.ui.views

/**
 * Интерфейс для валидации текстовых полей во вьюмоделях.
 */
interface TextInputValidator {
    fun validate(): Boolean
    val text: String
    /** Получение ID TextInputLayout'а для скролла к некорректным полям */
    fun getLayoutId(): Int
}
