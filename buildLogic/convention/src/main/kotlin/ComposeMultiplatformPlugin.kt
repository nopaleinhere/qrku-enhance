import com.sedate.qrku.alias
import com.sedate.qrku.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@Suppress("unused")
class ComposeMultiplatformPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        with(pluginManager) {
            alias(libs.plugins.composeMultiplatform)
            alias(libs.plugins.composeCompiler)
        }
        
        pluginManager.withPlugin("com.android.library") {
            dependencies.add(
                "implementation",
                libs.compose.ui.tooling.preview
            )
            dependencies.add(
                "debugImplementation",
                libs.compose.ui.tooling
            )
        }

        pluginManager.withPlugin("com.android.application") {
            dependencies.add(
                "implementation",
                libs.androidx.compose.ui.tooling.preview
            )
            dependencies.add(
                "debugImplementation",
                libs.androidx.compose.ui.tooling
            )
        }
    }
}