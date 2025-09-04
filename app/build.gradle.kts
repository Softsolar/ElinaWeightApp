
plugins { id("com.android.application"); id("org.jetbrains.kotlin.android"); id("org.jetbrains.kotlin.kapt") }
android { namespace = "com.example.elinaweight"; compileSdk = 34
  defaultConfig { applicationId = "com.example.elinaweight"; minSdk = 26; targetSdk = 34; versionCode = 1; versionName = "1.0"; vectorDrawables.useSupportLibrary = true }
  buildFeatures { compose = true }
  composeOptions { kotlinCompilerExtensionVersion = "1.5.14" }
  packaging { resources.excludes += "/META-INF/{AL2.0,LGPL2.1}" }
}
dependencies {
  val composeBom = platform("androidx.compose:compose-bom:2024.06.00"); implementation(composeBom); androidTestImplementation(composeBom)
  implementation("androidx.activity:activity-compose:1.9.2"); implementation("androidx.compose.ui:ui"); implementation("androidx.compose.ui:ui-tooling-preview"); debugImplementation("androidx.compose.ui:ui-tooling"); implementation("androidx.compose.material3:material3:1.2.1"); implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.4"); implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.4")
  implementation("androidx.room:room-runtime:2.6.1"); kapt("androidx.room:room-compiler:2.6.1"); implementation("androidx.room:room-ktx:2.6.1")
  implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
  implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0") }
