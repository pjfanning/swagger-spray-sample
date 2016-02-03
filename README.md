# swagger-spray-sample

Clone this git repo and use ```sbt run``` to start the Spray Can server.

Test using http://petstore.swagger.io/ and replace the swagger.json with http://localhost:12345/api-docs/swagger.json

The Swagger UI can be used to send sample requests. In my testing, the Add service fails when called from the Swagger but the curl command that it produces does run when used from the command line.
