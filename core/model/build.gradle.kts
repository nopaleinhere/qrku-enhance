plugins {
	alias(libs.plugins.kotlinMultiplatformPlugin)
}

kotlin {
	androidLibrary {
		namespace = "com.sedate.qrku.core.model"
	}

	sourceSets {
		commonMain.dependencies {
			implementation(projects.core.common)
		}
	}
}