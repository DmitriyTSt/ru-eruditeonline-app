import org.gradle.initialization.DependenciesAccessors
import org.gradle.kotlin.dsl.support.serviceOf

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    mavenCentral()
}

dependencies {
    // Зависимость на Kotlin Gradle plugin, чтобы у нас появилась возможность использовать его классы
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.detekt.gradle.plugin)

    gradle.serviceOf<DependenciesAccessors>().classes.asFiles.forEach {
        compileOnly(files(it.absolutePath))
    }
}

gradlePlugin {
    plugins {
        register("ru.dmitriyt.versionmaker") {
            id = "ru.dmitriyt.versionmaker"
            implementationClass = "ru.dmitriyt.android.plugins.VersionMakerPlugin"
        }
        register("ru.dmitriyt.detekt") {
            id = "ru.dmitriyt.detekt"
            implementationClass = "ru.dmitriyt.android.plugins.DetektConventionPlugin"
        }
    }
}