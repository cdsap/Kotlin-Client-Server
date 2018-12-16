plugins {
    id("kotlin")
    id("kotlin-kapt")
}

dependencies {
    api(project(":consumer-server:domain-server"))
    api(project(":core-domain"))
    implementation(Dependencies.objectify)
    implementation(Dependencies.kotlin)
    implementation(Dependencies.retrofitConverter)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.gson)
    implementation(Dependencies.javaxInject)
    implementation(Dependencies.daggerCompiler)
    implementation(Dependencies.appengineApi)
    kapt(Dependencies.daggerProcessor)
    kapt(Dependencies.daggerCompiler)

}

