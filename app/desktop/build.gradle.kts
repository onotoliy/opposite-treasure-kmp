plugins {
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
    kotlin("jvm")
}

repositories {
    mavenCentral()
    google()
    maven {
        url = uri("https://maven.pkg.github.com/onotoliy/opposite-treasure-api")
        credentials {
            username = project.findProperty("onotoliy.github.username") as String?
                ?: System.getenv("ONOTOLIY_GITHUB_USERNAME")
            password = project.findProperty("onotoliy.github.token") as String?
                ?: System.getenv("ONOTOLIY_GITHUB_TOKEN")
        }
    }
}

version = "1.0-SNAPSHOT"

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
            includeAllModules = true
        }

        buildTypes.release {
            proguard {
                isEnabled.value(false)
            }
        }
    }
}

dependencies {
    implementation(project(":app"))
    implementation(compose.desktop.windows_x64)
    implementation(libs.skiko.awt.runtime.windows.x64)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.koin.core.jvm)
    implementation(libs.koin.compose.viewmodel.jvm)
    implementation(libs.navigation.compose.desktop)
}
