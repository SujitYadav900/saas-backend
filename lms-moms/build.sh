mvn clean
mvn install -DskipTests
cd target
mv MOMSBELIEF-0.0.1-SNAPSHOT.war mblms.war
rm -rf /home/sujit/Desktop/mblms.war
cp mblms.war /home/sujit/Desktop/
