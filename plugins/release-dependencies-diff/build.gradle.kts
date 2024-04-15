import org.jetbrains.kotlin.config.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    alias(libs.plugins.detekt)
}

dependencies {
    implementation(libs.plugin.kotlin)
}


java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType(KotlinJvmCompile::class.java).configureEach {
    kotlinOptions.jvmTarget = JvmTarget.JVM_11.toString()
}

detekt {
    buildUponDefaultConfig = true
    config.from(file("../../detekt.yml"))
}

gradlePlugin {
    plugins {
        create("release-dependencies-diff-create") {
            id = "release-dependencies-diff-create"
            implementationClass = "ReleaseDependenciesCreatePlugin"
        }
        create("release-dependencies-diff-compare") {
            id = "release-dependencies-diff-compare"
            implementationClass = "ReleaseDependenciesDiffPlugin"
        }
    }
}
