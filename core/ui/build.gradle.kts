plugins {
	alias(libs.plugins.kotlinMultiplatformPlugin)
	alias(libs.plugins.composeMultiplatformPlugin)
	alias(libs.plugins.kotlinxSerialization)
}

kotlin {
	androidLibrary {
		namespace = "com.sedate.qrku.core.ui"
		experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true
	}

	sourceSets {
		androidMain.dependencies {
			implementation(libs.compose.ui.tooling)
			implementation(libs.zxing.core)
			api(libs.compose.ui.tooling.preview)
		}
		commonMain.dependencies {
			implementation(projects.core.common)
			api(libs.compose.material3)
			api(libs.compose.material.icons.extended)
			api(libs.compose.resources)
			api(libs.androidx.navigation.ui)
			api(libs.androidx.lifecycle.viewmodel.navigation3)
			api(libs.koin.compose.navigation)
		}
	}
}

compose.resources {
	publicResClass = true
	packageOfResClass = "com.sedate.qrku.resources"
	generateResClass = always
}