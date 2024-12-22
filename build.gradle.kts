plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    kotlin("plugin.serialization") version "2.1.0"  // For Kotlin Serialization plugin
}

group = "com.agusteam.travelo"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    /*  implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.insert-koin:koin-ktor:3.5.6")
    implementation("io.insert-koin:koin-logger-slf4j:3.5.6")
    implementation("ch.qos.logback:logback-classic:1.4.14")

    implementation(platform("io.github.jan-tennert.supabase:bom:3.0.3"))
      implementation("io.github.jan-tennert.supabase:postgrest-kt")
      implementation("io.github.jan-tennert.supabase:auth-kt")
      implementation("io.github.jan-tennert.supabase:realtime-kt")
      implementation("io.ktor:ktor-client-cio:3.0.2")*/


    implementation(libs.jetbrains.kotlinx.serialization)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.status)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.config.yaml)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)

    implementation("com.google.firebase:firebase-admin:9.2.0")
    implementation(platform("io.github.jan-tennert.supabase:bom:3.0.3"))
    implementation("io.ktor:ktor-client-cio:3.0.3")
    implementation("io.github.jan-tennert.supabase:postgrest-kt")
    implementation("io.github.jan-tennert.supabase:auth-kt")
}
