plugins {
    java
    `maven-publish`
    kotlin("jvm") version "2.0.21"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "org.ttlzmc"
version = "1.1-SNAPSHOT"
val minecraftVersion = "1.21.4"

dependencies {
    compileOnly("io.papermc.paper:paper-api:$minecraftVersion-R0.1-SNAPSHOT")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "org.ttlzmc.gui"
            artifactId = "ttlzgui"
            version = "testing-1.1"

            from(components["java"])
        }
    }
}

tasks {
    runServer {
        minecraftVersion(minecraftVersion)
    }

    jar {
        archiveFileName = "ttlzmc-gui-$version+paper-$minecraftVersion.jar"
    }
}

kotlin {
    jvmToolchain(21)
}