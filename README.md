# swagger-spray-sample

Clone this git repo and use ```sbt run``` to start the Spray Can server.

Uses [swagger-spray](https://github.com/swagger-spray/swagger-spray) which is built using [swagger.io](http://swagger.io/) libs.

Test using http://petstore.swagger.io/ and replace the swagger.json with http://localhost:12345/api-docs/swagger.json

The Swagger UI can be used to send sample requests. In my testing, the Add service fails when called from the Swagger UI but the curl command that it produces is displayed and does work when used from the command line. These issues appear to be [CORS](https://en.wikipedia.org/wiki/Cross-origin_resource_sharing) related.
