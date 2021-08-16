plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
}

group = AppConfig.PACKAGE
version = AppConfig.VERSION

repositories {
    google()
}

dependencies {
    implementation(project(":common"))
}

android {
    compileSdkVersion(AppConfig.Android.TARGET_SDK)
    defaultConfig {
        applicationId = "com.notes.android"
        minSdkVersion(AppConfig.Android.MIN_SDK)
        targetSdkVersion(AppConfig.Android.TARGET_SDK)
        versionCode = AppConfig.VERSION_CODE
        versionName = AppConfig.VERSION
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}