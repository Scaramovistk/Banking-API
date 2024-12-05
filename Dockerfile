FROM debian:bookworm


RUN apt-get update && apt-get upgrade -y && apt-get install -y  \
    git-all \
    curl \
    zsh \
    openjdk-17-jdk \
    openssl \
    maven


WORKDIR /home


RUN sh -c "$(curl -fsSL https://raw.github.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"


COPY utils/entrypoint.sh /home/entrypoint.sh


RUN chmod +x /home/entrypoint.sh


EXPOSE 8443


ENTRYPOINT ["/home/entrypoint.sh"]

