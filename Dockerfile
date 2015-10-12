FROM misumirize/android-sdk

RUN apt-get update && \
    apt-get install -y haproxy && \
    apt-get clean

COPY haproxy.cfg /etc/haproxy/haproxy.cfg

ENV PROJECT /project

RUN mkdir $PROJECT
WORKDIR $PROJECT

COPY . $PROJECT

RUN echo "sdk.dir=$ANDROID_HOME" > local.properties

CMD /etc/init.d/haproxy start && ./gradlew connectedAndroidTest
