# keep_console

# Install instruction
# 1
Load dependency:
```bash
mvn dependency:resolve
```
# 2
copy and rename file keep_console_example.properties to keep_console.properties
# 3
build application command:
```bash
mvn clean compile assembly:single
```
# 4
run tests:
```bash
mvn test
```
# 5
run application:
```bash
java -jar target/maven-keep-test-jar-with-dependencies.jar --list
```

# env variables
| name | description | type | is required | default |
| ------ | ------ | ------ | ------ | ------ |
| db.sqllite.url | sqllite url | string | + | jdbc:sqlite:keep.db |