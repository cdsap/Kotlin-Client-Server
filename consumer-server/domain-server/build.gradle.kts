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
    api(project(":core-domain"))
    implementation(Dependencies.javaxInject)
    implementation(Dependencies.kotlin)
    kapt(Dependencies.daggerProcessor)
    kapt(Dependencies.daggerCompiler)
    implementation(Dependencies.daggerCompiler)
    implementation(Dependencies.kotlin)
    testImplementation(Dependencies.kotlinTest)
    testImplementation(Dependencies.mockitoKotlin)
}
