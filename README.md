Readme Asset project

Documentation

To proper work this service you need:

mvn clean install all modules by next order:

<ul>
<li>asset-bom</li>
<li>common-dtos</li>
<li>exception-handler</li>
<li>abstract-service</li>
<li>gateway-service</li>
<li>first-service</li>
<li>second-service</li>
</ul>

For docker-compose you need also change the internal information(porst,environments,service names etc.)

First and second service are main modules.

Both services have regular CRUD for extending.

Second service work with first service throw feign client.

Second-service also have 1 endpoint for requesting first-service.