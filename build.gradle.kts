plugins {
    id("java")
    id("xyz.jpenilla.run-paper") version "2.3.1"
    kotlin("jvm")
}

group = "org.ttlzmc"
version = "1.0-SNAPSHOT"

repositories {
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    mavenCentral()
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    implementation(kotlin("stdlib-jdk8"))
}

tasks {
    runServer {
        minecraftVersion("1.21.4")
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}