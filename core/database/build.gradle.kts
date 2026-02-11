plugins {
	alias(libs.plugins.kotlinMultiplatformPlugin)
	alias(libs.plugins.ksp)
}

kotlin {
	androidLibrary {
		namespace = "com.sedate.qrku.core.database"
	}

	sourceSets {
		commonMain.dependencies {
			implementation(projects.core.common)
			implementation(projects.core.model)

			implementation(libs.androidx.room.runtime)
		}

		androidMain.dependencies {
			implementation(libs.androidx.room.ktx)
			implementation(libs.androidx.sqlite)
			implementation(libs.koin.core)
		}
	}
}

dependencies {
	ksp(libs.androidx.room.compiler)
}