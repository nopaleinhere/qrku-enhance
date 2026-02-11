package com.sedate.qrku

import org.gradle.api.Project

fun Project.getSdkVersions(): AndroidSdkVersions {
    return AndroidSdkVersions(
        compileSdk = libs.versions.android.compileSdk.get().toInt(),
        minSdk = libs.versions.android.minSdk.get().toInt(),
        targetSdk = libs.versions.android.targetSdk.get().toInt()
    )
}

data class AndroidSdkVersions(
    val compileSdk: Int,
    val minSdk: Int,
    val targetSdk: Int
)