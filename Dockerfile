FROM public.ecr.aws/docker/library/maven:3-amazoncorretto-21 AS build

WORKDIR /app

COPY . .

RUN mvn clean package

FROM public.ecr.aws/amazoncorretto/amazoncorretto:21

WORKDIR /app

COPY --from=build /app/target/libms-0.0.1-SNAPSHOT.jar libms.jar

EXPOSE 8080

CMD ["java", "-jar", "libms.jar"]