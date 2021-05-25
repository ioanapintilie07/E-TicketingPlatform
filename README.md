# E-TicketingPlatform

There are four main classes: Event, Location, Client, and Ticket. <br> <br>
The project particularizes five types of events: Concert, Conference, Exhibition, Fundraiser and Play, all inheriting from class Event. \
Locations can be physical or online. <br> <br>
A client can add or randomly generate an event, check an event's availability, and buy a ticket. In the case of fundraisers, they can also choose to make a donation, see how much 
money a certain fundraiser raised and check whether it has reached its goal. <br> <br>
In the case of concerts, the user can add a concert to the database, filter concerts by artist name, update the number of available tickets, and delete a concert. <br> <br>
Locations and clients can also be manually added or generated into the database, updated (name), or deleted. <br> <br>
All entities can be listed through the main menu. <br> <br>
The audit service logs all actions and queries and their timestmap into the databse. 
