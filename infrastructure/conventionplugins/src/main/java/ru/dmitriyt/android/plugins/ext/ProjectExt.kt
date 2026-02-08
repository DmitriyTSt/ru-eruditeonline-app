package ru.dmitriyt.android.plugins.ext

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.accessors.runtime.extensionOf

val Project.libs
    get(): LibrariesForLibs = extensionOf(this, "libs") as LibrariesForLibs