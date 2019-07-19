#!/bin/bash
if [ "$CIRCLE_BRANCH" = "master" ] ; then
  STAGE=Prod
  VERSION_NAME=$(.circleci/getVersionName.sh)
else
  STAGE=Dev
  VERSION_NAME=$(.circleci/getVersionName.sh --dev)
fi
echo "export STAGE=$STAGE"
echo "export VERSION_NAME=\"$VERSION_NAME\""
echo "export BUILD_TYPE_LOWER=\"$(tr '[:upper:]' '[:lower:]' <<< ${BUILD_TYPE:0:1})${BUILD_TYPE:1}\""
echo "export STAGE_LOWER=\"$(tr '[:upper:]' '[:lower:]' <<< ${STAGE:0:1})${STAGE:1}\""

echo "$ENCODED_KEYSTORE" | base64 --decode > ${HOME}/code/keystore.jks
KEYSTORE=${HOME}/code/keystore.jks

echo "export SIGN_CONFIG=\"-PgourmetKeyStore=$KEYSTORE\
 -PgourmetStorePassword=$KEYSTORE_PASSWORD\
 -PgourmetKeyName=$KEY_NAME\
 -PgourmetKeyPassword=$KEYSTORE_PASSWORD\
 -PgourmetAppcenterId=$APPCENTER_ID\
 -PgourmetAppcenterDevId=$APPCENTER_DEV_ID\""
