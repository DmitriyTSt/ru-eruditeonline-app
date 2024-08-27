package ru.eruditeonline.app.data.preferences.base

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class IntPref(
    private val storage: BasePreferencesStorage,
    private val key: String,
    private val defaultValue: Int,
    private val sync: Boolean,
) : ReadWriteProperty<Any, Int> {

    override fun getValue(thisRef: Any, property: KProperty<*>): Int {
        return storage.getInt(key, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
        storage.edit(sync) { putInt(key, value) }
    }
}

fun BasePreferencesStorage.int(key: String, defaultValue: Int = 0, sync: Boolean = false): IntPref {
    return IntPref(this, key, defaultValue, sync)
}