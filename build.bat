cd config-server
call mvnw install
cd ..

cd eureka-server
call mvnw install
cd ..

cd product-catalog
call mvnw install
cd ..

cd order-management
call mvnw install
cd ..

cd zuul-gateway
call mvnw install
cd ..