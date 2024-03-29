# FOSS BOT - AIO
### Open source Dreambot3 AIO

## Activities supported
- F2P mining without banking 

## Setup
 1. git clone this repo
 2. replace the `<outputDirectory>` in pom.xml with the path to your dreambot scripts folder
 3. (optional) modify util `Timing.java` with your own sleep values
 4. mvn install

## Structure 
* `org/dreambot/framework` is TBL framework read more https://github.com/LostVirt/Dreambot-Tree-Branch-Framework
 this script is actually using https://github.com/Bonfire/Lost-Delayed-TBL-Framework so that everyone building their own
 version can easily modify sleep timing / anti ban profile
* `org/dreambot/util` is for classes that provide utility when scripting such as the Interaction class from delayed tbl
* `org/dreambot/task` is for branches and leaves, should be organized Activity > Branch > Leaf, 1 activity may have 
more than 1 branch, eg mining might have F2PMiningBranch, P2PMiningBranch, MotherLodeMineBranch etc 

  * `timeout` This behavior branch is called anytime there is a "tick timeout" (Timing.tickTimeout > 0). It 
  will take priority over all other branches/leaves, decrement the tickTimeout by one, then wait 600ms (one tick).
  * `fallback` - This is the "fallback" branch, to prevent tick timeout deadlocks. This behavior branch is only called
  if there is a tick timeout (Timing.tickTimeout > 0) and there are no other valid leaves. It will reset the tick timeout
  to a random value using Timing.loopReturn().

* `org/dreambot/config` is for singleton classes holding global state
* `org/dreambot/data` is mostly for enums and constants like locations or chosen activity
* this script uses fluffees paint api for more flexible nice looking paint, 
read more https://community.tribot.org/topic/76023-snippet-fluffees-paint-api/

## Behaviour
Script should choose an activity (from `org/dreambot/data/Activities` enum) randomly every 2-4 hours 
(arbitrary amount of time) that activity will be set in config singleton and the script will decide what to mine based
on the state of the account, eg MINING is selected, you are a f2p account so the script will go to branch F2PMiningBranch
and choose what to mine and where based on your levels

## Todo
 * GUI, this will be needed before everything else as the settings passed from it will feed the logic
   * 1-99 spinner for each trainable skill
   * List of quests, selected quests will be trained if the activity mode is QUEST, in order of optimal quest guide
   * Toggle money makers, will allow MONEYMAKING activity to be selected then will probably choose based on a gp/hr
   estimate, maybe another list like how quests would work
   * Check box for toggle profitable / fast mode, eg, fast would power mine profitable would bank (skill training
   not for money makers)
   * Discord webhook url, will send webhook on level up of any skill
 * Obviously, a ton of skills, quests, and money makers
