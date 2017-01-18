Note: LevelReader is interpreter for these level files. To add features, please modify this class.

1st line
--------

1st number - Map Length - Number of map movements until the end of the level, minimum is 512
2nd string - Map Prefix - This is the type of images used to display the room


Remaining lines
---------------

Each line is assumed to be a single object (although spacing does not matter).
This means you can indent and add newlines without affecting the engine.
An object is defined with the following numbers:

    N           - The time when the instruction will be executed
                    The time is measured in frames since the beginning of the level
                    N > 100 is recommended for the first object
    ID          - The identification number of the instruction
    k           - The number of remaining numbers in this object
    k numbers   - The k numbers give the LevelReader additional information about this object
                    I will label these numbers k1, k2, k3, etc

The game engine assumes that the N's are in increasing order.
Any other order will cause the level to behave strangely.


ID values supported
-------------------

100 (Fly)
  This is the simplest possible enemy. This enemy spawns at the top of the screen.
  k = 2
    k1 - y velocity of the fly
    k2 - The x coordinate that this fly will spawn on
  Example usage:
    2 256 (A slow fly that moves down the center)
    3 32  (A fast fly that moves at the left side)

101 (Santa Poop)
  This enemy jumps every 70 frames and on every landing, and shoots 4 droplets at right angles.
  k = 3
    k1 - y velocity of the enemy while jumping
	k2 - Speed of droplets that spawn on every landing
    k3 - The x coordinate that this Santa Poop will spawn on
  Example usage:
    5 2 256  (Very normal movement down the center)
    -4 2 256 (Moves upwards during a jump, stays on the screen for a longer time)

102 (Blood droplet)
  It is not recommended to use this enemy directly.
  If you still want to use this enemy (for effect?), the enemy should spawn off-screen and move inside.
  k = 4
    k1 - x coordinate of the spawn
    k2 - y coordinate of the spawn
    k3 - x velocity of the enemy
    k4 - y velocity of the enemy
  Example usage:
    256 522 0 -2 (Droplet spawns at the bottom edge and moves up the center)
	-10 -10 2 2  (Droplet spawns at top-left corner and moves diagonally towards the center)

103 (Nerve ending)
  This version shoots bullets all at once, periodically.
  k = 4
    k1 - the bullet's speed
    k2 - number of bullets every period
    k3 - length of a period, in frames
    k4 - x coordinate of the spawn
  Example usage:
    2 12 90 256 (regular nerve ending down the middle, looks generic)

104 (Santa Poop 2)
  This enemy waits, then shoots 3 streams of droplets at the player.
  Afterwards, it jumps every 70 frames and no longer shoots droplets.
  k = 4
    k1 - y velocity of the enemy while jumping
	k2 - Acceleration of droplets (0.02 is pretty fast, 0.002 is very slow)
    k3 - Enemy shoots at this y coordinate (between 0 and 512)
    k4 - The x coordinate that this Santa Poop will spawn on
  Example usage:
    5 0.005 100 256 (Waits in the center, then shoots)
