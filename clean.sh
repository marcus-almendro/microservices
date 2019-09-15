cd config-server
chmod +x mvnw
./mvnw clean
cd ..

cd eureka-server
chmod +x mvnw
./mvnw clean
cd ..

cd product-catalog
chmod +x mvnw
./mvnw clean
cd ..

cd order-management
chmod +x mvnw
./mvnw clean
cd ..

cd zuul-gateway
chmod +x mvnw
./mvnw clean
cd ..