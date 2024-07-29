A Java-based program with GUI that allows creation of animals and adding them to a visual race according to the animal type(terrestrial, marine and aerial). Each animal has unique characteristics such as speed, energy required to move one meter, name, image and more.

Possible actions to perform:
1. Add a competition of the following types(only one competition per type is possible): land, water or air.
   Aerial competition can have up to 5 participants in it, while marine competitions can hav up to 4 participants and terrestrial competitions does not have limitations on amount of participants.
2. Adding a new animal - there are several nimals to choose from, such as eagle, dolphin, dog, alligator and more.
    When adding an animal you will be requested to enter details for the animal and to choose the type of competition he'll participate in.
    A message will show, denying the animal's creation if one of the followings occur:
      a) The animal's type does not fit the competition type(e.g a dog in an aerial competition).
      b) The competition is full(only in aerial and marine competitions).
      c) The competition type that was choosen was not created yet, As stated in section 1.
      d) Entering wronge input type (e.g entering a string in a field for numbers such as "speed").
3. Clearing the competition board of all the animals. Important to mention, this action also close all of the competition types that were created and will require recreating in order to add new animals.
4. Feeding the animals - each animal has energy, amount of energy required for movement and an argument for maximum energy capacity. When feeding the animals, it adds energy to their existing energy to help them keep going (if an animal runs out of energy it will stop moving).
5. Info - opens a table with the information of all the animals created, including animals that have been deleted/removed.
6. Exiting and closing the program.
