package ru.eruditeonline.app.data.mapper

import kotlinx.serialization.SerialName
import retrofit2.Converter
import retrofit2.Retrofit
import timber.log.Timber
import java.lang.reflect.Type

class EnumConverterFactory : Converter.Factory() {
    override fun stringConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<*, String>? {
        return if (type is Class<*> && type.isEnum) {
            Converter<Any?, String> { value -> getSerialNameValue(value as Enum<*>) }
        } else {
            null
        }
    }

    private fun <E : Enum<*>> getSerialNameValue(e: E): String {
        return try {
            e.javaClass.getField(e.name).getAnnotation(SerialName::class.java)?.value ?: e.name
        } catch (ex: NoSuchFieldException) {
            Timber.e(ex)
            e.name
        }
    }
}
