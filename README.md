DoReFindMi
==========

This is a game for [Rainbow HAT for Android Things][product] in which the aim is to find the sequence of the ABC buttons to press, which if followed in correct order, will play out the "do-re-mi" melody. The player's seek time is measured for an element of competitiveness.

<img src="https://pbs.twimg.com/media/C2JGfXlUkAA1Xaf.jpg:large" width="50%" />

Pre-requisites
--------------
- Android Things compatible board
- Android Studio 2.2+
- [Rainbow HAT for Android Things][product]

Game flow and rules
-------------------
- The game starts when the 3 step counter reaches zero and game timer starts
- The player needs to guess the order of the 8-step order of the  A, B, C buttons
- When pressed in correct order, the buzzer plays the “do-re-mi” melody, and the led strip lights up the leds one by one, from left to right
- When an incorrect button is pressed a low-frequency sound is played out, the led lights reset and the player has to guess the order from the start
- The round is won when the player completes the sentence after which the alphanumeric display displays “WON!” followed by the time it took the player to win
- A game is restarted by pressing on the buttons again, when a new sequence of steps is generated, and next players can attempt to complete their rounds in fastest time

Dependencies and architecture
-----------------------------
The game makes use of the following components of [Rainbow HAT][product]:
- Buttons
- Gpio Leds
- Piezo Speaker
- Apa102 Led Strip
- Alphanumeric Display

Classes which provide access to these components implement `things.supplier.*Supplier` adapter interfaces and contain minimal logic themselves to allow replacing them with other compatible devices, or a subset thereof, or stubs in tests. There are unit tests which do just that in the test source set. The logic of interaction with the devices is implemented in their respective `things.controller.*Controller` classes. A Game contains of rounds which are initiated and announced when completed by `GameController` class, and Player’s \*moves/guesses\* are validated by `Game` class.

Build and install
=================
In Android Studio, click on the "Run" button.
If you prefer to run on the command line, type
```bash
./gradlew installDebug
adb shell am start com.tomaszrykala.dorefindmi/.ui.DoReFindMiActivity
```

Schematics
----------
No idea, I’ve used [Rainbow HAT][product] (contributions welcome).

Contributions
-------------
As above ;-)

Next steps
----------
At the moment there are no hints as to what buttons should players press, akin to the popular game [Simon][simon] (I genuinely did not know of it until a friend told me this project is like it). This should probably be added, because it can be argued that currently winning a game is based on pure luck, with just a little of short term memory requirement :-)

Credits
-------
These excellent blogs helped to get this project off the ground:
- [Testing Android Things - IoT meets Java][cr-1-testing-1]
- [Testing Android Things – Unit & Vendor tests][cr-2-testing-2]
- [The quick and simple guide to Android Things][cr-3-simple-guide]
- [Getting Started with Rainbow HAT][cr-4-getting-started]

# License

    Copyright 2017 Tomasz Rykała

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


[product]: https://shop.pimoroni.com/products/rainbow-hat-for-android-things
[simon]: https://en.wikipedia.org/wiki/Simon_(game)
[cr-1-testing-1]: https://www.novoda.com/blog/testing-android-things/
[cr-2-testing-2]: http://blog.blundellapps.co.uk/testing-android-things-iot-meets-java/
[cr-3-simple-guide]: https://medium.com/@mojroid/the-quick-and-simple-guide-to-android-things-6592636e772f#.y9ti96z7p
[cr-4-getting-started]: https://learn.pimoroni.com/tutorial/sandyj/getting-started-with-rainbow-hat-in-python


