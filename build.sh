cd config-server
chmod +x mvnw
./mvnw install
cd ..

cd eureka-server
chmod +x mvnw
./mvnw install
cd ..

cd product-catalog
chmod +x mvnw
./mvnw install
cd ..

cd order-management
chmod +x mvnw
./mvnw install
cd ..

cd zuul-gateway
chmod +x mvnw
./mvnw install
cd ..