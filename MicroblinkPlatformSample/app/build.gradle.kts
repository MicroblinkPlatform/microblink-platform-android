plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.microblink.platform.sample"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.microblink.platform.sample"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.mbp.core) {
        artifact {
            type = "aar"
        }
    }

    implementation(libs.mbp.liveness) {
        artifact {
            type = "aar"
        }
    }

    // Navigation
    implementation(libs.androidx.compose.navigation)

    // Protobuf
    implementation(libs.protobuf.kotlin.lite)

    // Microblink SDK
    implementation(libs.blinkid.verify.ux)
}