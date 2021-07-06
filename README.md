# warehouse-service
warehouse-service-demo

A shop in London has 2 million IoT tracking devices in the warehouse for sale, of which half needs configured to meet UK standards. A configured device will have a status "READY" and an ideal temperature between (-25'C to 85'C).

The configuration process requires a given IoT device to be associated with a SIM (Subscriber Identification Module) card. The SIM card holds information such as:
•    SIM ID - uniquely identify the SIM card.
•    Operator code – uniquely identify a mobile operator
•    Country – country name, e.g. Italy
•    Status – devices status can be Active, waiting for activation, Blocked or Deactivated.
 
The shop can sell a device only if it meets the UK government's industry standard.



Task:
Using the Java programming language, develop a REST API that 
a.    Returns all devices in the warehouse that are waiting for activation. 
        
   /warehouse/devices?status=WAITING_FOR_ACTIVATION
   
b.    Management endpoints that enable the shop manager to remove or update a device configuration status.
    
    /warehouse/devices
    /warehouse/sim
    /warehouse/configure

c.    Returns an ordered result of devices available for sale.

    /warehouse/devices/{status}

d.    Expected response format should be in JSON.

•    Tech Stack:

o    Spring boot,
o    Java 8,
o    h2 in memory DB,
o    Spring boot test


•    Build: maven

•    Health: spring actuator

•    Deployment: embedded tomcat 

Build command: mvn clean install

Specification: Open API 3.0

    WarehouseManagement.yaml

FAQ

How build 

 mvn clean install
 
 How to run the app 
 
 java -jar warehouse-service-0.0.1-SNAPSHOT.jar
 
 How to access the service 
 
 start the service using above command 
 
 By default, app is running on localhost :8080 port
 
 go https://editor.swagger.io/ 
 
 copy contents WarehouseManagement.yaml and paste in the editor
 
 use the try option in the editor to call the service   or alternatively import the specification into postman 
 
 Order of execution 
 
 /warehouse/devices/load end point will allow you to load the initial data 
 
or create the data by calling the service 

 1)  create new device 
 
/warehouse/devices POST 
 
  2) create sim 
 
  /warehouse/devices POST
 
 2) configure 

  /warehouse/devices/configure
  
  Details can be found in the specification provided 
  
  =======================================
  Improvement area
  
  Security framework needs to add 
  application metrics (telemetry) needed
  log format needs to be standardized
  business validations 
