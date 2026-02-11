import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	alias(libs.plugins.androidApplication)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.composeMultiplatformPlugin)
}

android {
	namespace = "com.sedate.qrku"
	compileSdk {
		version = release(
			libs.versions.android.compileSdk.get()
				.toInt()
		)
	}

	defaultConfig {
		applicationId = "com.sedate.qrku"
		minSdk = libs.versions.android.minSdk.get()
			.toInt()
		targetSdk = libs.versions.android.targetSdk.get()
			.toInt()
		versionCode = libs.versions.android.buildVersion.get()
			.toInt()
		versionName = libs.versions.android.appVersion.get()
	}

	signingConfigs {
		create("release") {
			storeFile = file(
				rootProject.file(
					project.property("RELEASE_STORE_FILE") as String
				)
			)
			storePassword = project.property("RELEASE_STORE_PASSWORD") as String
			keyAlias = project.property("RELEASE_KEY_ALIAS") as String
			keyPassword = project.property("RELEASE_KEY_PASSWORD") as String
		}
	}


	buildTypes {
		getByName("release") {
			signingConfig = signingConfigs.getByName("release")
			isMinifyEnabled = true
			isShrinkResources = true

			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}

	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_21
		targetCompatibility = JavaVersion.VERSION_21
	}

	kotlin {
		compilerOptions {
			jvmTarget.set(JvmTarget.JVM_21)
		}
	}
}

dependencies {
	implementation(projects.composeApp)
	implementation(projects.core.ui)
	implementation(libs.koin.android)
	implementation(libs.threetenabp)
}