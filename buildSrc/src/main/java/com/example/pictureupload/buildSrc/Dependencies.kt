package com.example.pictureupload.buildSrc

object Libs {
    object Hilt {
        private const val hiltVersion = "2.36"
        private const val hiltCompilerVersion = "1.0.0"
        private const val hiltWorkVersion = "1.0.0"
        const val hiltAndroidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"
        const val android = "com.google.dagger:hilt-android:$hiltVersion"
        const val androidCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
        const val compiler = "androidx.hilt:hilt-compiler:$hiltCompilerVersion"
        const val work = "androidx.hilt:hilt-work:$hiltWorkVersion"
        const val testing = "com.google.dagger:hilt-android-testing:$hiltVersion"
    }

    object Room {
        private const val roomVersion = "2.4.0-alpha03"
        const val roomKtx = "androidx.room:room-ktx:$roomVersion"
        const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
        const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
        const val roomTest = "androidx.room:room-testing:$roomVersion"
    }

    object Navigation {
        private const val navComposeVer = "2.4.0-alpha06"
        const val navigation = "androidx.navigation:navigation-compose:$navComposeVer"

        private const val navVer = "2.3.5"
        const val navGradlePlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$navVer"
        const val fragment = "androidx.navigation:navigation-fragment-ktx:$navVer"
        const val ui = "androidx.navigation:navigation-ui-ktx:$navVer"
    }

    object Gradle {
        private const val agpVersion = "7.0.2"
        const val androidGradlePlugin = "com.android.tools.build:gradle:$agpVersion"
    }

    object Kotlin {
        private const val version = "1.5.21"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    }

    object GoogleServices {
        private const val servicesVer = "4.3.10"
        const val googleServices = "com.google.gms:google-services:$servicesVer"
    }

    object Mockk {
        private const val mockkVer = "1.11.0"
        private const val mockkAndroidVer = "1.10.6"
        const val mockk = "io.mockk:mockk:$mockkVer"
        const val mockkAndroid = "io.mockk:mockk-android:$mockkAndroidVer"
    }

    object Coroutines {
        private const val coroutinesVer = "1.5.2"
        private const val coroutinesTestingVer = "1.5.1"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVer"
        const val coroutinesTestingCore = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesTestingVer"
    }

    object AndroidX {
        private const val coreKtxVer = "1.6.0"
        private const val appCompatVer = "1.3.1"
        private const val activityVer = "1.3.1"
        private const val coreTestingVer = "2.1.0"
        const val coreKtx = "androidx.core:core-ktx:$coreKtxVer"
        const val appcompat = "androidx.appcompat:appcompat:$appCompatVer"
        const val activity = "androidx.activity:activity-ktx:$activityVer"
        const val coreTesting = "androidx.arch.core:core-testing:$coreTestingVer"
    }

    object WorkManager {
        private const val workManagerVer = "2.6.0-beta02"
        const val workManager = "androidx.work:work-runtime-ktx:$workManagerVer"
    }

    object Firebase {
        private const val platformVer = "28.4.0"
        const val firebasePlatform = "com.google.firebase:firebase-bom:$platformVer"
        const val firebaseAuth = "com.google.firebase:firebase-auth"
        const val firebaseStorage = "com.google.firebase:firebase-storage"
        const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
    }

    object KtLint {
        private const val ktLintVer = "10.1.0"
        const val ktLint = "org.jlleitschuh.gradle:ktlint-gradle:$ktLintVer"
    }

    object Material {
        private const val materialVersion = "1.4.0"
        const val material = "com.google.android.material:material:$materialVersion"
    }

    object LifeCycle {
        private const val lifeCycleVer = "2.3.1"
        private const val activityVer = "1.3.1"
        const val lifeCycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifeCycleVer"
        const val lifeCycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifeCycleVer"
    }

    object JUnit {
        private const val extJunitVer = "1.1.3"
        private const val junitVer = "4.13.2"
        const val extJUnit = "androidx.test.ext:junit:$extJunitVer"
        const val jUnit = "junit:junit:$junitVer"
    }

    object Espresso {
        private const val espressoVer = "3.4.0"
        const val espressoCore = "androidx.test.espresso:espresso-core:$espressoVer"
    }

    object Compose {
        const val composeVer = "1.0.2"
        private const val activityComposeVer = "1.3.1"
        const val ui = "androidx.compose.ui:ui:$composeVer"
        const val material = "androidx.compose.material:material:$composeVer"
        const val iconsExtended = "androidx.compose.material:material-icons-extended:$composeVer"
        const val tooling = "androidx.compose.ui:ui-tooling:$composeVer"
        const val toolingPrev = "androidx.compose.ui:ui-tooling-preview:$composeVer"
        const val composeUiJunit = "androidx.compose.ui:ui-test-junit4:$composeVer"
        const val activityCompose = "androidx.activity:activity-compose:$activityComposeVer"
    }
}