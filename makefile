run:
	./mvnw javafx:run

compile:
	./mvnw compile

reset:
	./mvnw clean install

lpermission:
	chmod +x mvnw


allmvn:
	mvn clean install
	mvn compile
	mvn javafx:run

