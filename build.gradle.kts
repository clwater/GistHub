import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.google.devtools.ksp").version("1.6.10-1.0.4")
}

group = "com.clwater"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation("io.coil-kt:coil-compose:2.0.0")
                implementation("com.squareup.moshi:moshi-kotlin-codegen:1.13.0")
                implementation("com.squareup.moshi:moshi-kotlin:1.13.0")
                implementation("androidx.compose.material:material-icons-extended:1.1.0")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "gisthub"
            packageVersion = "1.0.0"
        }
    }
}
