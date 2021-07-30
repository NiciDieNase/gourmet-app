plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("de.mannodermaus.android-junit5")
    id("com.google.gms.google-services")
}

val appName = "Genießer-App"
val versionString: String = File("versionfile").readText().trim()

val versionCode: String? by project

fun getMyVersionCode(): Int {
    val code = versionCode?.toInt() ?: 1
    println("VersionCode is set to $code")
    return code
}

android {

    val gourmetKeyStore: String by project
    val gourmetStorePassword: String by project
    val gourmetKeyName: String by project
    val gourmetKeyPassword: String by project
    val gourmetAppcenterId: String by project
    val gourmetAppcenterDevId: String by project

    compileSdk = 30
    defaultConfig {
        applicationId = "de.nicidienase.geniesser_app"
        minSdk = 21
        targetSdk = 30
        versionCode = getMyVersionCode()
        versionName = versionString
        manifestPlaceholders["label"] = appName
        javaCompileOptions {
            annotationProcessorOptions {
                argument("room.schemaLocation", "$projectDir/schemas")
            }
        }
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        //noinspection GroovyMissingReturnStatement, GroovyAssignabilityCheck
        create("release") {
            if (project.hasProperty("gourmetKeyStore") &&
                file(gourmetKeyStore).exists() &&
                file(gourmetKeyStore).isFile
            ) {
                println("Release app signing is configured: will sign APK")
                storeFile = file(gourmetKeyStore)
                storePassword = gourmetStorePassword
                keyAlias = gourmetKeyName
                keyPassword = gourmetKeyPassword
            } else {
                println("App signing data not found. Will not sign.")
            }
        }
    }

    buildTypes {
        getByName("debug") {
            manifestPlaceholders["label"] = "$appName-Debug"
            applicationIdSuffix = ".debug"
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            if (project.hasProperty("gourmetKeyStore") && file(gourmetKeyStore).exists() &&
                file(gourmetKeyStore).isFile
            ) {
                signingConfig = signingConfigs.getByName("release")
            }
        }
    }

    flavorDimensions.add("stage")
    productFlavors {
        create("prod") {
            dimension = "stage"
            buildConfigField("String", "APPCENTER_ID", gourmetAppcenterId)
        }
        create("dev") {
            dimension = "stage"
            applicationIdSuffix = ".dev"
            manifestPlaceholders["label"] = "$appName-DEV"
            buildConfigField("String", "APPCENTER_ID", gourmetAppcenterDevId)
        }
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerVersion = "1.5.10"
        kotlinCompilerExtensionVersion = "1.0.0-beta08"
    }

    packagingOptions {
        resources.excludes.add("META-INF/LICENSE*")
    }

    sourceSets {
        getByName("androidTest").assets.srcDirs("$projectDir/schemas")
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${rootProject.extra["kotlin_version"]}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3")

    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("androidx.core:core-ktx:1.5.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("com.google.android.material:material:1.4.0-rc01")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation("androidx.preference:preference-ktx:1.1.1")
    implementation("com.google.firebase:firebase-analytics:19.0.0")

    implementation("androidx.compose.compiler:compiler:1.0.0-beta08")
    implementation("androidx.compose.ui:ui:1.0.0-beta08")
    implementation("androidx.compose.ui:ui-tooling:1.0.0-beta08")
    implementation("androidx.compose.foundation:foundation:1.0.0-beta08")
    implementation("androidx.compose.material:material:1.0.0-beta08")
    implementation("androidx.compose.material:material-icons-core:1.0.0-beta08")
    implementation("androidx.compose.material:material-icons-extended:1.0.0-beta08")
    implementation("androidx.activity:activity-compose:1.3.0-beta01")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha06")
    implementation("androidx.compose.runtime:runtime-livedata:1.0.0-beta08")
    implementation("androidx.compose.runtime:runtime-rxjava2:1.0.0-beta08")

    val room_version = "2.3.0"
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

//    val nav_version_ktx = "2.3.5"
    val nav_version_ktx = "2.4.0-alpha03"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version_ktx")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version_ktx")

    val appCenterSdkVersion = "2.5.1"
    implementation("com.microsoft.appcenter:appcenter-analytics:$appCenterSdkVersion")
    implementation("com.microsoft.appcenter:appcenter-crashes:$appCenterSdkVersion")
    implementation("com.microsoft.appcenter:appcenter-distribute:$appCenterSdkVersion")

    val glideVersion = "4.9.0"
    implementation("com.github.bumptech.glide:glide:$glideVersion")
    kapt("com.github.bumptech.glide:compiler:$glideVersion")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("com.jakewharton.timber:timber:4.7.1")

    debugImplementation("com.facebook.stetho:stetho:1.5.1")
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.7")

    val hyperionVersion = "0.9.31"
    debugImplementation("com.willowtreeapps.hyperion:hyperion-core:$hyperionVersion")
    debugImplementation("com.willowtreeapps.hyperion:hyperion-attr:$hyperionVersion")
    debugImplementation("com.willowtreeapps.hyperion:hyperion-build-config:$hyperionVersion")
    debugImplementation("com.willowtreeapps.hyperion:hyperion-crash:$hyperionVersion")
//    debugImplementation("com.willowtreeapps.hyperion:hyperion-sqlite:$hyperionVersion")
    debugImplementation("com.willowtreeapps.hyperion:hyperion-disk:$hyperionVersion")
    debugImplementation("com.willowtreeapps.hyperion:hyperion-geiger-counter:$hyperionVersion")
    debugImplementation("com.willowtreeapps.hyperion:hyperion-measurement:$hyperionVersion")
    debugImplementation("com.willowtreeapps.hyperion:hyperion-phoenix:$hyperionVersion")
//    debugImplementation("com.willowtreeapps.hyperion:hyperion-recorder:$hyperionVersion")
    debugImplementation("com.willowtreeapps.hyperion:hyperion-shared-preferences:$hyperionVersion")
    debugImplementation("com.willowtreeapps.hyperion:hyperion-timber:$hyperionVersion")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.6.0")

    testImplementation("org.junit.platform:junit-platform-launcher:1.7.0")

    testImplementation("org.assertj:assertj-core:3.12.2")
    testImplementation("io.mockk:mockk:1.9.3.kotlin12")

    androidTestImplementation("android.arch.persistence.room:testing:1.1.1")
    androidTestImplementation("androidx.test:runner:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}
