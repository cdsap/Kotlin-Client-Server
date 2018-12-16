plugins {
    id("kotlin")
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform { }
}

dependencies {
    implementation(Dependencies.kotlin)
    api(Dependencies.retrofitConverter)
    api(Dependencies.retrofit)
    api(Dependencies.gson)
    testImplementation(Dependencies.kotlinTest)
    testImplementation(Dependencies.mockitoKotlin)
}