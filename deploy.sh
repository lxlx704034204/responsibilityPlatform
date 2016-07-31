#! /bin/sh

TOMCAT_HOME=/home/lipei/java/apache-tomcat-8.0.36
PROJECT_HOME=/home/lipei/projects/repoplatform

mvn clean install -DskipTests
exec "$TOMCAT_HOME/bin/shutdown.sh"
rm -rf $TOMCAT_HOME/webapps/orbit-web
rm -rf $TOMCAT_HOME/webapps/orbit-web.war
cp $PROJECT_HOME/orbit-web/target/orbit-web-1.0-SNAPSHOT.war $TOMCAT_HOME/webapps/orbit-web.war
exec "$TOMCAT_HOME/bin/startup.sh"


