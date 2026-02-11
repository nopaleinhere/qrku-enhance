# -----------------------------
# Kotlin
# -----------------------------
-keep class kotlin.Metadata { *; }

# -----------------------------
# Jetpack Compose
# -----------------------------
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**

# -----------------------------
# Koin
# -----------------------------
-keep class org.koin.** { *; }
-dontwarn org.koin.**

# -----------------------------
# kotlinx.serialization
# -----------------------------
-keepclassmembers class ** {
    @kotlinx.serialization.Serializable *;
}

# -----------------------------
# ZXing
# -----------------------------
-keep class com.google.zxing.** { *; }
-dontwarn com.google.zxing.**

# -----------------------------
# Navigation
# -----------------------------
-keep class androidx.navigation.** { *; }

# -----------------------------
# ViewModel
# -----------------------------
-keep class androidx.lifecycle.** { *; }

# -----------------------------
# Coroutines
# -----------------------------
-dontwarn kotlinx.coroutines.**

# -----------------------------
# Ktor (jika pakai)
# -----------------------------
-dontwarn io.ktor.**

# -----------------------------
# Reflection safety
# -----------------------------
-keepattributes Signature
-keepattributes *Annotation*