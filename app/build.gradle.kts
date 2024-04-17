import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")



//    id ("androidx.room")
   // id("kotlin-kapt")
  //  id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.solutionx"
    flavorDimensions += ("logging")
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.solutionx"
        minSdk = 24
        targetSdk = 33
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
        getByName("debug") {
            applicationIdSuffix = ".debug"
        }
    }

    productFlavors {
        create("logCat") {
            dimension = "logging"
            applicationId = "com.example.logCat"
        }
        create("logWriter") {
            dimension = "logging"
            applicationId = "com.example.logWriter"
        }
        create("production") {
            dimension = "logging"
            applicationId = "com.example.production"
        }
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java).configureEach {
            kotlinOptions.jvmTarget = "1.8"
            kotlinOptions.languageVersion = "1.9"
        }
    }

    androidComponents {
        beforeVariants { variant ->
            val isReleaseWithLogCatOrLogWriterFlavor = variant.buildType == "release" &&
                    variant.productFlavors.any { it.second in listOf("logCat", "logWriter") }

            val isDebugWithProductionFlavor =
                variant.buildType == "debug" && variant.productFlavors.any { it.second == "production" }

            if (isReleaseWithLogCatOrLogWriterFlavor || isDebugWithProductionFlavor) {
                variant.enable = false
            }
        }
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.emoji2:emoji2-emojipicker:1.4.0")
    implementation("com.google.firebase:firebase-crashlytics-buildtools:2.9.9")
    implementation("androidx.room:room-common:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
//    implementation("com.google.android.ads:mediation-test-suite:3.0.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //intuit
    implementation("com.intuit.sdp:sdp-android:1.1.0")

//
//    implementation ("com.google.android.material:material:<version>")
//
//    // navigation components
//    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
//    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
//
    // gson
    implementation ("com.google.code.gson:gson:2.10.1")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    //Gson converter
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.2.2")
    // coroutines(with retrofit)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
//
//    // room
//    implementation ("androidx.room:room-runtime:2.6.1")
//    annotationProcessor ("androidx.room:room-compiler:2.6.1")

//    kapt ("androidx.room:room-compiler:2.6.1")

//Dagger - Hilt
    implementation ("com.google.dagger:hilt-android:2.51")
    kapt ("com.google.dagger:hilt-compiler:2.51")
//
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation ("androidx.fragment:fragment-ktx:1.6.2")


//    // Material
//    implementation("libs.material")
//
//    // Kotlin Reflect
//    implementation("libs.kotlin.reflect")

    //interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.2")


   //DataStore
    implementation ("androidx.datastore:datastore-preferences:1.0.0")

    implementation ("org.jetbrains.kotlin:kotlin-reflect:1.9.23")

    testImplementation ("org.mockito:mockito-core:2.19.0")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")

}

kapt {
    correctErrorTypes = true
}
