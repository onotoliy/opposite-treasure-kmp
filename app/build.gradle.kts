@file:Suppress("OPT_IN_IS_NOT_ENABLED")

plugins {
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.detekt)
}

repositories {
    mavenCentral()
    google()
    maven {
        url = uri("https://maven.pkg.github.com/onotoliy/opposite-treasure-api")
        credentials {
            username = project.findProperty("onotoliy.github.username") as String?
                ?: System.getenv("onotoliy.github.username")
            password = project.findProperty("onotoliy.github.token") as String?
                ?: System.getenv("onotoliy.github.token")
        }
    }
}

version = "1.0-SNAPSHOT"

kotlin {
    androidTarget {
        publishLibraryVariants("release", "debug")
    }

    jvm("desktop")

    js(IR) {
        binaries.executable()
        browser {
            webpackTask {
                sourceMaps = true
            }
        }
    }

    sourceSets {
        all {
            languageSettings {
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
        }

        commonMain.dependencies {
            implementation(compose.ui)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.components.resources)
            implementation(libs.ktor.client.core)
            implementation(libs.koin.core)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.material.icons.extended)
            implementation(libs.navigation.compose)
            implementation(libs.table.m3)
            implementation(libs.opposite.treasure.service.api)
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.windows_x64)
                implementation(libs.skiko.awt.runtime.windows.x64)
                implementation(libs.ktor.client.okhttp)
            }
        }

        jsMain.dependencies {
            implementation(libs.ktor.client.js)
            implementation(libs.kotlin.stdlib)
            implementation(libs.kotlinx.coroutines.core)
        }

        androidMain.dependencies {
            implementation(compose.uiTooling)
            implementation(compose.preview)
            implementation(libs.ktor.client.okhttp)
        }
    }
}

detekt {
    toolVersion = "1.23.8"
    config = files("../detekt.yml")
    buildUponDefaultConfig = true
    allRules = false
    autoCorrect = false
    source = files("src/commonMain/kotlin", "src/androidMain/kotlin", "src/jsMain/kotlin")
}

compose.desktop {
    application {
        mainClass = "com.github.onotoliy.opposite.MainKt"

        nativeDistributions {
            targetFormats(
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Exe,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Msi
            )
            packageName = "OppositeTreasure"
            packageVersion = "1.0.0"

        }

        buildTypes.release {
            proguard {
                isEnabled.value(false)
            }
        }
    }
}

android {
    lint {
        disable.add("NullSafeMutableLiveData")
    }

    namespace = "com.github.onotoliy.opposite"
    compileSdk = 36
    defaultConfig {
        minSdk = 26
    }
    buildFeatures {
        buildConfig = false
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
