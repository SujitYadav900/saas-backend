mvn clean
echo "Clean Completed"
mvn install -DskipTests
sudo docker build -t 9873260761/springboot-microservices:wabuissness .
sudo docker push 9873260761/springboot-microservices:wabuissness
