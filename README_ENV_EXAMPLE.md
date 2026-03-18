# Example: How to run with environment variables

# 1. Copy the example env file and edit as needed
cp .env.example .env

# 2. Build and run with Docker Compose (Docker Compose automatically loads .env)
docker compose build
docker compose up

# 3. (Optional) To override variables, edit .env or set them in your CI/CD pipeline

# 4. Example curl request to test the chatbot API:
curl -X POST http://localhost:8080/chat \
  -H "Content-Type: application/json" \
  -d '{"message": "Hello, how are you?"}'

# 5. To clear chat history:
curl -X DELETE http://localhost:8080/chat/historial

# 6. To get chat history:
curl http://localhost:8080/chat/historial

