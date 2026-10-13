plugins {
    id("org.springframework.boot") version "4.1.1"
    id("io.spring.dependency-management") version "1.1.7"
    id("java")
}

group = "org.mardon"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    val springVersion = "7.0.9"
    val assertjVersion = "3.27.7"
    val jakartaAnnotationVersion = "3.0.0"

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.postgresql:postgresql")

    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:${assertjVersion}")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // not used because of dependency management
//    implementation("org.springframework:spring-core:$springVersion")
//    implementation("org.springframework:spring-context:$springVersion")
//    implementation("jakarta.annotation:jakarta.annotation-api:$jakartaAnnotationVersion")
}

tasks.test {
    useJUnitPlatform()
}