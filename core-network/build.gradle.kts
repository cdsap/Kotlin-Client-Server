plugins {
    id("kotlin")
}

dependencies {
    implementation(project(":core-domain"))
    implementation(Dependencies.kotlin)
    api(Dependencies.retrofitConverter)
    api(Dependencies.retrofit)
    api(Dependencies.gson)
}
