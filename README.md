Swagger Rest API doc
=======

Swagger Rest API doc add-on

## How to build
    git clone https://github.com/exo-addons/rest-api-doc.git
    cd rest-api-doc
    mvn clean install

## How to deploy
Install the Add-on by creating a local catalog named `local.json` under `$PLATFORM_HOME/addons/` with the following content. This local catalog will be merged with the central one at runtime. Don't forget to modify the downloadUrl.

```
[
    {
	"id": "rest-api-doc-webapp",
	"version": "1.0.x-SNAPSHOT",
	"name": "Swagger REST API Doc",
	"description": "Using Swagger-UI to display REST API Documentation",
	"downloadUrl": "file://PATH/TO/rest-api-doc/bundle/target/rest-api-doc-bundle-1.0.x-SNAPSHOT.zip",
	"vendor": "eXo platform",
	"license": "LGPLv3",
	"supportedDistributions": ["community","enterprise"],
	"supportedApplicationServers": ["tomcat"]
    }
]
```
Then run `./addon(.bat) install rest-api-doc-webapp:1.0.x-SNAPSHOT`.
You should see the add-on zip file in `$PLATFORM_HOME/addons/archives`

### Root Path
    /rest-api-doc-webapp
#### Swagger-UI
    /swagger-ui
##### Description
Access Swagger-UI and the Calendar REST API Documentation

#### swagger.json
    /api/swagger.json
##### Description
Access the swagger.json file if needed. That's what reads Swagger-UI to display the documentation



