FROM misumirize/android-sdk

RUN apt-get update && \
    apt-get install -y haproxy && \
    apt-get clean

COPY haproxy.cfg /etc/haproxy/haproxy.cfg

ENV PROJECT /project

RUN mkdir $PROJECT
WORKDIR $PROJECT

COPY . $PROJECT

RUN echo "sdk.dir=$ANDROID_HOME" > local.properties && \
    wget https://raw.githubusercontent.com/travis-ci/travis-cookbooks/master/community-cookbooks/android-sdk/files/default/android-wait-for-emulator && \
    chmod +x android-wait-for-emulator

CMD /etc/init.d/haproxy start && ./android-wait-for-emulator && ./android-send-menu-key && ./gradlew connectedAndroidTest
