run:
	gradle bootRun

curl-twelvedays-verse:
	curl -v -X GET "localhost:8080/api/twelve-days/verse?verseNumber=1"

curl-twelvedays-verses:
	curl -v -X GET "localhost:8080/api/twelve-days/verses?startVerse=7&endVerse=9"

curl-twelvedays-sing:
	curl -v -X GET "localhost:8080/api/twelve-days/sing"

curl-bracket-checker:
	curl -v -X GET "localhost:8080/api/bracket-checker?expression=%28%5B%7B%7D%5D%29"

curl-minesweeper:
	curl -v "localhost:8080/api/minesweeper-board?rows=%20*%20*%20&rows=%20%20*%20%20&rows=%20%20*%20%20&rows=%20%20%20%20%20"

curl-word-count:
	curl "localhost:8080/api/word-count?phrase=Hello%20World%20Hello%20World"

curl-phone-number:
	curl "localhost:8080/api/phone-number?input=(223)%20456-7890"

clean:
	rm -rf ./classes

compile-one: clean copy-deps
	javac -cp .:libs/\* -d classes src/main/java/dev/naome/probsolv/ProbsolvApplication.java

compile: clean copy-deps
	javac -cp .:libs/\* -d classes src/main/java/dev/naome/probsolv/*

runcp:
	java -cp .:"libs/*":classes dev.naome.probsolv.ProbsolvApplication

copy-deps:
	gradle copyDependencies

wrapper:
	gradle wrapper

install-java17-openjdk:
	sdk install java 17.0.9-tem