# periodic-table-api
Create an API that provides details of elements in the periodic table based on the attached dataset. (periodic_table.json)

The API should provide the following objectives( please refer following objective 1-4 )
        
In addition:
    - Once live, the data is expected to receive periodic minor updates
    - The application won't be tested for scaling
    
The application must be written in Java

## Objective 1

    - Get a list of all elements on the periodic table. Each element should have the following properties:
        - Name
        - Atomic number

## Objective 2

    - Get a filtered list of elements based on any or all of:
	    - Group (e.g '13' for Aluminium)


## Objective 3

    - Get a filtered list of elements based on any or all of:
		  - Period (e.g. '3' for Aluminium)
      
## Objective 4

  	- Get details for an individual element by atomic number. The query should return
	    - Atomic number
		  - Name
		  - Alternative name if one exists, else "none"
		  - Atomic symbol (e.g. Li)
		  - Appearance
		  - Discoverers as an array, empty if unknown
		  - Discovery year as a string. "unknown" if not available
		  - Group (as an integer, 0 if n/a)
      - Period

## Environment 
Language ：Java 11  
Framework：Spring Boot 
Build system：Maven

## How to run each objective(with eclipse)

Please import src as a java maven projects.  

https://github.com/irmk323/periodic-table-api 


For Objective 1. 

1:Select PeriodicTableApiApplication.java under src/main/java/com/example/periodictableapi/PeriodicTableApiApplication.java
2:Right click on the file and select "Run as" and "Java application".
3:Open browser.   
4:Enter http://localhost:8080/all_list in the browser.

recommend to use chrome with extensions like JSONVue

For Objective 2.  

1-3 is the same as Objective 1.
4:Enter http://localhost:8080/group/2 in the browser for example. You can replace and type a number after group/ 

For Objective 3  

1-3 is the same as Objective 1.  
4:Enter http://localhost:8080/period/2 in the browserfor example. You can replace and type a number after period/ 

For Objective 4

1-3 is the same as Objective 1.  
4:Enter http://localhost:8080/individual/2 in the browser.  You can replace and type a number after individual/ 


## Error Handlings

An error will occur in the following cases. 

 - For any invalid URL  (ex: http://localhost:8080/all_li , http://localhost:8080/gro/2 , etc...)  
 - Invalid group number (ex: http://localhost:8080/group/9999) 
 - Invalid period (ex: http://localhost:8080/period/9999)  
 - Invalid atomic_number  (ex: http://localhost:8080/individual/9999)   

