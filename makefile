lrun:
	./mvnw javafx:run

lcompile:
	./mvnw compile

lreset:
	./mvnw clean install

lpermission:
	chmod +x mvnw


wrun:
	mvnw.cmd javafx:run

wcompile:
	mvnw.cmd compile

wreset:
	mvnw.cmd clean install


allmvn:
	mvn clean install
	mvn compile
	mvn javafx:run

