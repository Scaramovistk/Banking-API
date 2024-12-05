#!/bin/sh

# @$1 .env file 

# Ensure the .env file exists
touch $1

# Remove existing APPLICATION_IP and APPLICATION_URL entries
file=$(sed '/^APPLICATION_IP/d;/^APPLICATION_URL/d' $1)

cd "$(dirname "$1")"
file_dir="$(pwd)"

echo "$file" > $file_dir/.env
printf "APPLICATION_IP=$(hostname -I | awk '{print $1}')\n" >> $file_dir/.env
printf "APPLICATION_URL=https://$(hostname -I | awk '{print $1}')\n" >> $file_dir/.env