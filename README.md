# keep_console

copy and rename file keep_console_example.properties to keep_console.properties

build application command:
```bash
mvn clean compile assembly:single
```

run application:
```bash
java -jar target/maven-keep-tКуest-jar-with-dependencies.jar --list
```

run tests:
```bash
mvn test
```

# env variables
| name | description | type | is required | default |
| ------ | ------ | ------ | ------ | ------ |
| db.sqllite.url | sqllite url | string | + | jdbc:sqlite:keep.db |