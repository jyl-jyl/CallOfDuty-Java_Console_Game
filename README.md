## Call of Duty (Java Console Game)
### CIT590 Programming Languages and Techniques HW10


### Introduction
In the game, you serve as a soldier and your mission is destroying a 10x10 enemy base. You need to destroy all Targets in the base.

<img width="646" alt="Screen Shot 2022-05-16 at 4 03 38 PM" src="https://user-images.githubusercontent.com/97498760/168673243-ce84e954-730b-43cb-9e9c-8505af10d0ad.png">

### How to Play Call of Duty
Before the game begins, the program places all targets randomly in such a way that no buildings are immediately adjacent to each other, either horizontally, vertically, or diagonally. Take a look at the following diagrams for examples of legal and illegal placements. (Notes: Tanks and oil drums are not buildings, so they could be placed directly adjacent to each other, or to buildings.)

<img width="456" alt="Screen Shot 2022-05-16 at 4 05 31 PM" src="https://user-images.githubusercontent.com/97498760/168673505-079434bf-a919-41eb-8508-5c03fb83d90c.png">

The player doesn’t know where the targets are. The base is initially covered by mist, and the initial display of the base to be printed to the console therefore shows a 10x10 array with a ‘.’ (a period) in every location.

The player tries to hit the targets, by indicating a specific row and column number (r,c). The player has two weapons, a “rocket launcher” and a “missile”. An input of “q” will switch the weapon. You can shoot a “rocket launcher” 20 times and shoot a “missile” 3 times. The “rocket launcher” will hit one square at the specific coordinate. However, the “missile” will hit a 3x3 area with the coordinate as its center. The program should display the currently selected weapon.

When a target is hit but not destroyed, the program does not provide any information about what kind of a target was hit. However, when a target is hit and destroyed, the program prints out a message “You have destroyed a xxx.” After each shot, the computer refreshes the base, meaning, it prints the entire base (10x10 array) again.

A target is “destroyed” when every square of the target has been hit. But tanks can withstand two hits! Thus, it takes 6 hits to destroy a headquarter and an armory, 3 for barracks, 2 for tanks and 1 for oil drums.

When an oil drum or a tank is destroyed, it will explode and hit a 5x5 area around it. When an armory is destroyed, it will explode and hit a 6x7 (or 7x6) area around it. And the explosion can spread, i.e., an explosion can trigger another explosion.

<img width="324" alt="Screen Shot 2022-05-16 at 4 08 10 PM" src="https://user-images.githubusercontent.com/97498760/168673966-355b33f4-c43f-4423-b037-fbb0f29c04a6.png">

The object is to destroy all targets with as few shots as possible. But you can only shoot a rocket launcher 20 times and a missile 3 times. If all targets are destroyed before you run out of ammunition, you win. Otherwise, you lose. You can switch weapons. Each time before you shoot, the program tells you how many shots are left.

The program prints out a message indicating whether you have won or lost, and how many shots were required, when the game is over.


