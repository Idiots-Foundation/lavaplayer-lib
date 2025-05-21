plugins {
    `java-library`
    id("io.github.goooler.shadow") version "8.1.8"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
}

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://jitpack.io/")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://maven.lavalink.dev/snapshots")
    maven("https://maven.lavalink.dev/releases")
}

dependencies {
    shadow(libs.lavaplayer.youtube)

    shadow(libs.lavaplayer) {
        exclude("org.slf4j")
    }

    compileOnly("org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    disableAutoTargetJvm()
}

tasks.jar {
    enabled = false
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks.shadowJar {
    archiveFileName = "${rootProject.name}-$version.jar"

    configurations = listOf(project.configurations.shadow.get())
    mergeServiceFiles()

    fun relocate(pkg: String) = relocate(pkg, "io.github.subkek.lavaplayer.libs.$pkg") {
        exclude("com/sedmelluq/discord/lavaplayer/natives/**")
    }

    relocate("org.apache")
    relocate("org.jsoup")
    relocate("com.fasterxml")
    relocate("org.yaml.snakeyaml")
    relocate("org.simpleyaml")
    relocate("org.jflac")
    relocate("org.json")
    relocate("org.tritonus")
    relocate("mozilla")
    relocate("junit")
    relocate("javazoom")
    relocate("certificates")
    relocate("org.hamcrest")
    relocate("org.junit")
    relocate("net.sourceforge.jaad.aac")
    relocate("net.kyori")
    relocate("net.iharder")
    relocate("com.tcoded")
    relocate("com.grack")
    relocate("dev.lavalink")
    relocate("org.intellij")
    relocate("org.jetbrains")
    relocate("com.sedmelluq")
    relocate("dev.jorel.commandapi")
}
