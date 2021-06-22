rootProject.name = "blockpartyplus"

include("ihdeveloper-legacy-api", "legacy-game-api")

project(":ihdeveloper-legacy-api").projectDir = File("hd-api")
project(":legacy-game-api").projectDir = File("game-api")
