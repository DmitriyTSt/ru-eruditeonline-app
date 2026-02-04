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
                config.setFrom(files(buildDetektConfigs(target)))
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


    /**
     * Построение списка путей конфига
     * Дефолтный конфиг buildDefaultDetektConfigPath() всегда первый.
     * Если есть свойство detektCustomRulesConfigs, то добавляются пути указанные в этом свойстве(Необходимо для добавления своих правил)
     */
    private fun buildDetektConfigs(project: Project): List<String> {
        val configs = mutableListOf<String>()
        configs.add(buildDefaultDetektConfigPath(project))
        if (project.extra.has("detektCustomRulesConfigs")) {
            configs.addAll((project.extra["detektCustomRulesConfigs"] as List<*>).map { it.toString() })
        }
        return configs
    }

    private fun buildDefaultDetektConfigPath(project: Project): String {
        val detektConfigFileName = "detekt-config.yml"

        return if (project.extra.has("detektConfigPath")) {
            "${project.rootDir}/${project.extra["detektConfigPath"]}/$detektConfigFileName"
        } else {
            "${project.rootDir}/config/quality/detekt/$detektConfigFileName"
        }
    }

    /**
     *  Логика работы по определению путей исходников следующая:
     *  1. находим хвосты путей от корня модуля. Либо установленная на уровне проекта переменная
     *     'detektSource' либо дефолтные пути (kotlin/java)
     *  2. определяем модули если они имеются (не монолит кода). если скрипт изначально прикрепили в
     *     корень - то будут искаться подмодули (как минимум будет найден дефолтный модуль app). Если
     *     скрипт применяли к каждому модулю отдельно - то в нем не будет подпроектов и путь будет
     *     изначальный до самого модуля
     */
    private fun buildSourcePaths(project: Project): Set<String> {
        // определяем хвосты путей исходников
        val paths = if (project.extra.has("detektSource")) {
            (project.extra["detektSource"] as List<*>).map { it.toString() }
        } else {
            listOf("src/main/kotlin", "src/main/java")
        }

        // определяем зависимые модули
        val modulePaths = mutableSetOf<String>()
        val root = project.subprojects
        if (root.isEmpty()) {
            root.add(project)
        }

        // склеиваем пути модулей и хвосты до исходников
        root.iterator().forEachRemaining {
            paths.iterator().forEachRemaining { path ->
                modulePaths.add("${it.projectDir.absolutePath}/$path")
            }
        }
        return modulePaths
    }
}