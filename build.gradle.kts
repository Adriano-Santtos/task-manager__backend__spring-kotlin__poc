import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val flywayVersion = "10.7.2"
    val kotlinVersion = "1.9.21"
    val ktlintVersion = "10.0.0"
    val springBootVersion = "3.2.4"
    val springDependencyManagementVersion = "1.1.4"

    id("io.spring.dependency-management") version springDependencyManagementVersion
    id("org.flywaydb.flyway") version flywayVersion
    id("org.jlleitschuh.gradle.ktlint") version ktlintVersion
    id("org.springframework.boot") version springBootVersion
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
}

group = "poc"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

val springDocOpenAPiVersion = "2.3.0"
val kotlinLoggingVersion = "2.0.11"
val mockkVersion = "1.13.9"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.flywaydb:flyway-core")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springDocOpenAPiVersion")
    implementation("io.github.microutils:kotlin-logging-jvm:$kotlinLoggingVersion")

    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.h2database:h2")
    testImplementation("io.mockk:mockk:$mockkVersion")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
