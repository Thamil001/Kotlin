plugins {
    kotlin("jvm") version "2.0.21"
    `maven-publish`
}

group = "com.Math.mathematics"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}
allprojects {
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
