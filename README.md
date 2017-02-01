<a href="http://predixdev.github.io/asset-bootstrap/javadocs/index.html" target="_blank" >
	<img height="50px" width="100px" src="images/javadoc.png" alt="view javadoc"></a>
&nbsp;
<a href="http://predixdev.github.io/asset-bootstrap" target="_blank">
	<img height="50px" width="100px" src="images/pages.jpg" alt="view github pages">
</a>

Asset Bootstrap SDK
================================

Welcome to Asset Bootstrap SDK. This is a SDK in Java for calling the Predix Asset API to perform CRUD operation on [Asset Domain Objects](https://www.predix.io/docs/#zChUPu1U). It has built in mechanism to perform authenticated calls to Asset API. It can be used as a dependency to other java back-end applications for performing CRUD operations on Asset Domain Objects.
For further information please view the [Asset Tutorial](https://www.predix.io/resources/tutorials/journey.html#1709).

##Download Asset Bootstrap SDK

```
git clone https://github.com/PredixDev/asset-bootstrap.git
```
##Build it

1. From the command line, go the the project directory.
2. Run as

```
  mvn clean package
```
##Run integration tests

1. Edit config/application.properties as follows. For further information on configuring Predix Asset service, please refer to the [Asset Tutorial](https://www.predix.io/resources/tutorials/journey.html#1709).

```
 # e.g. predix.oauth.issuerId=https://36492c1e-657c-4377-ac51-add963552460.predix-uaa.cloud.com/oauth/token

 predix.oauth.issuerId=put.your.uaa.issuerId.here

 #you may put client:secret as unencoded cleartext by setting predix.oauth.clientIdEncode=true
 predix.oauth.clientIdEncode=false
 predix.oauth.clientId=you.should.base64encode(put.your.clientId:put.your.clientSecret separated by a colon)  


 # e.g. predix.asset.restHost=predix-asset.cloud.com

 predix.asset.restHost=put.your.asset.service.instance.name.hostname.here

 predix.asset.zoneid=put.your.asset.zoneid

 # e.g. asset.service.base.url=https://predix-asset.cloud.com

 asset.service.base.url=put.your.asset.service.base.url
 
 ```
 2. From the command line, go the the project directory.
 3. Run as 

``` 
 mvn clean install.
```
##How to include as a dependency
1. Modify the pom.xml as follows in the SDK for which you would like to have asset-bootstrap as a dependency.
2. Add the following under the ```<dependencies>``` section with the latest version:
```
<dependency>
     <groupId>com.ge.predix.solsvc</groupId>
     <artifactId>asset-bootstrap-client</artifactId>
     <version>${asset-bootstrap.version}</version>
</dependency>
 ```
 
##Tech Stack

 - Spring
 - SpringTest
 - Maven
 
##More Details
 
 [Asset Tutorial](https://www.predix.io/resources/tutorials/journey.html#1709)

[![Analytics](https://ga-beacon.appspot.com/UA-82773213-1/asset-bootstrap/readme?pixel)](https://github.com/PredixDev)
