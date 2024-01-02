lrun:
	./mvnw javafx:run


wrun:
	mvnw.cmd javafx:run


lreset:
	./mvnw clean install


wreset:
	mvnw.cmd clean install


lpermission:
	chmod +x mvnw


allmvn:
	mvn clean install
	mvn compile
	mvn javafx:run

