package ru.eruditeonline.app.data.preferences.base

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class BooleanPref(
    private val storage: BasePreferencesStorage,
    private val key: String,
    private val defaultValue: Boolean,
    private val sync: Boolean,
) : ReadWriteProperty<Any, Boolean> {

    override fun getValue(thisRef: Any, property: KProperty<*>): Boolean {
        return storage.getBoolean(key, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
        storage.edit(sync) { putBoolean(key, value) }
    }
}

fun BasePreferencesStorage.boolean(key: String, defaultValue: Boolean = false, sync: Boolean = false): BooleanPref {
    return BooleanPref(this, key, defaultValue, sync)
}