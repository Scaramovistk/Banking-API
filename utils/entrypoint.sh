#!/bin/sh

cd /home/current-account-api

openssl req -x509 -nodes -days 365 -newkey rsa:2048 \
    -keyout ssl/private/selfsigned.key \
    -out ssl/certs/selfsigned.crt \
    -subj "/C=BE/L=Brussels/OU=IT/O=Capgemini/CN=localhost/"

openssl pkcs12 -export -out ssl/private/selfsigned.p12 -inkey ssl/private/selfsigned.key -in ssl/certs/selfsigned.crt -name selfsigned -passout pass:

mvn clean package
mvn spring-boot:run

tail -f /dev/null