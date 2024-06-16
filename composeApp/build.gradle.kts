import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    id("app.cash.sqldelight") version "2.0.2"
    kotlin("plugin.serialization") version "2.0.0"
}


sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.expenseApp.db")
        }
    }
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions{
                jvmTarget = "11"
            }
        }
        //@OptIn(ExperimentalKotlinGradlePluginApi::class)
        //compilerOptions {
          //  jvmTarget.set(JvmTarget.JVM_11)
        //}
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            //Koin
            implementation(project.dependencies.platform("io.insert-koin:koin-bom:3.5.1"))
            implementation("io.insert-koin:koin-core")
            implementation("io.insert-koin:koin-android")

            //SQLDelight
            implementation("app.cash.sqldelight:android-driver:2.0.2")

            // Ktor
            implementation(libs.ktor.client.okhttp)
            implementation(libs.kotlinx.coroutines.android)


        }
        commonMain.dependencies {
            implementation(compose.runtime)
            api(compose.foundation)
            api(compose.animation)
            api(compose.materialIconsExtended)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            // Navigation Precompose
            api("moe.tlaster:precompose:1.5.10")
            //ViewModel
            api("moe.tlaster:precompose-viewmodel:1.5.10") // For ViewModel intergration

            //Koin
            implementation(project.dependencies.platform("io.insert-koin:koin-bom:3.5.1"))
            implementation("io.insert-koin:koin-core")
            implementation("io.insert-koin:koin-compose")
            api("moe.tlaster:precompose-koin:1.5.10")
            // Ktor
            implementation(libs.ktor.client.core)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.ktor.serialization)
            implementation(libs.ktor.content.negotiation)

        }
        iosMain.dependencies {
            //iOS dependencies
            //SQLDelight
            implementation("app.cash.sqldelight:native-driver:2.0.2")
            implementation("co.touchlab:stately-common:2.0.5")
            //Ktor
            implementation(libs.ktor.client.darwin)

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "org.example.project"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "org.example.project"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
    buildFeatures{
        compose = true
    }
}
dependencies {
    implementation(libs.androidx.material3.android)
}

