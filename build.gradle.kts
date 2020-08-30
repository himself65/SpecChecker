import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.0"
    application
}
group = "himself65.specchecker"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies {
    implementation("junit:junit:4.13")
    testImplementation(kotlin("test-junit5"))
}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}
application {
    mainClassName = "MainKt"
}