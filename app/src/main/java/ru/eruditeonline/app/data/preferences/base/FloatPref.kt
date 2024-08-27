package ru.eruditeonline.app.data.preferences.base

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class FloatPref(
    private val storage: BasePreferencesStorage,
    private val key: String,
    private val defaultValue: Float,
    private val sync: Boolean,
) : ReadWriteProperty<Any, Float> {

    override fun getValue(thisRef: Any, property: KProperty<*>): Float {
        return storage.getFloat(key, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Float) {
        storage.edit(sync) { putFloat(key, value) }
    }
}

fun BasePreferencesStorage.float(key: String, defaultValue: Float = 0f, sync: Boolean = false): FloatPref {
    return FloatPref(this, key, defaultValue, sync)
}