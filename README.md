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
- You can also locate my visual component (as an extra) right on the InteractivePanel of the GUI. The blue sky with a bird and clouds
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
- I have chosen to implement:
Test and design a class that is robust. You must have at least one method that throws a checked exception.  
You must have one test for the case where the exception is expected and another where the exception is not expected.
- The class that is robust is DestinationsManager in the model package (this class was previously named Destinations, now changed the
class/file name to DestinationsManager as this is more appropriate).
- In this robust class, the two methods, getCityFromSummerDestinations and getCityFromWinterDestinations, 
both throw the checked exception, IllegalDestinationException.
- This checked exception is thrown if the country name of the input parameter, Destination, does not match any of the specified country names.
 Otherwise, the checked exception is not thrown. 
- To test this checked exception, the DestinationsManagerTest class contains the tests where:
1) checked exception is expected 2) checked exception is not expected to be thrown. 
- For example, testGetCityCatchExceptionSummer(), testGetCityCatchExceptionWinter(), testGetCityFromIceland() and testGetCityFromSpain() 
will test the above cases with try/catch blocks. 
- I have implemented the try/catch blocks within summerCall and winterCall methods in the TextPanel class of the UI package,
where the two methods (getCityFromSummerDestinations and getCityFromWinterDestinations) are called from the DestinationManager class.

## Phase 4: Task 3
- Note: As this is the final phase of the project, I re-named a few classes (including interfaces) to better suit the context of my project.
- Changes are: FormPanel -> InteractivePanel, FormEvent -> InteractivePanelEvent, FormListener -> PanelListener, StringListener -> TextListener, Toolbar -> TopPanel. Thank you.
 Problem 1: As per note above, the following names changed from: FormListener -> PanelListener and FormPanel -> InteractivePanel.
I have identified there were 6 separate associations to the FormListener(PanelListener) interfaces (these were previously named: 
FormListener, FormListenerAdd, FormListenerEnter, FormListenerLoad, FormListenerRemove, FormListenerSave) within the FormPanel(InteractivePanel) class. 
I realized all of these 6 interfaces essentially specified the same behavior. Thus, I have removed the extra interfaces 
to a single PanelListener interface. This removal would improve the cohesion and eliminated the need of 
separate associations to the 6 separate FormListener interfaces within the InteractivePanel class.

## Phase 4: Task 3
- Problem 2: I have identified a cohesion problem within the MainFrame class with respect to the methods of playAddSound() and playDeleteSound(), 
as these methods can be extracted into a separate class, named Music. This way, the Music class will be responsible (1 class, single responsibility principle) 
for its own methods of the sound from addSound.wav and javadeletesound.wav files saved in the data folder of the project.
These music methods are used for the program's ADD button, Alt+R/Alt+N keys/buttons, and the drop-down menu item (Add country) in the program. 
Therefore, this fix will additionally improve the cohesion of the MainFrame class and the Music class. 

## Phase 4: Task 3
- Problem 3: Within the MainFrame class, I realized that I could move the methods related to appending and outputting strings to the text panel
of the program into the TextPanel class to improve cohesion in the MainFrame class. For instance, some of the fixes that I have implemented
include moving the methods of summerCall, winterCall, and setLoading to the TextPanel class to improve cohesion of the MainFrame class.

## Phase 4: Task 3
- Problem 4: I have identified that there was poor cohesion in the MainFrame class of the UI package with the additional 
responsibility of creating the JMenu and JMenuItem objects within this class. Thus, I have refactored these codes by 
creating a new class called MenuBuilder for JMenu and JMenuItems. This helped to improve the cohesion issue in the original MainFrame class 
by extracting out this additional responsibility from the MainFrame class. 

## Phase 4: Task 3
- Problem 5: First, I resolved duplicate codes from enterKey(), addMethod(), and addCountryWithMenu() inside the MainFrame class, 
and named this addMethodToList(). Then, I identified parts of the addMethodToList() codes that could be moved to the TextPanel class
to resolve a cohesion issue within the MainFrame class (the code fits the responsibility of the TextPanel 
rather than the MainFrame class). This newly created method inside the TextPanel class is called buildingDreamVacation(). 

- Overall, I have refactored codes and improved the cohesion of the TextPanel, InteractivePanel, and the MainFrame classes with numerous fixes. 
    
## Phase 4: UML diagram
- The UML_Design_Diagram.pdf has been added to the root of my project. Thank you! 



