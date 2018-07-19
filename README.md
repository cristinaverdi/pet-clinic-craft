# Isolating the database in your integration tests

Sample project to demonstrate how to isolate the database in your integration tests. The code is based in the Spring PetClinic, a project that aims at demonstrating the implementation of a database-oriented application with Spring-related technologies.

## PetClinic Sample Application

Full description: http://projects.spring.io/spring-petclinic/

### Requirements

The application requirement is for an information system that is accessible through a web browser. The users of the application are employees of the clinic who in the course of their work need to view and manage information regarding the veterinarians, the clients, and their pets. The sample application supports the following:

#### Use Cases

* View a list of veterinarians and their specialties
* View information pertaining to a pet owner
* ~~Update the information pertaining to a pet owner~~
* ~~Add a new pet owner to the system~~
* ~~View information pertaining to a pet~~
* ~~Update the information pertaining to a pet~~
* ~~Add a new pet to the system~~
* ~~View information pertaining to a pet's visitation history~~
* ~~Add information pertaining to a visit to the pet's visitation history~~

### Business Rules

An owner may not have multiple pets with the same case-insensitive name.

## Using this example

### Running tests locally

Start the database server:

```text
cd docker ; docker-compose -f docker-compose-postgres.yml up
```

Create the database:

```text
docker exec -it postgres psql -U postgres
```

```text
> CREATE DATABASE petclinic;
> \q
```

Set the search path to match the database schema:

```text
> SET search_path TO public;
> \q
```

Run the tests from the command line:

```text
./mvnw clean verify
```

### Dropping test schemas

```text
SELECT string_agg(format('DROP SCHEMA %I CASCADE;', nspname), E'\n')
FROM   pg_namespace
WHERE  nspname LIKE 'test_%';
```

## References

* Concurrent test execution in Spring: http://www.baeldung.com/spring-5-concurrent-tests
* Database migration with Flyway and Spring Boot: https://docs.spring.io/spring-boot/docs/current/reference/html/howto-database-initialization.html#howto-execute-flyway-database-migrations-on-startup
