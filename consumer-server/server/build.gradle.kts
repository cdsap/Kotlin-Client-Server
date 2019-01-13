import com.clientserver.buildProcess.CollectUnitTest.collectUnitTest

plugins {
    id("war")
    id("com.google.cloud.tools.appengine-standard")
    id("kotlin")
    id("kotlin-kapt")
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform { }
}

collectUnitTest()


dependencies {
    api(project(":consumer-server:domain-server"))
    api(project(":consumer-server:repository-server"))
    implementation(project(":core-domain"))
    implementation(project(":core-network"))
    implementation(Dependencies.objectify)
    implementation(Dependencies.endpoints)
    implementation(Dependencies.javaxServlet)
    implementation(Dependencies.kotlin)
    implementation(Dependencies.javaxInject)
    implementation(Dependencies.daggerCompiler)
    implementation(Dependencies.retrofitConverter)
    implementation(Dependencies.retrofit)
    kapt(Dependencies.dagger)
    implementation(Dependencies.gson)
    testImplementation(Dependencies.mockito)
}

appengine {
    deploy {
        version = "2"
        projectId = "APPENGINE_CONFIG"
    }
}
