# Camping Application

This is API for camping application done in Java using Spring Boot framework. To run this application you need to have MySQL server installed and clone this repository. 
This application is made for people passionate about camping. For campers to find 
campsites, reserve them and enjoy in their activities. For campsite owners (caterers)
to help them get more visitors.

## Main functionalities

* **Campers**

    They can find, reserve and share campsites with other campers. When their reservations expire they can rate and comment campsites, report caterers for incorrect information.
    As long as caterer do not process reservations, they are able to cancel them.
    
* **Caterers**

    They can add a new campsite, accept and reject reservations.

* **Admin**

    He can block campers when they cancel reservations too many times, block caterers when they are reported by campers for incorrect information about campsites. See statistics about campsites with most comments, reservations and top ratings.