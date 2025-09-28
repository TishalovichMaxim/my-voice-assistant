plugins {
    application
    id("io.freefair.lombok") version "9.0.0-rc2"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation(libs.guava)
    implementation("com.fasterxml.jackson.core:jackson-databind:2.20.0")

    implementation("org.springframework:spring-context:6.2.11")

    implementation("org.mapstruct:mapstruct:1.6.3")

    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")

    implementation("ch.qos.logback:logback-classic:1.5.18")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(24)
    }
}

application {
    mainClass = "by.tishalovichm.mva.App"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
