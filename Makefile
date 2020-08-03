.PHONY: build

build:
	./gradlew clean build
	docker build -t demo-product-api .

ecr_login:
	aws ecr get-login --no-include-email | sh

push: build
	docker tag demo-product-api sarayutorion/demo-product-api:latest
	docker push sarayutorion/demo-product-api:latest

init:
	git config core.hooksPath .githooks
