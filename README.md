# registerup.sii.pl - application use user registration for conferences

<table>
<tr>
<td>
A project to develop best app for participant registration for conferences</td>
</tr>
</table>

## Table of contents

* [About the components](#about-the-components)
* [Developing locally](#developing-locally)
* [Setup](#setup)
* [Deploying the app](#deploying-the-app)
* [Contact us](#contact-us)

## About the components

This app is developed with [Java](https://www.java.com/), uses [Spring](https://spring.io/) framework, for database
engine we decided to use [MongoDb](https://www.mongodb.com/) version 5.0.

## Developing locally

If you would like to develop locally we recommend using docker, it is used for starting database server.

### Setup

You need Docker and docker-compose which is a part of Docker desktop. Clone this repo and start the database server
configuration We do this with docker-compose, run docker-compose up then you can access database from 
http://localhost:27017. Also, database credentials are needed, report to our team for the appropriate database data
or create your own credentials based on **.env.example** which contains sample credentials.

### Deploying the app

The **registerup.sii.pl** application server is created from the docker-compose.yml file (the same file is implemented by the 
database server). The app uses [Jacoco plugin](https://www.jacoco.org/jacoco/) and [Jenkins](https://www.jenkins.io/) automation server to develop it even better
This application is under development, please wait for more details.

### Contact us

Use this [Email](mailto:email@example.com)