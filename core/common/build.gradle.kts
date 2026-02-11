import com.codingfeline.buildkonfig.compiler.FieldSpec

plugins {
	alias(libs.plugins.kotlinMultiplatformPlugin)
	alias(libs.plugins.buildkonfig)
}

kotlin {
	androidLibrary {
		namespace = "com.sedate.qrku.core.common"
	}

	sourceSets {
		androidMain.dependencies {
			implementation(libs.threetenabp)
		}
		commonMain.dependencies {
			implementation(libs.timber)
			implementation(libs.kotlinx.coroutines.core)
			implementation(libs.koin.core)
			api(libs.androidx.lifecycle.viewmodel.navigation3)
		}
	}
}

buildkonfig {
	packageName = "com.sedate.qrku.core.common.utils"
	objectName = "BuildKonfig"
	exposeObjectWithName = "BuildKonfig"

	defaultConfigs {
		val libs = project.extensions.getByType<VersionCatalogsExtension>()
			.named("libs")
		val appVersion = libs.findVersion("android-appVersion")
			.get().requiredVersion

		buildConfigField(
			FieldSpec.Type.STRING,
			"APP_VERSION",
			appVersion
		)
	}
}