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
        }

        androidMain.dependencies {

        }

//        desktopMain.dependencies {
//
//        }
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
