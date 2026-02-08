package ru.dmitriyt.android.plugins

import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.extensions.core.extra
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.named
import ru.dmitriyt.android.plugins.ext.libs

/**
 * Настроенный плагин Detekt для использования в проекте
 */
class DetektConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(libs.plugins.detekt.get().pluginId)

            with(extensions.getByType<DetektExtension>()) {
                source.setFrom(files(buildSourcePaths(target)))
                config.setFrom(files(listOf("${project.rootDir}/config/quality/detekt/detekt-config.yml")))
                baseline = file("lint/detekt/baseline.xml")

                buildUponDefaultConfig = true
            }

            tasks.named<Detekt>("detekt") {
                reports {
                    html.required.set(true)
                    html.outputLocation.set(file("build/reports/detekt.html"))

                    md.required.set(true)
                    md.outputLocation.set(file("build/reports/detekt.md"))

                    xml.required.set(true)
                    xml.outputLocation.set(file("build/reports/detekt.xml"))

                    txt.required.set(false)
                    sarif.required.set(false)
                }
            }
            dependencies {
                "detektPlugins"(libs.detekt.formatting)
            }
        }
    }

    private fun buildSourcePaths(project: Project): Set<String> {
        val paths = if (project.extra.has("detektSource")) {
            (project.extra["detektSource"] as List<*>).map { it.toString() }
        } else {
            listOf("src/main/kotlin", "src/main/java")
        }

        val modulePaths = mutableSetOf<String>()
        val root = project.subprojects
        if (root.isEmpty()) {
            root.add(project)
        }

        root.iterator().forEachRemaining {
            paths.iterator().forEachRemaining { path ->
                modulePaths.add("${it.projectDir.absolutePath}/$path")
            }
        }
        return modulePaths
    }
}