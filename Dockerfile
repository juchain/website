FROM gitlab.blockshine.net:4567/library/java:8-openjdk

ADD build/libs/blockshine-website.jar /usr/src/website.jar

WORKDIR /usr/src

CMD ["java", "-Duser.timezone=Asia/Shanghai", "-jar", "website.jar"]
