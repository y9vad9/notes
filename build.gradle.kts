buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev/")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
        classpath("com.android.tools.build:gradle:4.2.2")
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.1")
        classpath("org.jetbrains.compose:compose-gradle-plugin:1.0.0-alpha3")
    }
}

group = AppConfig.PACKAGE
version = AppConfig.VERSION

allprojects {
    repositories {
        mavenCentral()
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}