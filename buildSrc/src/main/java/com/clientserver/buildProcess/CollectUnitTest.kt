package com.clientserver.buildProcess

import org.gradle.api.Project
import org.gradle.api.tasks.testing.TestReport
import org.gradle.kotlin.dsl.closureOf
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.get


object CollectUnitTest {
    fun Project.collectUnitTest() = this.run {
        val testTask = if (rootProject.tasks.findByPath("collectUnitTest") == null) {
            rootProject.tasks.create("collectUnitTest", TestReport::class) {
            }
        } else {
            rootProject.tasks["collectUnitTest"]
        }
        testTask.configure(closureOf<TestReport> {
            group = "Verification"
            description = "Collect all tests"

            val tasks = project.tasks

            val testTask = tasks.find { it.name == "testDebugUnitTest" }
                    ?: tasks.find { it.name == "test" }

            testTask?.let { reportOn(testTask) }

            destinationDir = file("${rootProject.buildDir}/reports/tests")
        })
    }
}