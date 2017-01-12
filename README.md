# MovieFinder
For this project we are using the following API http://omdbapi.com/ please read it

##Consuming the API 
We are using the library retrofit to consume the API, please read the following tutorial 
https://guides.codepath.com/android/Consuming-APIs-with-Retrofit

under the package /com/moviefinder/moviefinder/api we have our own implementation of the classes need it,
*[APIProvider.class](https://github.com/oscarg798/MovieFinder/blob/master/app/src/main/java/moviefinder/com/moviefinder/api/ApiProvider.java)* and
*[IAPIEndpoints.class](https://github.com/oscarg798/MovieFinder/blob/master/app/src/main/java/moviefinder/com/moviefinder/api/IAPIEndpoints.java)*

We have an example of how consuming the api using those classes on 
*[MainActivity.class] (https://github.com/oscarg798/MovieFinder/blob/master/app/src/main/java/moviefinder/com/moviefinder/MainActivity.java)*
