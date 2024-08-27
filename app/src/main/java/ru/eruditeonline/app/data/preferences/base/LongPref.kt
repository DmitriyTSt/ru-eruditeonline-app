package ru.eruditeonline.app.data.preferences.base

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class LongPref(
    private val storage: BasePreferencesStorage,
    private val key: String,
    private val defaultValue: Long,
    private val sync: Boolean,
) : ReadWriteProperty<Any, Long> {

    override fun getValue(thisRef: Any, property: KProperty<*>): Long {
        return storage.getLong(key, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Long) {
        storage.edit(sync) { putLong(key, value) }
    }
}

fun BasePreferencesStorage.long(key: String, defaultValue: Long = 0, sync: Boolean = false): LongPref {
    return LongPref(this, key, defaultValue, sync)
}