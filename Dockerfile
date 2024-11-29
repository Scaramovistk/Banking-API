FROM debian:bookworm

RUN apt-get update && apt-get upgrade -y && apt-get install -y  \
	git-all \
	curl \
	zsh \
	openjdk-17-jre \
	maven

WORKDIR /home

RUN sh -c "$(curl -fsSL https://raw.github.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"

CMD ["tail", "-f", "/dev/null"]