import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.gonzalomonzon02.movieapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.gonzalomonzon02.movieapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }

    flavorDimensions.add("env")
    productFlavors {
        create("prod") {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://api.themoviedb.org/3/\""
            )

            buildConfigField(
                "String",
                "IMAGE_URL",
                "\"https://image.tmdb.org/t/p/w500\""
            )

            buildConfigField(
                "String",
                "TMDB_API_KEY",
                "\"${getPropertyFromAllPropertiesFiles("TMDB_API_KEY")}\""
            )
        }
    }
}

dependencies {
    // Libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.glide.compose)

    // Bundles
    implementation(libs.bundles.presentation)
    implementation(libs.bundles.networking)
    implementation(libs.bundles.local.db)
    implementation(libs.bundles.dependency.injection)

    // BOMs
    implementation(platform(libs.androidx.compose.bom))

    // Compilers
    ksp(libs.room.compiler)
    debugImplementation(libs.androidx.ui.tooling)

    // Test
    testImplementation(libs.junit)
    testImplementation(libs.bundles.test.mock)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    debugImplementation(libs.bundles.test.ui)
}

fun getPropertyFromAllPropertiesFiles(key: String): String {
    val properties = Properties()

    rootProject.projectDir.listFiles { file ->
        file.isFile && file.name.endsWith(".properties")
    }?.forEach { file ->
        file.inputStream().use { stream ->
            properties.load(stream)
        }
    }

    return properties.getProperty(key)
        ?: error("Property $key not found in any .properties file in project root")
}