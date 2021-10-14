package com.example.pictureupload.buildSrc

object Versions {
    const val accompanistVer = "0.19.0"
    const val coilVer = "1.4.0"
    const val agpVersion = "7.0.2"
    const val hiltVersion = "2.36"
    const val hiltCompilerVersion = "1.0.0"
    const val hiltWorkVersion = "1.0.0"
    const val roomVersion = "2.4.0-alpha03"
    const val navComposeVer = "2.4.0-alpha06"
    const val navigationVer = "2.3.5"
    const val kotlinVersion = "1.5.21"
    const val servicesVer = "4.3.10"
    const val composeVer = "1.0.2"
    const val activityComposeVer = "1.3.1"
    const val mockkVer = "1.11.0"
    const val mockkAndroidVer = "1.10.6"
    const val coroutinesVer = "1.5.2"
    const val coroutinesTestingVer = "1.5.1"
    const val coreKtxVer = "1.6.0"
    const val appCompatVer = "1.3.1"
    const val coreTestingVer = "2.1.0"
    const val workManagerVer = "2.6.0-beta02"
    const val platformVer = "28.4.0"
    const val espressoVer = "3.4.0"
    const val extJunitVer = "1.1.3"
    const val junitVer = "4.13.2"
    const val lifeCycleVer = "2.3.1"
    const val activityVer = "1.3.1"
    const val materialVersion = "1.4.0"
    const val ktLintVer = "10.1.0"
}

object Libs {
    object Hilt {
        const val hiltAndroidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}"
        const val android = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
        const val androidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"
        const val compiler = "androidx.hilt:hilt-compiler:${Versions.hiltCompilerVersion}"
        const val work = "androidx.hilt:hilt-work:${Versions.hiltWorkVersion}"
        const val testing = "com.google.dagger:hilt-android-testing:${Versions.hiltVersion}"
    }

    object Room {
        const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
        const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
        const val roomTest = "androidx.room:room-testing:${Versions.roomVersion}"
    }

    object Navigation {
        const val navigation = "androidx.navigation:navigation-compose:${Versions.navComposeVer}"

        const val navGradlePlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationVer}"
        const val fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVer}"
        const val ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigationVer}"
    }

    object Gradle {
        const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.agpVersion}"
    }

    object Kotlin {
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    }

    object GoogleServices {
        const val googleServices = "com.google.gms:google-services:${Versions.servicesVer}"
    }

    object Mockk {
        const val mockk = "io.mockk:mockk:${Versions.mockkVer}"
        const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockkAndroidVer}"
    }

    object Coroutines {
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVer}"
        const val coroutinesTestingCore = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTestingVer}"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVer}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompatVer}"
        const val activity = "androidx.activity:activity-ktx:${Versions.activityVer}"
        const val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTestingVer}"
    }

    object WorkManager {
        const val workManager = "androidx.work:work-runtime-ktx:${Versions.workManagerVer}"
    }

    object Firebase {
        const val firebasePlatform = "com.google.firebase:firebase-bom:${Versions.platformVer}"
        const val auth = "com.google.firebase:firebase-auth"
        const val storage = "com.google.firebase:firebase-storage"
        const val analytics = "com.google.firebase:firebase-analytics-ktx"
    }

    object KtLint {
        const val ktLint = "org.jlleitschuh.gradle:ktlint-gradle:${Versions.ktLintVer}"
    }

    object Material {
        const val material = "com.google.android.material:material:${Versions.materialVersion}"
    }

    object LifeCycle {
        const val lifeCycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycleVer}"
        const val lifeCycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifeCycleVer}"
    }

    object JUnit {
        const val extJUnit = "androidx.test.ext:junit:${Versions.extJunitVer}"
        const val jUnit = "junit:junit:${Versions.junitVer}"
    }

    object Espresso {
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoVer}"
    }

    object Accompanist {
        const val inset = "com.google.accompanist:accompanist-insets:${Versions.accompanistVer}"
        const val uiController = "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanistVer}"
    }

    object Coil {
        const val coil = "io.coil-kt:coil-compose:${Versions.coilVer}"
    }

    object Compose {
        const val ui = "androidx.compose.ui:ui:${Versions.composeVer}"
        const val material = "androidx.compose.material:material:${Versions.composeVer}"
        const val iconsExtended = "androidx.compose.material:material-icons-extended:${Versions.composeVer}"
        const val tooling = "androidx.compose.ui:ui-tooling:${Versions.composeVer}"
        const val toolingPrev = "androidx.compose.ui:ui-tooling-preview:${Versions.composeVer}"
        const val composeUiJunit = "androidx.compose.ui:ui-test-junit4:${Versions.composeVer}"
        const val activityCompose = "androidx.activity:activity-compose:${Versions.activityComposeVer}"
    }
}