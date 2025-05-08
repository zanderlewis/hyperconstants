#!/bin/zsh

# Export environment variables if needed
export GPG_TTY=$(tty)

# Run the Maven deploy command
mvn clean deploy -DskipTests=true
