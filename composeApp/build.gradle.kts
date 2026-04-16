import com.android.build.api.dsl.androidLibrary
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	alias(libs.plugins.kotlinMultiplatform)
	alias(libs.plugins.android.kotlin.multiplatform.library)
	alias(libs.plugins.composeMultiplatformPlugin)
}

kotlin {
	@Suppress("UnstableApiUsage")
	androidLibrary {
		namespace = "com.sedate.qrku.composeapp"
		compileSdk = libs.versions.android.compileSdk.get()
			.toInt()
		minSdk = libs.versions.android.minSdk.get()
			.toInt()

		compilerOptions {
			jvmTarget.set(JvmTarget.JVM_21)
		}
	}

	listOf(
		iosArm64(),
		iosSimulatorArm64()
	).forEach { iosTarget ->
		iosTarget.binaries.framework {
			baseName = "ComposeApp"
			isStatic = true
		}
	}

	sourceSets {
		commonMain.dependencies {
			implementation(projects.core.common)
			implementation(projects.core.model)
			implementation(projects.core.ui)
			implementation(projects.core.datastore)
			implementation(projects.core.database)
			implementation(projects.feature.write)
			implementation(projects.feature.scan)
			implementation(projects.feature.overview)
			implementation(projects.feature.history)
			implementation(projects.feature.settings)

			implementation(libs.koin.core)
			implementation(libs.koin.compose)
		}
		commonTest.dependencies {
			implementation(libs.kotlin.test)
		}
	}
}