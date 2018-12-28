package com.clientserver.buildProcess

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.testing.TestReport
import org.gradle.kotlin.dsl.closureOf
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.withType


object JUnitOptions {
    fun Project.applyJUnitOptions() = this.run {
        tasks.withType<Test> {
            val rootProject = project.rootProject

            if (rootProject.hasProperty("maxParallelForks")) {
                val param = rootProject.properties["maxParallelForks"] as String
                maxParallelForks = java.lang.Integer.valueOf(param)
            } else {
                maxParallelForks = 1
            }
            if (rootProject.hasProperty("forkEvery")) {
                setForkEvery(rootProject.properties["forkEvery"] as Long)
            } else {
                setForkEvery(2000)
            }

            jvmArgs("-noverify")
        }

        val testTask = if (rootProject.tasks.findByPath("testReport") == null) {
            rootProject.tasks.create("testReport", TestReport::class) {

            }
        } else {
            rootProject.tasks["testReport"]
        }
        addTestTaskToRoot(testTask, project)
    }

    private fun Project.addTestTaskToRoot(rootTestTask: Task, project: Project): Task? {
        return rootTestTask.configure(closureOf<TestReport> {
            group = "Verification"
            description = "Execute all tests"

            val tasks = project.tasks

            val testTask = tasks.find { it.name == "testGooglePlayStoreAgodaRawUnitTest" }
                    ?: tasks.find { it.name == "testGoogleRawUnitTest" }
                    ?: tasks.find { it.name == "testDebugUnitTest" }
                    ?: tasks.find { it.name == "test" }

            testTask?.let { reportOn(testTask) }
                    ?: logger.error("Couldn't find test task for ${project.displayName}")

            if (testTask == null) {
                tasks.forEach { logger.error(it.name) }
            } else {
                logger.info("Found ${testTask.name} for ${project.displayName}")
            }

            destinationDir = file("${rootProject.buildDir}/reports/tests")
        })
    }
}