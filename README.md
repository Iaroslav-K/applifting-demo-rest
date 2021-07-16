# Applifting demo REST application

## Run

run `docker-compose up` (don't forget to build the application - `./gradle build`)

## Tests

run `./gradle build`

## API configuration

### paths:

- GET `"/"` - liveness probe (you don't need access-toke)
- GET `/api/v1/monitoring-results` (based on access-token)
    - `/by-monitored-endpoint/{monitoredEndpointId}` - get last 10 results for the given monitored endpoint
- `/api/v1/monitored-endpoints`  (based on access-token)
    - GET ` ` - get all monitored endpoint
    - GET `{id}` - get monitored endpoint by id
    - DELETE `{id}` - delete monitored endpoint by id
    - POST ` ` - create monitored endpoint (requires body)
      Example: `{
      "monitoredEndpointId": 1,
      "name": "google",
      "url": "https://www.google.com/",
      "dateOfCreation": null,
      "dateOfLastCheck": null,
      "monitoredInterval": 10 }`
    - PUT ` ` - update monitored endpoint (requires body)
      Example: `{
      "monitoredEndpointId": 1,
      "name": "google",
      "url": "https://www.google.com/",
      "dateOfCreation": null,
      "dateOfLastCheck": null,
      "monitoredInterval": 10 }`
      
### access token

Add required access token as a header variable. Example: `"Authentication": 93f39e2f-80de-4033-99ee-249d92736a25`

I used an Insomnia.
