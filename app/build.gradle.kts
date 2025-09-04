@file:Suppress("OPT_IN_IS_NOT_ENABLED")
@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl


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


    wasmJs {
//        moduleName = "app"
        binaries.executable()
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "app.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                }
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
            implementation(compose.material)
            implementation(compose.components.resources)
            implementation("org.jetbrains.skiko:skiko:0.9.24")
        }

        wasmJsMain.dependencies {
            implementation (compose.ui)
            implementation (compose.foundation)
            implementation("org.jetbrains.skiko:skiko-wasm-js:0.9.24")
        }

        androidMain.dependencies {

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
