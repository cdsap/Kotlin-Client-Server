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
        classpath(Dependencies.androidJunit5)
        classpath(Dependencies.jetifierProcessor)
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