build:
	mvn package

docker-build:
	docker build -t app .
	docker images

docker-run:
	docker run -td -p 8080:8080 --name appkanghuawu368 app
	docker ps

docker-stop:
	docker stop appkanghuawu368

docker-clean:
	docker rm appkanghuawu368
	docker rmi app