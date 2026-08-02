plugins {
    id("com.android.application")
}

android {
    namespace = "com.muslimqi.design"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.muslimqi.design"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "0.1.0-design"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
        debug {
            applicationIdSuffix = ".demo"
            versionNameSuffix = "-demo"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
