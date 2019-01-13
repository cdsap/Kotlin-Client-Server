import com.clientserver.buildProcess.CollectUnitTest.collectUnitTest

plugins {
    id("kotlin")
    id("kotlin-kapt")
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform { }
}

collectUnitTest()

dependencies {
    implementation(project(":consumer-server:domain-server"))
    implementation(project(":core-domain"))
    api(project(":core-network"))
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
    testImplementation(Dependencies.kotlinTest)
    testImplementation(Dependencies.mockitoKotlin)
}
