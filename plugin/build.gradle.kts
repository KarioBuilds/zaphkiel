import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

dependencies {
    implementation(project(":project:common"))
    implementation(project(":project:common-impl"))
    implementation(project(":project:module-bukkit"))
    implementation(project(":project:module-legacy-api"))
}

tasks {
    withType<ShadowJar> {
        archiveClassifier.set("")
        exclude("META-INF/maven/**")
        exclude("META-INF/tf/**")
        exclude("module-info.java")
        relocate("ink.ptms.um", "ink.ptms.zaphkiel.um")
        relocate("taboolib", "ink.ptms.zaphkiel.taboolib")
    }
    build {
        dependsOn(shadowJar)
    }
}