# Example Kotlin App Engine / Android
Repository based in this [article](https://medium.com/p/c2db393e576e/edit) and presentations in [Droidcon Vietnam](https://speakerdeck.com/cdsap/kotlin-server-client) and [Droidcon Dubai](https://speakerdeck.com/cdsap/droidcon-dubai-kotlin-server-client)

# What includes?
- Simple client in Android with Kotin
- Server side in App Engine with Kotlin
- Additional module to include uses cases and domain entities

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
