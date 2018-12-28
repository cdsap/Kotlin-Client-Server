import com.clientserver.buildProcess.JUnitOptions.applyJUnitOptions

plugins {
    id("kotlin")
    id("kotlin-kapt")
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform { }
}

applyJUnitOptions()

dependencies {
    api(project(":consumer-server:domain-server"))
    api(project(":core-domain"))
    implementation(Dependencies.objectify)
    implementation(Dependencies.kotlin)
    api(Dependencies.retrofitConverter)
    api(Dependencies.retrofit)
    implementation(Dependencies.gson)
    implementation(Dependencies.javaxInject)
    implementation(Dependencies.daggerCompiler)
    implementation(Dependencies.appengineApi)
    kapt(Dependencies.daggerProcessor)
    kapt(Dependencies.daggerCompiler)
    testImplementation(Dependencies.kotlinTest)
    testImplementation(Dependencies.mockitoKotlin)
}
