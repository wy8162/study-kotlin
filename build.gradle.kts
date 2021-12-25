import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.0"
}

group = "y.w"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("com.beust:klaxon:5.5")
    implementation("junit:junit:4.13.1")
    runtimeOnly("io.ktor:ktor-client-core:1.6.6")
    implementation("io.ktor:ktor-client-cio:1.6.6")
    // For convenience purpose. It should be test scope testImplementation.
    implementation("org.jetbrains.kotlin:kotlin-test:1.5.31")
    testImplementation("org.assertj:assertj-core:3.21.0")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "16"
}