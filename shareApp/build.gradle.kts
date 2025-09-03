@file:Suppress("OPT_IN_IS_NOT_ENABLED")

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.androidLibrary)
}

version = "1.0-SNAPSHOT"

kotlin {
    androidTarget {
        publishLibraryVariants("release", "debug")
    }

    jvm("desktop")

    js(IR) {
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
            implementation(compose.components.resources)
            implementation("org.jetbrains.compose.material:material-icons-core:1.6.11")
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")
        }
        val androidMain by getting {
        }

        val desktopMain by getting {

        }

    }
}

android {
    namespace = "com.example.shareApp"
    compileSdk = 35   // 🔥 обязательно!
    defaultConfig {
        minSdk = 26
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
