Note: LevelReader is interpreter for these level files. To add features, please modify this class.

1st line
--------

1st number - Map Length - Number of map movements until the end of the level, minimum is 512


Remaining lines
---------------

Each line is assumed to be a single object (although spacing does not matter).
This means you can indent and add newlines without affecting the engine.
An object is defined with the following numbers:

    N         - The time when the instruction will be executed
                  The time is measured in frames since the beginning of the level
                  N > 100 is recommended for the first object
    ID        - The identification number of the instruction
    k         - The number of remaining numbers in this object
    k numbers - The k numbers give the LevelReader additional information about this object
                  I will label these numbers k1, k2, k3, etc

The game engine assumes that the N's are in increasing order.
Any other order will cause the level to behave strangely.


ID values supported
-------------------

100
  This is a fly, the simplest possible enemy. This enemy spawns at the top of the screen.
  k = 2
  k1 - The downwards speed of the fly, in pixels/frame.
  k2 - The x coordinate that this fly will spawn on.

