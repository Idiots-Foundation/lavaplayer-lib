plugins {
    `java-library`
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(11))
    withSourcesJar()
}

if (properties.containsKey("snapshot")) {
    version = "$version-SNAPSHOT"
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

tasks.shadowJar {
    configurations = listOf(project.configurations.shadow.get())
    mergeServiceFiles()

    archiveBaseName.set(rootProject.name)
    archiveClassifier.set("")
    archiveAppendix.set("")

    relocate("org.apache", "io.github.subkek.lavaplayer.libs.org.apache")
    relocate("org.jsoup", "io.github.subkek.lavaplayer.libs.org.jsoup")
    relocate("com.fasterxml", "io.github.subkek.lavaplayer.libs.com.fasterxml")
    relocate("net.iharder", "io.github.subkek.lavaplayer.libs.net.iharder")
    relocate("ibxm", "io.github.subkek.lavaplayer.libs.ibxm")
    relocate("net.sourceforge", "io.github.subkek.lavaplayer.libs.net.sourceforge")
    relocate("org.json", "io.github.subkek.lavaplayer.libs.org.json")
    relocate("org.intellij", "io.github.subkek.lavaplayer.libs.org.intellij")
    relocate("org.jetbrains", "io.github.subkek.lavaplayer.libs.org.jetbrains")

    relocate("dev.lavalink", "io.github.subkek.lavaplayer.libs.dev.lavalink")
    relocate("com.grack", "io.github.subkek.lavaplayer.libs.com.grack")
    relocate("com.github.topi314", "io.github.subkek.lavaplayer.libs.com.github.topi314")
    relocate("com.auth0", "io.github.subkek.lavaplayer.libs.com.auth0")
    relocate("dev.schlaubi", "io.github.subkek.lavaplayer.libs.dev.schlaubi")

    exclude("lavalink-plugins/**")

    relocate("com.sedmelluq", "io.github.subkek.lavaplayer.libs.com.sedmelluq") {
        exclude("com/sedmelluq/discord/lavaplayer/natives/**")
    }
    relocate("mozilla", "io.github.subkek.lavaplayer.libs.mozilla")
    relocate("certificates", "io.github.subkek.lavaplayer.libs.certificates")
}

bukkit {
    name = rootProject.name
    version = rootProject.version as String
    main = "io.github.subkek.lavaplayer.CdLib"

    authors = listOf(
        "subkek",
        "yiskii"
    )
    website = "https://discord.gg/eRvwvmEXWz"
    apiVersion = "1.16"

    foliaSupported = true

    depend = listOf(
        "CustomDiscs",
    )
}

tasks.build {
    dependsOn(tasks.shadowJar)
}
