package ru.dmitriyt.android.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

class VersionMakerPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        // не требуется ничего, просто плагин чтобы достучаться до класса VersionMaker
    }
}
