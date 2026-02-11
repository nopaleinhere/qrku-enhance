import com.android.build.api.dsl.androidLibrary
import com.sedate.qrku.alias
import com.sedate.qrku.getSdkVersions
import com.sedate.qrku.libs
import org.gradle.api.Plugin
import org.gradle.kotlin.dsl.configure
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@Suppress("unused")
class KotlinMultiplatformPlugin: Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            alias(libs.plugins.kotlinMultiplatform)
            alias(libs.plugins.android.kotlin.multiplatform.library)
        }

        extensions.configure<KotlinMultiplatformExtension> {
            val sdkVersion = getSdkVersions()

            @Suppress("UnstableApiUsage")
            androidLibrary {
                compileSdk = sdkVersion.compileSdk
                minSdk = sdkVersion.minSdk

                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_21)
                }
            }

            listOf(
                iosArm64(),
                iosSimulatorArm64()
            ).forEach { iosTarget ->
                iosTarget.binaries.framework {
                    baseName = path.substring(1).replace(':', '-')
                }
            }

            compilerOptions.freeCompilerArgs.add("-Xexpect-actual-classes")
        }
    }
}