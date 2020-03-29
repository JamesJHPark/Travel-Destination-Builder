# Let's Travel Around the Globe!

## Plan your future destinations
Why don't you select a place for your next travel destination in advance?
Pick places that you wish to travel for you future dream vacations! 

The application will allow the user to choose either **summer or winter** season for the next vacation, 
view a list of countries based on the chosen season,
and select a particular country for the upcoming vacation.
In addition, the user will be able to select other countries that the user wishes to visit 
in the future and add these places to a personalized **"dream vacation"** list. 

**This application will allow the user to:**
- Choose a vacation season between summer or winter 
- Select the next travel destination
- Create a personal list of dream vacation


## User Stories
- As a user, I want to be able to choose a season (winter or summer) for my next vacation
- As a user, I want to be able to view a list of countries for my next vacation based on the chosen season
- As a user, I want to be able to select a country from the given list that I wish to visit for my next vacation 
- As a user, I want to be able to view popular cities of the selected country for travel 
- As a user, I want to be able to choose 2 countries that I wish to visit in the future and add these 2 countries to a “dream vacation list”
- As a user, I want to be able to view my customized list of dream vacation
- As a user, I want to be able to save the customized dream vacation list for future viewing 
- As a user, I want to be able to view the previously saved, customized dream vacation list
- As a user, I want to be able to continuously add more countries to the customized dream vacation list
- As a user, I want to be able to start over and create a new dream vacation list if I wish to do so

## Instructions for Grader
- First, when beginning the application, you can press the START Button on top left of the screen.
- Then, you can choose the season either Summer or Winter and type this into Season text field panel and click SUBMIT Button.
- Next, you can choose one of the countries from the provided travel destinations and type the country 
into the Destination text field panel and click SUBMIT Button.
- Now, it's time to create the Dream Vacation list! You can type in any country destination that you wish to add to 
your customized Dream Vacation list into Dream Vacation text field panel.
- You can generate the first required event of "add an X to a Y" by typing in a country in the text field panel of the Dream Vacation, and
press Alt+N on keyboard or click ADD Button. 
Also, you can select the following menu item under Data on top left of the screen: "Add country (typed in Dream Vacation panel) to list."
This will add the typed country in the Dream Vacation text field panel to the Dream Vacation list as well.
- You can generate the second required event related to the required user story by first typing in a country 
from the loaded Dream Vacation list (that was previously saved) into the Dream Vacation text field panel. 
After typing the country into the panel, you can press Alt+R on keyboard or click on "Alt+R to remove" Button.
This event will allow the user to remove a specific destination country from the Dream Vacation list.    
- You can locate my audio component by clicking on the ADD Button, pressing Alt+N or Alt+R on keyboard. 
Also, you can locate it by selecting the menu item under Data on top left of the screen: "Add country (typed in Dream Vacation panel) to list."
All these events will produce the audio sound. 
- You can also locate my visual component (as an extra) right on the form panel of the GUI. The blue sky with a bird and clouds
fit the theme of travelling nicely. 
- You can save the state of my application by clicking on the SAVE LIST Button after you have created a Dream Vacation list.
- You can also save the state of my application by selecting the following menu item under Data on top left of the screen: Save Data.
- You can close the program by selecting the menu item Exit under Data. 
- If exited and re-launched the program again, you can press START button and click on LOAD LIST button as instructed to view
your saved customized Dream Vacation list.
- Thus, you can reload the state of my application by clicking on the LOAD LIST Button. 
- You can also reload the state of my application by selecting the menu item under Data on top left of the screen: Load Data.
- This concludes my phase 3 and have fulfilled all the requirements of the project so far. Thank you! 


## Phase 4: Task 2
- I have made designed and tested class DestinationsManager robust (this class previously was named Destinations, now changed to 
DestinationsManager) and I have changed the signature of the following methods in this class,
getCityFromSummerDestinations and getCityFromWinterDestinations, to throw the checked exception of IllegalDestinationException, 
if the input of the method parameter is not valid. 
- I have tested these methods inside the DestinationsManagerTest with try/catch blocks for both cases where 
1) exception is expected 2) exception is not expected, and implemented the try/catch blocks in the MainFrame of the UI package 
that called these two methods from the DestinationManager class.

## Phase 4: Task 3
- I have identified that there was poor cohesion in the MainFrame class of the UI package with respect to JMenu and JMenuItem fields/objects
in this class. Thus, I have refactored the codes by creating a new class called MenuBuilder for JMenu and JMenuItem to improve the 
cohesion issue in the original MainFrame class. In the MenuBuilder class, I was able to eliminate the need for getter methods in the
by creating methods that take in action listener as an argument. In the MainFrame class, I have implemented
to reflect this change by setting this.menuBuilder inside the methods of handleExport, handleImport, handleAddCountry,and handleShowItem.

## Phase 4: Task 3
- I have identified a cohesion problem within the MainFrame class with respect to the methods of playAddSound() and playDeleteSound(), as these methods
can be extracted into a separate class, named Music. This way, the Music class will be responsible (1 class, single responsibility principle)
for the sounds of addSound.wav and deleteSound.wav files in the data folder of the project. 

## Phase 4: Task 3
- I have identified an issue with cohesion in the MainFrame class with respect to the Single Responsibility Principle. 
First, I resolved duplicate codes by extracting a method from enterKey(), addMethod(), and addCountryWithMenu() inside the MainFrame class, 
and called the newly created method, addMethodToList() inside those methods. Then, I identified 
the cohesion issue within this method, addMethodToList(), of implementing TextPanel.setText with text description of building DreamVacation list to user. 
I realized that since I already had the TextPanel class separately in the UI package,
this particular code of addMethodToList() could be replaced with a call to the newly created method in the TextPanel class, 
buildingDreamVacation(), to resolve a cohesion issue of the MainFrame class. 

## Phase 4: Task 3
- Inside the MainFrame class, the method setLoading() is called in loadMethod() and setLoading() methods of MainFrame Class. 
This is another cohesion problem as setLoading() implements codes that should entirely be in its own TextPanel class. Therefore,
I resolved this by creating a method inside the TextPanel class and replaced the setLoading() call in the body of both
setLoading() and loadMethod() by calling this new method from the TextPanel class.  

## Phase 4: Task 3
- Within the MainFrame class, I replaced the textPanel.appendText of text body (duplicate codes) from the methods, summerCall() and winterCall(), with method calls to the new 
 methods that I created, textForDreamVacation() as well as showCountriesWithWinterSeason
 and showCountriesWithSummerSeason methods inside the TextPanel class. This is another fix of the cohesion problems of the MainFrame class. 


## Phase 4: Task 3
- I have identified that there was tight coupling in the FormPanel class of the UI package associated with multiple FormListener interfaces (FormListener, FormListenerAdd,
FormListenerEnter, FormListenerLoad, FormListenerRemove, FormListenerSave). These interfaces could be dealt with by simply creating
a single FormListener interface and set this as the sole FormListener in my FormPanel class to eliminate the need of multiple associations 
of FormListener interfaces within FormPanel class.

## Phase 4: Task 3
- I also removed the Singleton class from the project as this was not necessary in the application of my program, and this would
reduce coupling between my classes (originally, this would have created coupling between the MainFrame class and TextPanel class as
I passed the singleton instance of DreamVacation and list of destinations in these two classes in prior commits - but I decided
to implement adding/removing X to/from Y (destination(s) to the DreamVacation list) in the MainFrame class alone).
 

