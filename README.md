# Andrew's Datapack Utils

A fabric mod with useful utilities for Datapacks!

## Features
- Removes the restriction of /data and /execute store being unable to modify the entity data of players
- The /aputils command
  - Has most of the utilities here
  - /aputils calc
    - A calculator with some useful operations! Works well with /execute store and function macroes
    - subcommands include add, sub, mul, div, power, sqrt. All inputs are doubles.
    - Due to Brigadier and Minecraft limitations, the return value (for /execute store result) is an int, so you can't use this to calculate decimal results sadly. The command feedback, however, will show decimals just fine because that isn't restricted to an int (instead a string, which is much more flexable) like the return value is.
- More to come!