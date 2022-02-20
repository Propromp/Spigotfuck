import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.apache.tools.ant.filters.ReplaceTokens

plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.1.2"
    kotlin("jvm") version "1.6.10"
}

group = "net.propromp"
version = "1.0-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

repositories {
    mavenLocal()
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    //paper
    compileOnly("com.destroystokyo.paper:paper-api:1.16.5-R0.1-SNAPSHOT")
    //maven local
    compileOnly("org.spigotmc:spigot:1.16.5-R0.1-SNAPSHOT")
    //maven central
    compileOnly("net.propromp:neocommander:1.5.1")
    compileOnly("net.propromp:brainfuckkt:1.1")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
    shadowJar {
        dependencies {
            val children = mutableListOf<ResolvedDependency>()
            exclude {
                if (it.moduleGroup == "org.jetbrains.kotlin" || children.contains(it)) {
                    children.addAll(it.children)
                    true
                } else {
                    false
                }
            }
        }
    }
    processResources {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        from(sourceSets["main"].resources.srcDirs) {
            filter(ReplaceTokens::class,mapOf("version" to version))
        }
    }
    create<Copy>("buildPlugin") {
        from(jar)
        into("server/plugins/")
    }
}