import ru.dmitriyt.android.plugins.util.VersionMaker
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.kotlin.compose.compiler)
    alias(libs.plugins.androidx.navigation.safeargs)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics.gradle)
    id("ru.dmitriyt.detekt")
    id("ru.dmitriyt.versionmaker")
}

android {
    namespace = "ru.eruditeonline.app"
    compileSdk = 36

    defaultConfig {
        applicationId = "ru.eruditeonline.app"
        multiDexEnabled = true
        minSdk = 23
        targetSdk = 36
        versionCode = VersionMaker.getVersionCode(rootProject)
        versionName = "1.0.0"

        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    // для подписки релизного билда
    val releaseSignFile = rootProject.file("release_keystore.properties")
    val releaseSignProperties = Properties()
    if (releaseSignFile.exists()) {
        releaseSignFile.inputStream().use { releaseSignProperties.load(it) }
    }

    signingConfigs {
        create("release") {
            storeFile = file("keystore/${releaseSignProperties.getProperty("keystore", "")}")
            storePassword = releaseSignProperties.getProperty("storePassword", "")
            keyAlias = releaseSignProperties.getProperty("keyAlias", "")
            keyPassword = releaseSignProperties.getProperty("keyPassword", "")
        }
        getByName("debug") {
            storeFile = file("keystore/debug.keystore")
            keyAlias = "AndroidDebugKey"
            storePassword = "android"
            keyPassword = "android"
        }
    }

    buildTypes {
        // release build for google play, master/release branches
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
        }

        // debug build for developers only
        getByName("debug") {
            applicationIdSuffix = ".develop"
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = false
            isDebuggable = true
        }

        // internal build for testers
        create("internal") {
            initWith(getByName("release"))
            applicationIdSuffix = ".develop"
            signingConfig = signingConfigs.getByName("debug")
            isDebuggable = false
        }
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
        compose = true
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    kotlin {
        jvmToolchain(17)
    }

    applicationVariants.all {
        val variant = this
        variant.outputs.all {
            val appName = "eruditeonline"
            val buildType = variant.buildType.name
            val newName = "${appName}-${defaultConfig.versionName}_${defaultConfig.versionCode}_${buildType}"
            val output = this as com.android.build.gradle.internal.api.BaseVariantOutputImpl
            output.outputFileName = newName.lowercase() + ".apk"
        }
    }

    lint {
        lintConfig = file("${project.rootDir}/config/quality/lint/lint.xml")
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    coreLibraryDesugaring(libs.code.desugaring)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.viewpager2)
    implementation(libs.flexbox)
    implementation(libs.androidx.paging)
    implementation(libs.androidx.swiperefreshlayout)
    implementation(libs.androidx.splashscreen)

    implementation(libs.glide.core)
    kapt(libs.glide.compiler)

    implementation(libs.viewbinding.delegate)

    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)
    implementation(libs.androidx.livedata)
    implementation(libs.androidx.lifecycle.process)

    implementation(libs.bundles.retrofit)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    implementation(libs.bundles.dagger.core)
    kapt(libs.bundles.dagger.kapt)

    implementation(libs.timber)
    implementation(libs.chucker)

    implementation(libs.androidx.browser)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)

    // Compose BOM
    implementation(platform(libs.compose.bom))
    
    // Compose Core
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.foundation.layout)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    
    // Activity Compose
    implementation(libs.androidx.activity.compose)
    
    // Navigation Compose
    implementation(libs.androidx.navigation.compose)
    
    // ViewModel Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    
    // Runtime LiveData for Compose
    implementation(libs.androidx.runtime.livedata)
    
    // Paging Compose
    implementation(libs.androidx.paging.compose)

    implementation(libs.haze)
    implementation(libs.haze.materials)
    
    // Debug Tooling
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
