import org.gradle.initialization.DependenciesAccessors
import org.gradle.kotlin.dsl.support.serviceOf

plugins {
    `kotlin-dsl`
}

group = "com.sedate.qrku.buildLogic.convention"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)

    gradle.serviceOf<DependenciesAccessors>().classes.asFiles.forEach {
        compileOnly(files(it.absolutePath))
    }
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}
gradlePlugin {
    plugins {
        register("composeMultiplatformPlugin") {
            id = "com.sedate.composeMultiplatformPlugin"
            implementationClass = "ComposeMultiplatformPlugin"
        }

        register("kotlinMultiplatformPlugin") {
            id = "com.sedate.kotlinMultiplatformPlugin"
            implementationClass = "KotlinMultiplatformPlugin"
        }
    }
}
