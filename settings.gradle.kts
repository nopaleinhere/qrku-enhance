rootProject.name = "QRKU"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
	includeBuild("buildLogic")
	repositories {
		google {
			mavenContent {
				includeGroupAndSubgroups("androidx")
				includeGroupAndSubgroups("com.android")
				includeGroupAndSubgroups("com.google")
			}
		}
		mavenCentral()
		gradlePluginPortal()
	}
}
plugins {
	id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
	repositories {
		google {
			mavenContent {
				includeGroupAndSubgroups("androidx")
				includeGroupAndSubgroups("com.android")
				includeGroupAndSubgroups("com.google")
			}
		}
		mavenCentral()
	}
}

include(":composeApp")
include(":androidApp")
include(":core:common")
include(":core:ui")
include(":core:model")
include(":core:datastore")
include(":feature:write")
include(":feature:scan")
include(":feature:overview")
include(":core:database")
include(":feature:history")
include(":feature:settings")
