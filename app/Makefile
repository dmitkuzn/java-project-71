.DEFAULT_GOAL := build-run

run-dist:
	./build/install/app/bin/app -h

report:
	./gradlew jacocoTestReport

rebuild:
	./gradlew clean
	./gradlew build
	./gradlew jacocoTestReport

.PHONY: build
