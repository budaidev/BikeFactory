# BikeFactory

This is the repository for the DSS coding challenge 2020.

To build and run use the following commands:

docker build --build-arg JAR_FILE=build/libs/*.jar -t budai/bikefactory .

docker run -p 8080:8080 budai/bikefactory

Alternatively local build is possible with the following command:

./gradlew bootRun

The application starts a springboot server. 
You can calculate the optimized ordering by calling the /optimal-ordering (http://localhost:8080/optimal-ordering) endpoint with a post call and add the file in the body as file paramater.

The result is given back in json format as a response, or you can find the saved csv version in the sample_output folder.

The application is easily scalable since it does not store any state.
