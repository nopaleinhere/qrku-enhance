plugins {
	alias(libs.plugins.kotlinMultiplatformPlugin)
	alias(libs.plugins.composeMultiplatformPlugin)
}

kotlin {
	androidLibrary {
		namespace = "com.sedate.qrku.feature.write"
	}

	sourceSets {
		commonMain.dependencies {
			implementation(projects.core.common)
			implementation(projects.core.model)
			implementation(projects.core.ui)

			implementation(libs.koin.core)
		}
	}
}