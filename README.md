# BlockParty+ (Minecraft Minigame)
It's a revamped version of HiveMC BlockParty.
It uses a 3D arena instead of a 2D floor and includes different entertainment modes!

## Mistakes I made
This will be a list of mistakes I made when making this game.
It will only include the solved mistakes for now.

- I misspelled `destroy` with `distory` **a lot**.
- I misspelled `becuase` with `becuase` 🤦‍♂️


## Backstory
I started working on this game since I was 14 years old (2017). I didn't have a lot of experience in Java and game development.
But, this game is by far one of the best projects I have done when I started in game development.

This project started when I was working with the first Minecraft server team I worked with.
The team was called **HerozSA Network**.
For some reason, the team was dissolved and the project went dead and became dusty.

In 2021, I tried to re-alive the project and got it working with the map too! \o/

So, I decided to make a revamped version of it to, solve its bugs, and make minor improvements. 😄

## How I revamped the project?
I had the source code on my old laptop, but I was too lazy to get them from there.
So, I did as below

1. Decompile the original jars
2. Turn them into gradle project for dependency management
3. Finally, Rebuild the whole thing!
4. You have a revamped version of the game

**Note:** I will include the original jars in a folder called `original/` in case you wanted to take a look at my legacy code in **2017**.

## How to compile
```shell
./gradlew shadowJar
```
You will find the compiled jars in the `build` directory.

**Note:** There's no different between the jars with or without `scratch-`.

## Modules
| Name | Path | Description |
|------|------|-------------|
| Game API (Legacy) | `game-api/` | API that was designed to identify games on the network |
| iHDeveloper API (Legacy) | `hd-api/` | Library to help create some functionality in games faster |
| BlockParty+ Source Code (Core) | `src/` | The main source code to run the game! |

