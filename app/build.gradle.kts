plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.chamayetu"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.chamayetu"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("com.google.firebase:firebase-auth:22.3.1")
    implementation("com.google.firebase:firebase-firestore:24.10.3")
    val retrofitVersion = "2.9.0"
    val moshiVersion = "1.13.0"
    val roomVersion = "2.5.2"


    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")



    // kotlin coroutines dependency
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // hilt dependencies
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")


    //compose-navigation dependencies
    implementation ("androidx.navigation:navigation-compose:2.6.0")
    implementation ("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")

    // viewModel-compose dependencies
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")


    implementation ("androidx.compose.material:material-icons-extended")


    // retrofit and moshi dependencies


    implementation ("com.squareup.okhttp3:okhttp:4.11.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3")
    implementation ("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation ("com.squareup.retrofit2:converter-moshi:$retrofitVersion")
    implementation ("com.squareup.moshi:moshi:$moshiVersion")
    implementation ("com.squareup.moshi:moshi-kotlin:$moshiVersion")

    // https://mvnrepository.com/artifact/com.squareup.retrofit2/converter-gson
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")


    // room dependencies
    implementation ("androidx.room:room-runtime:$roomVersion")
    implementation ("androidx.room:room-ktx:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")

    // dataStore dependency
    implementation ("androidx.datastore:datastore-preferences:1.0.0")

    //splash screen
    implementation ("androidx.core:core-splashscreen:1.0.0")
}



kapt {
    correctErrorTypes = true
}