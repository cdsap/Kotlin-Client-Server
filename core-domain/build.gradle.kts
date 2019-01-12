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
    testImplementation(Dependencies.kotlinTest)
    testImplementation(Dependencies.mockitoKotlin)
}
