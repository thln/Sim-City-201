# Requirements Summary
 + Driverless
 + Solarpowered(no gas)
 + Accidents are rare
 + Passengers
 + Underground Parking Structures in each building (assumption)
 + Group Leader is always the first passenger in the occupants array (assumption)

# Vehicle Agent
### Data

<pre><code>
int capacity
String type
ArrayList <Person> Occupants
boolean full
Map GPS
int locationIndex //initialized to null
Double change
Enum VehicleState(On, Off)
Enum VehicleEvent(TurnOn, TurnOff)
Boolean private
</code></pre>

### Messages

<pre><code>
msgStartCar()
VehicleEvent = TurnOn

msgEntering(ArrayList <Person> group)
for(Person p: group)
            	occupants.add(p)
if(private)
            	VehicleEvent = TurnOn

msgTakeMeTo(int LocationIndex)
this.LocationIndex = LocationIndex

msgLeaving()
Empty Occupants array
this.LocationIndex = null
if(private)
VehicleEvent = TurnOff
</code></pre>

### Scheduler

<pre><code>
If(VehicleState == On)
if(!occupants.isEmpty())
If(LocationIndex.isNull())
RequestDestination(occupants.get(0))

else if (VehicleState == Off)
Drive(LocationIndex)
            Else If (VehicleState = Off)
                            //still needs to be designed
</code></pre> 

### Actions

<pre><code>
StartCar()
           msgStartCar()
RequestDestination(Person p)
            p.msgWhereTo()
Drive(int location)
            Gui.goToLocation(location)
StopCar()
	msgStopCar()
</code></pre>

# Gui Design

<pre><code>
GoToLocation(int location)
</code></pre> 

# Considerations                                         	
 + What happens when the vehicle is idle
 + When can the vehicle be idle
           
