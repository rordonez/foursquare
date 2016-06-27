### About the implementation
To implement the solution to this exercise, I have used Spring Boot since it
provides a simplification for web development as well as advanced features
of Spring framework and modules.

I have implemented a REST API with one entity /places and one operation to get
all the recommended places.

For simplicity, this project uses two layers: one layer for web (controller) and
another for the service layer which contains a Http client (RestTemplate) to
call the Foursquare API. Ideally, it should be necessary to have a third layer
to abstract the application of the details of Foursquare DTOs as well as low
level error handling. It should be also necessary to add a generic component for
Request/Response bindings and connections. Both layers contains tests (unit and
integration).

### The Controller

This project contains only one controller with one method _getPlacesBy_. This
method defines an operation (GET) over a _places_ entity. By default, it will
return a JSON array with a list of places.

`GET http://localhost:8080/places/London`

will return:

```json
[
  {
    "name": "Hampstead Heath",
    "url": "http://www.cityoflondon.gov.uk/hampstead",
    "rating": 9.7
  },
  {
    "name": "Hyde Park",
    "url": "http://www.royalparks.org.uk/parks/hyde-park",
    "rating": 9.7
  },
  ...
]
```

Every item in this list will contain a _name_ for the recommended place, a _url_
if exists and a _rating_.

There is also defined a ControllerAdvice class to handle errors that can arise.
_HandlerError_ class centralizes all the errors. It is also possible to define
more customized error handling and error types, such as Vnd standard. The format
for the error response is the following:

```json
{
  "status": 400,
  "message": "Bad Request"
}
```

This REST API does not provide links to other resources using HATEOAS, but it
could be an interesting feature for the project.

### Execution

If you are using Maven, you can run the application using `mvn spring-boot:run`.
Or you can build the JAR file with `mvn clean package` and run the JAR by typing:

`java -jar target/foursquare-0.0.1-SNAPSHOT.jar`

NOTE: This project contains _clientId_ and _clientSecret keys to access to
Foursquare API. This information is not usually stored in Git repositories.
They are usually configured as placeholders. Again, for simplicity, they are
included within the repository.
