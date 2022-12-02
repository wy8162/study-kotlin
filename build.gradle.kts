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
    implementation("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    implementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    implementation("com.github.javafaker:javafaker:1.0.2")
    runtimeOnly("io.ktor:ktor-client-core:1.6.6")
    implementation("io.ktor:ktor-client-cio:1.6.6")
    implementation("io.github.microutils:kotlin-logging-jvm:2.1.20")
    implementation("ch.qos.logback:logback-classic:1.2.11")
    implementation("io.arrow-kt:arrow-core:1.1.2")
    // For convenience purpose. It should be test scope testImplementation.
    implementation("org.jetbrains.kotlin:kotlin-test:1.5.31")
    testImplementation("org.assertj:assertj-core:3.21.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "16"
}