import org.jetbrains.compose.compose

plugins {
    id(Deps.Plugins.Jetbrains.Kotlin.Multiplatform.Id)
    id(Deps.Plugins.Jetbrains.Compose.Id)
    id(Deps.Plugins.Android.Library.Id)
    id(Deps.Plugins.SQDelight.Id)
    id(Deps.Plugins.Jetbrains.Kotlin.Parcelize.Id)
}

group = AppConfig.PACKAGE
version = AppConfig.VERSION

repositories {
    google()
}

kotlin {
    android()
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(Deps.Libs.SQLDelight.Runtime)
                api(Deps.Libs.Decompose.Decompose)
                api(Deps.Libs.Decompose.JetbrainsComposeExtensions)
            }
        }
        val commonTest by getting {
            dependencies {
                //implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api(Deps.Libs.Androidx.AppCompat)
                api(Deps.Libs.Androidx.CoreKtx)
                api(Deps.Libs.Androidx.Activity)
                api(Deps.Libs.SQLDelight.AndroidDriver)
                api(Deps.Libs.Androidx.Sqlite)
                api(Deps.Libs.Androidx.Compose.Accompanist.SystemUiController)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(Deps.Libs.JUnit)
            }
        }
        val desktopMain by getting {
            dependencies {
                api(Deps.Libs.SQLDelight.SqliteDriver)
            }
        }
        val desktopTest by getting
    }
}

android {
    compileSdkVersion(AppConfig.Android.TARGET_SDK)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(AppConfig.Android.MIN_SDK)
        targetSdkVersion(AppConfig.Android.TARGET_SDK)
    }
}

sqldelight {
    database("NoteDatabase") {
        packageName = "${AppConfig.PACKAGE}.database"
        sourceFolders = listOf("sqldelight")
        schemaOutputDirectory = file("build/dbs")
        dialect = "sqlite:3.25"
    }
}