team20
======

##SimCity201 Project Repository 

###Team Information
  + Name: Tam Henry Le Nguyen
  	+ USC Email: tamnguye@usc.edu
  	+ USC ID: 6154285255

  + Name: Kristi Hupka
  	+ USC Email: hupka@usc.edu
  	+ USC ID: 4609916936

  + Name: Josh Greenberger
  	+ USC Email: jdgreenb@usc.edu
  	+ USC ID: 

  + Name: Carmen Tan
  	+ USC Email: carmiele@usc.edu
  	+ USC ID: 1542023032

  + Name: Nishant Srikanthan (Team Leader)
  	+ USC Email: srikanth@usc.edu
  	+ USC ID:

###Mentor Information
  + Name: Keith DeRuiter
  	+ USC Email: kderuite@usc.edu

###Design Documents and Interaction Diagrams
  + [View Design Documents and Interaction Diagrams in the Wiki](https://github.com/usc-csci201-fall2013/team20/wiki)

###How to Import File
  + Please have Java and Eclipse installed on your computer.
  + Please clone my repository (use Git Bash if Windows, terminal for Linux or Mac)
    which is "team20.git" in the usc-csci201-fall2013 directory
  + Open Eclipse
  + File -> New -> Java Project(if Java Project isn't visible, Project->Java Project)
  + Uncheck "Use Default Location"
  + Click on Browse Button
  + Navigate to git repository that you cloned earlier (Should be team20)
  + Press Finish
  + Left Click on Project Folder in Package Explorer
  + Go to Add Library
  + Add JUnit
  + Click Next
  + Choose JUnit Library Version 3
  + Click Finish

###Work By Individual (V1)
  + Tam Henry Le Nguyen
  	+ Designed and implemented Person Abstract Class
  	+ Designed and implemented Wealthy Sub Class
  	+ Designed and implemented Deadbeat Sub Class
  	+ Designed and implemented Crook Sub Class
  	+ Designed and implemented Worker Sub Class 
  	+ Designed and implemented Phonebook Reference
  	+ README file
  	+ Implemented Roles system
  	+ Designed and implemented all Housing Agents and Code
  	+ Designed and implemented Maintenance Worker Role
  	+ Designed and implemented Rent paying system with mailbox
  	+ Designed and implemented Housing Phonebook Reference
  	+ Designed and implemented how housing and population would work
  	+ Created Housing Maintenance Interface
  	+ Created the Mainteance Worker & Rent Paying System Test
  	+ Editted and Formatted the Group Wiki
  	+ Designed and implemented Alternative Waiter (Shared Data)
  	+ Designed and implemented Revolving Stand
  	+ Adapted and integrated Kristi's restaurant into the City
  	+ Created and adapted Trace Panel and corresponding print functions (with the right tag's)
  	+ Created Add Person Panel to dynamically figure out what information is necessary to display
  	+ Fix Info Panel to dynamically figure out what information is necessary to display


  + Kristi Hupka
  	+ Rave Mode
  	+ Designed and implemented Person Abstract Class
  	+ Designed and implemented Wealthy Sub Class
  	+ Designed and implemented Deadbeat Sub Class
  	+ Designed and implemented Crook Sub Class
  	+ Designed and implemented Worker Sub Class 
  	+ Designed and implemented Phonebook Reference
  	+ Designed and implemented Time System
  	+ Implemented Roles system
  	+ Designed and implemented all Market Agents and Code
  	+ Designed and implemented Market Customer
  	+ Designed and implemented Market Runner
  	+ Designed and implemented Sales Person
  	+ Designed and implemented UPS Man
  	+ Created Interfaces for all of the above roles
  	+ Created Mocks for all of the above roles
  	+ Created tests for all of the above roles
  	+ Figured out the local image/how to show images
  	+ Created Aplication file
  	+ Changed the Build file for it to work on other computers
  	+ Designed and implemented Restaurant Phonebook Reference
  	+ Integrated Restaurant into City
  	+ Created Design Documents
  	+ A bunch GUI stuff I don't know right now, Kristi fix pls


  + Josh Greenberger
  	+ Rave Mode
  	+ Designed and implemented Person Abstract Class
  	+ Designed and implemented Wealthy Sub Class
  	+ Designed and implemented Deadbeat Sub Class
  	+ Designed and implemented Crook Sub Class
  	+ Designed and implemented Worker Sub Class 
  	+ Designed and implemented Phonebook Reference
  	+ Implemented Roles system
  	+ Designed and implemented how shift times for all roles would work
  	+ Designed the best system for interaction between Person, subclasses, and all of the roles
  	+ Designed and implemented all Bank related Agents and Code
  	+ Designed and implemented Phonebook reference for Bank
  	+ Designed and implemented Banking System (Loans, Account Balances, etc)
  	+ Designed and implemented Bank Customer Role
  	+ Designed and implemented Bank Guard Role
  	+ Designed and implemented Bank Teller Role
  	+ Designed and implemented Loan Officer Role
  	+ Created Interfaces for all of the above roles
  	+ Created Mocks for all of the above roles
  	+ Created tests for all of the above roles
  	+ Josh needs way more credit
  	+
  	+

  + Carmen Tan
  	+Created Design Documents

  + Nishant Srikanthan (Team Leader)
  	+

###How To Run Scenarios (V1)
  + Simply press the Green Play Button in the Tool Bar (ApplicationGui.Java)
  	+ Alternatively, you can right click "ApplicationGui.Java" and hit "Run As -> Java Application"
  + The program will immediately run and go through all the scenario's. In order to easily see each 
  	scenario, go to the "TracePanel" tab. Here you can see the different scenario's running with print statements.
    The General City Print Statements show by default, whereas all the other type of print statements are hidden.
    You can hide the General City Print Statements, and click to show another scenario's print statements
    in order to see the different scenarios running. 
  + *Since we have implemented all the main scenarios, we have not implemented a configuration file under the time constraints.  However all normative scenerios can be tracked via the Trace Panel.*
  + We recommend restarting the application each time.
  + In order to run the test cases, please open the respective directory (market.test, bank.test, restaurant.test, housing.test), right click the test you want to run, and run as JUNIT test.

###Unimplemented/Not Working (V1)
  + There is currently no busing system/roads/cars. Everyone walks.
  + Apartment/Housing Related GUI not finished
  + Only Kristi's restaurant has been integrated
  + Integrated but unimplemented GUI for Bank & Market
  + Crooks/Deadbeats/Non Norm's