$env:JAVA_HOME = "C:\Program Files\Java\jdk-23"
$env:MAVEN_HOME = "C:\tools\apache-maven-3.9.8"
$env:Path = "$env:JAVA_HOME\bin;$env:MAVEN_HOME\bin;$env:Path"
mvn test
