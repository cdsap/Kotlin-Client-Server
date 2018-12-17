plugins {
    id("kotlin")
    id("kotlin-kapt")
}

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
