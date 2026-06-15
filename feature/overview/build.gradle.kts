plugins {
    alias(libs.plugins.kotlinMultiplatformPlugin)
    alias(libs.plugins.composeMultiplatformPlugin)
}

kotlin {
    androidLibrary {
        namespace = "com.sedate.qrku.feature.overview"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(projects.core.model)
            implementation(projects.core.ui)
            implementation(projects.core.database)
            implementation(projects.core.datastore)

            implementation(libs.koin.core)
        }
    }
}

