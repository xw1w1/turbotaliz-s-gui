plugins {
    id("java")
    kotlin("jvm")
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "org.ttlzmc"
version = "1.1-SNAPSHOT"

repositories {
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    mavenCentral()
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
}

tasks {
    runServer {
        minecraftVersion("1.21.4")
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}