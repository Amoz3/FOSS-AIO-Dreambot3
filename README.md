# FOSS AIO
### Open source Dreambot3 AIO

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
  * `fallback` - This is the "fallback" branch, to prevent tick timeout deadlocks. This behavior branch is only called if there is a tick timeout (Timing.tickTimeout > 0) and there are no other valid leaves. It will reset the tick timeout to a random value using Timing.loopReturn().

* `org/dreambot/config` is for singleton classes holding global state
* `org/dreambot/data` is mostly for enums and constants like locations or chosen activity

## Behaviour
Script should choose an activity (from `org/dreambot/data/Activities` enum) randomly every 2-4 hours 
(arbitrary amount of time) that activity will be set in config singleton and the script will decide what to mine based
on the state of the account, eg MINING is selected, you are a f2p account so the script will go to branch F2PMiningBranch
and choose what to mine and where based on your levels