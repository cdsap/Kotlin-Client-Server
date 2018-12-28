buildscript {
    repositories {
        jcenter()
        google()
        mavenCentral(

        )
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath(Dependencies.appengineGradle)
        classpath(Dependencies.kotlinGradle)
        classpath(Dependencies.androidGradle)
        classpath("de.mannodermaus.gradle.plugins:android-junit5:1.3.1.1")
    }
}
allprojects {
    repositories {
        maven {
            url = uri("https://maven-central.storage.googleapis.com")
        }
        jcenter()
        google()
        mavenCentral()
    }

}