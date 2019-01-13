# Example Kotlin App Engine / Android

[Second Part](https://medium.com/@inyaki_mwc/android-and-app-engine-with-kotlin-2nd-part-96e5472cfd7a)

[First Part](https://medium.com/p/c2db393e576e)

# What includes?
- Simple client in Android with Kotin
- Server side in App Engine with Kotlin
- Additional module to include uses cases, implementations and domain entities

# Usage

### App Engine

#### Local conf App Engine

- Install Google Cloud
`curl https://sdk.cloud.google.com | bash`

- Install Google App Engine Component
`gcloud components install app-engine-java`

- Deploy local instance:
`./gradlew appengineRun`

- Install Cloud Datastore Emulator
`gcloud components install cloud-datastore-emulator`

- Run Datastore Emulator
`gcloud beta emulators datastore start`

#### Deploy App Engine

- Create project Google Cloud

- `gcloud auth login`

- `gcloud config set project PROJECT_ID`

- `./gradlew appengineDeploy`

### Android

For the Android App there are two flavours:

- `local`: it uses the local instance of App Engine: http://10.0.2.2:8080

- `gae`: it uses the remote instance of App Engine: https://PROJECT_ID.appspot.com
