### building and running
```
javac -d bin $(find src -name '*.java')
```
```
cd bin
jar cfm output.jar ../src/Manifest.txt *
```
```
java -jar output.jar
```


