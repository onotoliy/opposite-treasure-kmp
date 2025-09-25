plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
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

android {
    namespace = "com.github.onotoliy.opposite"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.github.onotoliy.opposite"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    lint {
        disable.add("NullSafeMutableLiveData")
    }
}

dependencies {
    implementation(project(":app"))
    implementation(compose.ui)
    implementation(compose.foundation)
    implementation(compose.material3)
    implementation(compose.components.resources)

    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.appcompat)
    implementation(libs.activity.compose)
    implementation(libs.material)
    implementation(libs.opposite.treasure.service.api.jvm)
    implementation(libs.ktor.client.okhttp)
}