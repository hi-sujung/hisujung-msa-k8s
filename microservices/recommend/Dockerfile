# Create a new build stage from a base image
FROM python:3.12-slim

# Change working directory
WORKDIR /app

# Copy necessary files and directories
COPY requirements.txt .
COPY app.py .

# Execute build commands
RUN apt-get update && apt-get install -y build-essential python3-dev default-jdk
RUN pip install -r requirements.txt

# Describe which ports your application is listening on
EXPOSE 5000

# Specify default executable
ENTRYPOINT [ "flask", "run", "--host=0.0.0.0" ]
