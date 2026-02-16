plugins {
    alias(libs.plugins.kotlinMultiplatformPlugin)
}

kotlin {
    androidLibrary {
        namespace = "com.sedate.qrku.core.datastore"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(projects.core.model)

            implementation(libs.androidx.datastore)
            implementation(libs.androidx.datastore.preferences)
            implementation(libs.koin.core)
            implementation(libs.kotlinx.coroutines.core)
        }
    }
}