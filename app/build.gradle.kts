@file:Suppress("OPT_IN_IS_NOT_ENABLED")

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("org.jetbrains.kotlin.plugin.serialization") version "2.2.0"
}

version = "1.0-SNAPSHOT"

kotlin {
    androidTarget {
        publishLibraryVariants("release", "debug")
    }

    jvm("desktop")

    js(IR) {
        binaries.executable()
        browser()
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
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.components.resources)
            implementation("io.insert-koin:koin-core:4.1.1")
            implementation("io.insert-koin:koin-compose-viewmodel:4.1.1")
//            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.7.1")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
            implementation("org.jetbrains.compose.material:material-icons-extended:1.7.3")
            implementation("org.jetbrains.androidx.navigation:navigation-compose:2.9.0-rc01")
            implementation("io.github.windedge.table:table-m2:0.2.2")
        }

        jsMain.dependencies {
        }

        androidMain.dependencies {
            implementation(compose.uiTooling)
            implementation(compose.preview)
        }
    }
}

android {
    namespace = "com.github.onotoliy.opposite"
    compileSdk = 36
    defaultConfig {
        minSdk = 26
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
