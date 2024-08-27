package ru.eruditeonline.app.data.preferences.base

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class StringPref(
    private val storage: BasePreferencesStorage,
    private val key: String,
    private val defaultValue: String?,
    private val sync: Boolean,
) : ReadWriteProperty<Any, String?> {

    override fun getValue(thisRef: Any, property: KProperty<*>): String? {
        return storage.getString(key, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String?) {
        storage.edit(sync) { putString(key, value) }
    }
}

fun BasePreferencesStorage.string(key: String, defaultValue: String? = null, sync: Boolean = false): StringPref {
    return StringPref(this, key, defaultValue, sync)
}