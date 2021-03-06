plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {

    compileSdkVersion(28)
    defaultConfig {
        applicationId = "com.kotlin.client"
        minSdkVersion(21)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
        javaCompileOptions {
            annotationProcessorOptions {
                includeCompileClasspath = true
            }
        }
        multiDexEnabled = true
        buildConfigField("String", "URL", "\"YOUR_GAE_ENDPOINT_PROJECT\"")
    }
    buildTypes {
    }

    flavorDimensions("environment")

    productFlavors {
        create("local") {
            buildConfigField("String", "URL", "\"http://10.0.2.2:8080/\"")
            dimension = "environment"
        }

        create("gae") {
            dimension = "environment"
        }
    }
}

dependencies {
    implementation(project(":consumer-android:repository-android"))
    implementation(project(":core-domain"))
    implementation(Dependencies.supportAppcompat)
    implementation(Dependencies.supportRecycler)
    implementation(Dependencies.supportCardView)
    implementation(Dependencies.workManager)
    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.coroutinesAndroid)
    implementation(Dependencies.javaxInject)
    kapt(Dependencies.daggerProcessor)
    kapt(Dependencies.daggerCompiler)
    kapt(Dependencies.jetifierCore)
    implementation(Dependencies.daggerAndroid)
    implementation(Dependencies.kotlin)
    androidTestImplementation(Dependencies.mockitoKotlin)
}
