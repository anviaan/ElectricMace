plugins {
    id("org.jetbrains.gradle.plugin.idea-ext") version ("1.1.7")
}

val MOD_VERSION = project.property("version") as String
val ARCHIVE_NAME = project.property("mod_name") as String
val COMPATIBLE_VERSIONS = "[1.21.2, 1.22)"

tasks.register("printEnv") {
    doLast {
        val envFile = File(System.getenv("GITHUB_ENV"))
        envFile.appendText("MOD_VERSION=$MOD_VERSION\n")
        envFile.appendText("RELEASE_NAME=$ARCHIVE_NAME-$MOD_VERSION\n")
        envFile.appendText("GAME_VERSIONS=$COMPATIBLE_VERSIONS\n")
    }
}