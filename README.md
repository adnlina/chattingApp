# Chat Application

A real-time chat application built with Spring Boot and modern web technologies. This application allows users to register, login, and have real-time conversations with other users.

## 🚀 Features

### Core Functionality
- **User Authentication**: Sign up and login with username/password
- **Real-time Messaging**: Send and receive messages in real-time
- **Bidirectional Conversations**: View complete conversation history between users
- **User Management**: Dynamic user list with logout functionality
- **Session Management**: Persistent login sessions with localStorage

### Technical Features
- **Spring Boot 3.2.5**: Modern Java framework
- **Spring Data JPA**: Database persistence with Hibernate
- **H2 Database**: In-memory database for development
- **RESTful API**: Clean REST endpoints for all operations
- **Modern UI**: Beautiful interface with Tailwind CSS
- **Responsive Design**: Works on desktop and mobile devices

## 🛠️ Technology Stack

### Backend
- **Java 21**
- **Spring Boot 3.2.5**
- **Spring Data JPA**
- **H2 Database**
- **Maven**

### Frontend
- **HTML5**
- **CSS3 (Tailwind CSS)**
- **Vanilla JavaScript**
- **Modern ES6+ Features**

## 📋 Prerequisites

Before running this application, make sure you have:

- **Java 21** or higher
- **Maven 3.6** or higher
- **Git** (for cloning the repository)

## 🚀 Quick Start

### 1. Clone the Repository
```bash
git clone https://github.com/adnlina/chattingApp.git
cd chattingApp
```

### 2. Run the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### 3. Open the Frontend
Open `front.html` in your web browser or navigate to the file location and double-click it.

## 📖 Usage Guide

### Creating an Account
1. Open the chat application in your browser
2. Enter a username and password
3. Click "Sign Up"
4. You'll be automatically logged in

### Logging In
1. Enter your username and password
2. Click "Login"
3. You'll see the chat interface

### Sending Messages
1. Select a user from the dropdown menu
2. Type your message in the input field
3. Press Enter or click "Send"
4. Your message will appear in the chat

### Viewing Conversations
- Messages are displayed in chronological order
- Your messages appear on the right (blue)
- Received messages appear on the left (white)
- The conversation updates automatically every 2 seconds

### Logging Out
1. Click the red "Logout" button in the top-right corner
2. You'll be returned to the login screen
3. All session data will be cleared

## 🔧 API Endpoints

### Authentication
- `POST /api/auth/signup` - Register a new user
- `POST /api/auth/login` - Login user
- `GET /api/auth/users` - Get all registered users

### Messaging
- `POST /api/messages` - Send a message
- `GET /api/messages?user1=X&user2=Y` - Get conversation between two users
- `GET /api/messages/latest?user1=X&user2=Y&timestampMs=Z` - Get new messages since timestamp

## 🗄️ Database

The application uses H2 in-memory database with the following configuration:
- **URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: (empty)
- **H2 Console**: Available at `http://localhost:8080/h2-console`

## 🧪 Testing the Application

### Test Users
The application comes with several test users:
- **alice** (password: alice123)
- **ilyas** (password: ilyas123)
- **bob** (password: bob123)

### Testing Scenarios
1. **Login as ilyas** and send a message to alice
2. **Logout** and **login as alice**
3. **Select ilyas** from the dropdown to see the message
4. **Reply** as alice
5. **Switch back** to ilyas to see the complete conversation

## 🔒 Security Features

- **Password Storage**: Passwords are stored in plain text (for demo purposes)
- **Session Management**: User sessions are managed with localStorage
- **CORS Configuration**: Cross-origin requests are enabled for development

## 🚀 Deployment

### Local Development
```bash
mvn spring-boot:run
```

### Production Deployment
1. Build the application: `mvn clean package`
2. Run the JAR file: `java -jar target/chat-0.0.1-SNAPSHOT.jar`

## 📁 Project Structure

```
chattingApp/
├── src/
│   └── main/
│       ├── java/com/example/chat/
│       │   ├── ChatApplication.java
│       │   ├── controller/
│       │   │   ├── AuthController.java
│       │   │   └── ChatController.java
│       │   ├── model/
│       │   │   ├── Message.java
│       │   │   └── User.java
│       │   ├── repository/
│       │   │   ├── ConversationRepository.java
│       │   │   └── UserRepository.java
│       │   └── service/
│       │       └── ChatService.java
│       └── resources/
│           └── application.properties
├── front.html
├── pom.xml
└── README.md
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature-name`
3. Commit your changes: `git commit -am 'Add feature'`
4. Push to the branch: `git push origin feature-name`
5. Submit a pull request

## 📝 License

This project is open source and available under the [MIT License](LICENSE).

## 🆘 Support

If you encounter any issues or have questions:
1. Check the application logs for error messages
2. Ensure all prerequisites are installed
3. Verify the application is running on port 8080
4. Check that the frontend can access the backend API

## 🎯 Future Enhancements

- [ ] WebSocket support for real-time messaging
- [ ] Message encryption
- [ ] File sharing capabilities
- [ ] User profiles and avatars
- [ ] Group chat functionality
- [ ] Message search and filtering
- [ ] Push notifications
- [ ] Mobile app version
