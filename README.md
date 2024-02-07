# Current Weather Service
Current Weather Service is a flexible and maintainable application that enables users to effortlessly retrieve real-time weather data based on city name, zip code, or coordinates.

## Table of Contents
- [Introduction](#introduction)
- [How to Run](#how-to-run)

## Introduction
The service leveraging the OpenWeatherMap API and designed with extensibility in mind, facilitating easy integration of new features and testing.

**Key Feature:**
Retrieving current weather condition base on following data
- A city name.
- A zip code.
- coordinates

## How to Run

- step 1
```maven
./mvnw clean install
```
- step 2
```bash
java -jar target/weather-service-0.0.1-SNAPSHOT.jar 
```
- step 3

sample calls:
```http
curl --location 'localhost:8080/weather?lat=44.34&lon=10.99'
```

```http
curl --location 'localhost:8080/weather/city/London'
```

```http
curl --location 'localhost:8080/weather/zipcode/94040'
```