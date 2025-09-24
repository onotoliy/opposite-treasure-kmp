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
            username = project.findProperty("onotoliy.github.username") as String? ?: ""
            password = project.findProperty("onotoliy.github.token") as String? ?: ""
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
                implementation("io.ktor:ktor-client-js:3.1.3")
                implementation("org.jetbrains.kotlin:kotlin-stdlib:2.2.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
                implementation("org.jetbrains.androidx.navigation:navigation-compose-js:2.9.0-rc01")
                implementation("io.insert-koin:koin-core-js:4.1.1")
                implementation("io.insert-koin:koin-compose-viewmodel-js:4.1.1")
            }
        }
    }
}


