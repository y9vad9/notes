object Deps {
    object Plugins {
        object Jetbrains {
            object Compose {
                const val Id = "org.jetbrains.compose"
            }

            object Kotlin {
                object Multiplatform {
                    const val Id = "org.jetbrains.kotlin.multiplatform"
                }

                object Android {
                    const val Id = "org.jetbrains.kotlin.android"
                }

                object Parcelize {
                    const val Id = "kotlin-parcelize"
                }
            }
        }

        object Android {
            object Library {
                const val Id = "com.android.library"
            }

            object Application {
                const val Id = "com.android.application"
            }
        }

        object SQDelight {
            const val Id = "com.squareup.sqldelight"
        }
    }

    object Libs {
        object Androidx {
            private const val Version = "1.3.1"
            const val Activity = "androidx.activity:activity-compose:$Version"
            const val AppCompat = "androidx.appcompat:appcompat:$Version"
            const val CoreKtx = "androidx.core:core-ktx:1.6.0"
            const val Sqlite = "androidx.sqlite:sqlite:2.2.0-alpha02"

            object Compose {
                object Accompanist {
                    const val SystemUiController = "com.google.accompanist:accompanist-systemuicontroller:0.17.0"
                }
            }
        }

        const val JUnit = "junit:junit:4.13.2"

        object SQLDelight {
            private const val Version = "1.5.1"
            const val Runtime = "com.squareup.sqldelight:runtime:$Version"
            const val AndroidDriver = "com.squareup.sqldelight:android-driver:$Version"
            const val SqliteDriver = "com.squareup.sqldelight:sqlite-driver:$Version"
        }

        object Decompose {
            private const val Version = "0.3.1"
            const val Decompose = "com.arkivanov.decompose:decompose:$Version"
            const val JetbrainsComposeExtensions = "com.arkivanov.decompose:extensions-compose-jetbrains:$Version"
        }
    }
}