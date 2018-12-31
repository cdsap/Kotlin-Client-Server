import com.clientserver.buildProcess.CollectUnitTest.collectUnitTest

plugins {
    id("kotlin")
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform { }
}

collectUnitTest()

dependencies {
    implementation(Dependencies.kotlin)
    api(Dependencies.retrofitConverter)
    api(Dependencies.retrofit)
    api(Dependencies.gson)
    testImplementation(Dependencies.kotlinTest)
    testImplementation(Dependencies.mockitoKotlin)
}
