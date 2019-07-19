#!/bin/sh
VERSION_FILE="versionfile"
VERSION_NAME=$(cat ${VERSION_FILE} | tr -d '\n\r')

case $1 in
	--dev) printf "${VERSION_NAME}-develop" ;;
	*) printf "${VERSION_NAME}" ;;
esac
