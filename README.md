# JavaSpring-FinalProject
# Necromancer Workforce Manager

This project is a small backend application made with Java and Spring Boot. The idea of the project is a fantasy HR system for undead employees working in a necromancer company.

The system can store employees, departments, complaints, and reanimation tasks. Every undead employee also has a decay rate that shows their current condition. Reanimation tasks can improve employee condition depending on different factors.

The main purpose of this project was to practice backend development and work with Spring ecosystem tools such as Spring Security, JPA/Hibernate, REST APIs, PostgreSQL, and Docker Compose.

Technologies used:
- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA / Hibernate
- PostgreSQL
- OpenFeign
- Docker Compose

Authentication in the project is session-based and uses Spring Security with JSESSIONID cookies.

## Important Note

The real database password is not uploaded to github.

In application.properties there is a line like: 
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD:PleaseTeacherWriteToMeInTeamsToGetAPassword}

I added this on purpose to avoid exposing real database password in a public repository. If needed, I can provide the actual password separately. Just please write me in Teams!
