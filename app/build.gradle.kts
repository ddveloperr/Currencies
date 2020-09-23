import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(29)
    buildToolsVersion("30.0.2")

    defaultConfig {
        applicationId = "com.example.currencies"
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    androidExtensions {
        isExperimental = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))
    implementation(Libraries.coreKtx)
    implementation(Libraries.appcompat)
    implementation(Libraries.material)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.fragmentNavigation)
    testImplementation(Libraries.junit)
    androidTestImplementation(Libraries.junitTestExt)
    androidTestImplementation(Libraries.espressoCore)

    //dagger
    implementation(Libraries.dagger)
    implementation(Libraries.daggerAndroidSupport)
    annotationProcessor(Libraries.daggerAndroidProcessor)
    kapt(Libraries.daggerCompiler)
    kapt(Libraries.daggerAndroidProcessor)

    //rxjava
    implementation(Libraries.rxJava)
    implementation(Libraries.rxAndroid)

    //retrofit
    implementation(Libraries.gson)
    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitGsonConverter)
    implementation(Libraries.retrofitRxJavaAdapter)
    implementation(Libraries.okhttp)

    //mosby
    implementation(Libraries.mosby)


    implementation(project(":common"))
    implementation(project(":error_manager"))
    implementation(project(":mvi"))

}