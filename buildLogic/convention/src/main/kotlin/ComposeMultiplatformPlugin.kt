import com.sedate.qrku.alias
import com.sedate.qrku.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class ComposeMultiplatformPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            alias(libs.plugins.composeMultiplatform)
            alias(libs.plugins.composeCompiler)
        }
    }
}