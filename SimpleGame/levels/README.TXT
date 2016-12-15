Note: LevelReader is interpreter for these level files. To add features, please modify this class.

1st line
--------

1st number - Map Length - Number of map movements until the end of the level, minimum is 512


Remaining lines
---------------

Each line is assumed to be a single object (although newlines do not matter in the game engine)
An object is defined with the following numbers:

    N         - The time when the instruction will be executed
				The time is measured in frames since the beginning of the level
				N > 100 is recommended for the first object
	ID        - The identification number of the instruction
	k         - The number of remaining numbers in this object
	k numbers - The k numbers give the LevelReader additional information about this object

The game engine assumes that the N's are in increasing order.
Any other order will cause the level to behave strangely.

