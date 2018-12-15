plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(28)
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
    }
}

dependencies {
    api(project(":core-domain"))
    implementation(Dependencies.dagger)
    kapt(Dependencies.roomCompiler)
    kapt(Dependencies.daggerCompiler)
    implementation(Dependencies.gson)
    api(Dependencies.retrofitConverter)
    api(Dependencies.retrofit)
    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.coroutinesAndroid)
    implementation(Dependencies.roomRuntime)
}
