# Thaumic Mastery

Progress: 20%

## Info
Thaumic Mastery is an addon for Thaumcraft which adds endgame spells.

### Spells
There are various spells added by this mod. 
These spells require you to press a hotkey while holding a wand or staff.

#### Mirror Dimension
##### Aspect: Ordo
##### Vis cost: 500
##### Features:
- Gives caster creative flight
- Regenerates vis in wand (except for Ordo)
- Gives caster buffs
- Deletes flux when it disappears

## Things left to do
### Spells
- Mirror dimension functionality
- Nova Blast
- Golem Summon
- World Eater
- Vis to Essentia

### Cleanliness
- Massive Refactor

## Important Dev Notes:
- All GUI textures MUST be 256x256
- All Thaumonomicon backgrounds must be 512x512
- Style guide for adding research pages:
  - `new ResearchPage()`
  - `.noParameterMethods()`
  - `.setPages()`
  - `.registerResearchItem();`