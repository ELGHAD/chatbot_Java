# Chatbot API

A simple **Spring Boot REST API** for a chatbot that uses OpenAI's GPT-4 model to generate intelligent responses. The application provides endpoints for sending messages and receiving AI-powered replies.

## 📋 Features

- **REST API** endpoints for chat interactions
- **OpenAI Integration** using GPT-4o-mini model
- **CORS enabled** for cross-origin requests from frontend applications
- **Spring Boot 3.4.12** with Java 17
- **DevTools** for automatic reload during development
- **Logging** configured at DEBUG level for detailed output
- **Health check** endpoint (`/api/chat/ping`)

## 🛠️ Tech Stack

- **Framework**: Spring Boot 3.4.12
- **Language**: Java 17
- **Build Tool**: Maven
- **API Client**: RestTemplate
- **External Service**: OpenAI API (GPT-4o-mini)

## 📦 Prerequisites

Before running this project, ensure you have:

- **Java 17** or higher installed
- **Maven 3.8+** installed
- **OpenAI API Key** (get one from [platform.openai.com](https://platform.openai.com))

## 🚀 Getting Started

### 1. Clone or download the project

```bash
cd chatbot
```

### 2. Configure OpenAI API Key

Update the `src/main/resources/application.properties` file with your OpenAI API key:

```properties
openai.api.key=your_actual_api_key_here
openai.model=gpt-4o-mini
server.port=8080
```

⚠️ **Security Note**: Never commit your API key to version control. Consider using environment variables or a `.env` file (not included in git).

### 3. Build the project

```bash
mvn clean install
```

### 4. Run the application

```bash
mvn spring-boot:run
```

The API will start on `http://localhost:8080`

## 📡 API Endpoints

### POST `/api/chat`

Send a message to the chatbot and receive an AI-powered response.

**Request Body:**

```json
{
  "message": "What is Spring Boot?"
}
```

**Response:**

```json
{
  "reply": "Spring Boot is a framework that simplifies the creation of Spring applications..."
}
```

**Example using cURL:**

```bash
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message":"Hello, how are you?"}'
```

### GET `/api/chat/ping`

Health check endpoint to verify the API is running.

**Response:**

```
Chatbot API is up ✅
```

**Example:**

```bash
curl http://localhost:8080/api/chat/ping
```

## 📁 Project Structure

```
chatbot/
├── src/
│   ├── main/
│   │   ├── java/com/example/chatbot/
│   │   │   ├── ChatbotApplication.java          # Main application class
│   │   │   ├── config/
│   │   │   │   └── RestTemplateConfig.java      # RestTemplate configuration
│   │   │   ├── controller/
│   │   │   │   └── ChatController.java          # REST endpoints
│   │   │   ├── dto/
│   │   │   │   ├── ChatRequest.java             # Request DTO
│   │   │   │   └── ChatResponse.java            # Response DTO
│   │   │   └── service/
│   │   │       └── ChatService.java             # Business logic (OpenAI integration)
│   │   └── resources/
│   │       ├── application.properties           # Configuration
│   │       ├── application.yaml                 # Alternative YAML config
│   │       └── static/
│   │           └── index.html                   # Frontend (if needed)
│   └── test/
│       └── java/com/example/chatbot/
│           └── ChatbotApplicationTests.java     # Unit tests
├── pom.xml                                      # Maven dependencies
└── README.md                                    # This file
```

## 🔧 Configuration

Key properties in `application.properties`:

| Property                                     | Description    | Default     |
| -------------------------------------------- | -------------- | ----------- |
| `server.port`                                | Server port    | 8080        |
| `openai.api.key`                             | OpenAI API key | -           |
| `openai.model`                               | Model to use   | gpt-4o-mini |
| `logging.level.com.example.chatbot`          | Log level      | DEBUG       |
| `spring.jackson.serialization.indent_output` | Pretty JSON    | true        |

## 🧪 Testing

Run the tests using Maven:

```bash
mvn test
```

## 📝 Dependencies

Main dependencies included:

- `spring-boot-starter-web` - REST API support
- `spring-boot-devtools` - Development utilities
- `spring-boot-starter-test` - Testing framework
- `lombok` - Code generation (optional)

## ⚠️ Important Notes

1. **API Key Security**: Store your OpenAI API key in environment variables, not in the code.
2. **Rate Limiting**: Be aware of OpenAI API rate limits and pricing.
3. **CORS Configuration**: Currently allows all origins (`*`). In production, specify exact origins.
4. **Error Handling**: The application may return errors if the OpenAI API is unavailable or the key is invalid.

## 🚢 Deployment

For production deployment:

1. Set environment variables for sensitive data (API keys)
2. Adjust CORS origins to specific domains
3. Enable HTTPS
4. Configure logging appropriately
5. Use a proper application container (Docker, etc.)

## 📞 Support

For issues with:

- **OpenAI API**: Visit [OpenAI Documentation](https://platform.openai.com/docs)
- **Spring Boot**: Visit [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- **This Project**: Review the code in the `controller`, `service`, and `dto` packages

## 📄 License

This project is provided as-is for educational purposes.

---

**Happy Chatting!** 🤖
