import com.clientserver.buildProcess.CollectUnitTest.collectUnitTest

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("de.mannodermaus.android-junit5")
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

collectUnitTest()


dependencies {
    implementation(project(":core-domain"))
    implementation(project(":core-network"))
    implementation(Dependencies.dagger)
    kapt(Dependencies.roomCompiler)
    kapt(Dependencies.daggerCompiler)
    implementation(Dependencies.gson)
    api(Dependencies.retrofitConverter)
    api(Dependencies.retrofit)
    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.coroutinesAndroid)
    implementation(Dependencies.roomRuntime)
    testImplementation(Dependencies.kotlinTest)
    testImplementation(Dependencies.mockitoKotlin)

}
