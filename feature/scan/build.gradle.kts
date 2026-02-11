plugins {
	alias(libs.plugins.kotlinMultiplatformPlugin)
	alias(libs.plugins.composeMultiplatformPlugin)
}

kotlin {
	androidLibrary {
		namespace = "com.sedate.qrku.feature.scan"
	}

	sourceSets {
		androidMain.dependencies {
			implementation(libs.mlkit.barcode.scanning)
			implementation(libs.camera.core)
			implementation(libs.camera.camera2)
			implementation(libs.camera.lifecycle)
			implementation(libs.camera.view)
		}
		commonMain.dependencies {
			implementation(projects.core.common)
			implementation(projects.core.model)
			implementation(projects.core.ui)

			implementation(libs.koin.core)
		}
	}
}