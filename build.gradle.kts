@file:Suppress("PropertyName", "SpellCheckingInspection")

import io.izzel.taboolib.gradle.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    id("io.izzel.taboolib") version "2.0.2"
    id("org.jetbrains.kotlin.jvm") version "1.8.22"
}

val taboolib_version: String by project

subprojects {
    apply<JavaPlugin>()
    apply(plugin = "io.izzel.taboolib")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    // TabooLib 配置
    taboolib {
        env {
            install(UNIVERSAL, BUKKIT_ALL, NMS_UTIL, DATABASE, UI, KETHER, EXPANSION_PLAYER_DATABASE)
        }
        version { taboolib = "6.1.0" }
    }

    // 全局仓库
    repositories {
        mavenLocal()
        mavenCentral()
        maven("https://repo.tabooproject.org/repository/releases")
    }
    // 全局依赖
    dependencies {
        compileOnly("org.apache.commons:commons-lang3:3.13.0")
        compileOnly("com.google.guava:guava:32.1.3-jre")
        compileOnly("com.google.code.gson:gson:2.10.1")
        compileOnly("ink.ptms.core:v11904:11904:mapped")
        compileOnly("ink.ptms.core:v11200:11200")
        compileOnly(kotlin("stdlib"))

        compileOnly("io.izzel.taboolib:common:$taboolib_version")
        implementation("io.izzel.taboolib:common-5:$taboolib_version")
        implementation("io.izzel.taboolib:module-chat:$taboolib_version")
        implementation("io.izzel.taboolib:module-configuration:$taboolib_version")
        implementation("io.izzel.taboolib:module-nms:$taboolib_version")
        implementation("io.izzel.taboolib:module-nms-util:$taboolib_version")
        implementation("io.izzel.taboolib:module-ui:$taboolib_version")
        implementation("io.izzel.taboolib:platform-bukkit:$taboolib_version")
    }

    // 编译配置
    java {
        withSourcesJar()
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xjvm-default=all", "-Xextended-compiler-checks")
        }
    }
}

gradle.buildFinished {
    buildDir.deleteRecursively()
}