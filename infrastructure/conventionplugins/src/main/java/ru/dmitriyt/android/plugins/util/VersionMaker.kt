package ru.dmitriyt.android.plugins.util

import org.gradle.api.Project

object VersionMaker {

    private const val DEFAULT_VERSION_CODE = 1

    fun getVersionCode(project: Project): Int {
        return Command("git rev-list --count HEAD").run(project.rootDir)?.toIntOrNull() ?: DEFAULT_VERSION_CODE
    }
}