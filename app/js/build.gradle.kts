plugins {
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
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

kotlin {
    js(IR) {
        browser {
            binaries.executable()
            webpackTask {
                sourceMaps = true
                mainOutputFileName = "app.js"
            }
            runTask {
                mainOutputFileName = "app.js"
            }
        }
    }

    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(project(":app")) // подключение common части
                implementation(libs.ktor.client.js)
                implementation(libs.kotlin.stdlib)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.navigation.compose.js)
                implementation(libs.koin.core.js)
                implementation(libs.koin.compose.viewmodel.js)
            }
        }
    }
}


