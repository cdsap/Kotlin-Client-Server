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
        buildConfigField("String", "URL", "\"http://10.0.2.2:8080/\"")
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
    api(project(":consumer-android:repository-android"))
    implementation(Dependencies.supportAppcompat)
    implementation(Dependencies.supportRecycler)
    implementation(Dependencies.supportCardView)
    implementation(Dependencies.workManager)
    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.coroutinesAndroid)
    implementation(Dependencies.supportV4)
    implementation(Dependencies.javaxInject)
    kapt(Dependencies.daggerProcessor)
    kapt(Dependencies.daggerCompiler)
    implementation(Dependencies.daggerAndroid)
    implementation(Dependencies.kotlin)
    androidTestImplementation("androidx.test.espresso:espresso-core:3.1.1")
    androidTestImplementation("androidx.test:rules:1.1.1")
    androidTestImplementation("androidx.test:runner:1.1.1")
    androidTestImplementation("androidx.test:core:1.1.0")
    androidTestImplementation(Dependencies.mockitoKotlin)
    // compile group: 'androidx.test.espresso', name: 'espresso-core', version: '3.1.1'

}

