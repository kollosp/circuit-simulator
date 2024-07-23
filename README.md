## Build & Run
compile into the `bin` directory
```
javac -d bin $(find src -name '*.java')
```
pack .jar file
```
cd bin
jar cfm output.jar ../src/Manifest.txt *
```
run
```
java -jar output.jar
```


