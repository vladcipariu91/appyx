import org.jetbrains.kotlin.config.JvmTarget

plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    alias(libs.plugins.detekt)
}

dependencies {
    implementation(libs.plugin.android)
    implementation(libs.plugin.kotlin)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType(org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile::class.java).configureEach {
    kotlinOptions.jvmTarget = JvmTarget.JVM_11.toString()
}

detekt {
    buildUponDefaultConfig = true
    config.from(file("../../detekt.yml"))
}

gradlePlugin {
    plugins {
        create("appyx-publish-android") {
            id = "appyx-publish-android"
            implementationClass = "AndroidAppyxPublishPlugin"
        }
        create("appyx-publish-java") {
            id = "appyx-publish-java"
            implementationClass = "JavaAppyxPublishPlugin"
        }
    }
}
