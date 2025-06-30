# 🎬 MovieApp

A modern Android application built with **Kotlin** and **Jetpack Compose**, designed to deliver a seamless movie discovery experience using [TMDB API](https://developer.themoviedb.org/reference/intro/getting-started).

This project was developed as a way to **consolidate and showcase my experience** in mobile development, architecture patterns, and modern Android tools — with a strong emphasis on clean, scalable code and professional UI/UX.

> 🔗 GitHub Repo: [github.com/gonzalomonzon02/MovieApp](https://github.com/gonzalomonzon02/MovieApp)

---

## 📱 Key Features

### 🎯 Core Functionality
- **Movie Exploration**: Browse popular movies with infinite pagination
- **Smart Search**: Search movies by title with 500ms debounce
- **Filters & Sorting**: Sort by popularity, rating, release date, or title A-Z
- **Complete Details**: Detailed movie view with comprehensive information
- **Related Movies**: Discover similar movies to the ones you like
- **Smooth Navigation**: Seamless navigation between screens with smooth animations

### 🌍 Internationalization
- **Multi-language Support**: English and Spanish
- **Automatic Detection**: Adapts to device language
- **Complete Translations**: All text strings localized

### 🎨 User Experience
- **Material Design 3**: Modern and attractive interface
- **Loading States**: Elegant loading indicators and error states
- **Error Handling**: Clear error messages with retry options
- **Empty States**: Informative states when there's no content

## 🏗️ Architecture & Patterns

### 📐 Clean Architecture
The application follows **Clean Architecture** principles with clear separation of concerns:

```
📁 presentation/     # Presentation layer (UI/UX)
📁 domain/          # Domain layer (business logic)
📁 data/            # Data layer (data sources)
```

### 🎯 Design Patterns Implemented
- **MVVM (Model-View-ViewModel)**: For UI state management
- **Repository Pattern**: To abstract data sources
- **Use Case Pattern**: To encapsulate business logic
- **Dependency Injection**: With Koin for dependency injection
- **Observer Pattern**: With Kotlin Flow for reactivity

### 🔄 Data Flow
```
UI → ViewModel → UseCase → Repository → DataSource (Remote/Local)
```

## 🛠️ Technology Stack

### 📱 Mobile Development
- **Kotlin**: Primary project language
- **Jetpack Compose**: Modern declarative UI
- **Android SDK**: API 35 (Android 15)
- **Min SDK**: 24 (Android 7.0)

### 🌐 Networking
- **Retrofit**: HTTP client for REST APIs
- **OkHttp**: HTTP client with custom interceptors
- **Gson**: JSON serialization/deserialization
- **Custom Interceptors**:
  - `AddAuthorizationHeaderInterceptor`: Automatic API key handling
  - `LanguageInterceptor`: Automatic language detection
  - `AddSettingHeaderInterceptor`: Additional headers

### 💾 Data Persistence
- **Room Database**: Local SQLite database
- **DAO Pattern**: Data access with annotations
- **Entity Mapping**: Mapping between entities and DTOs

### 🔄 State Management
- **Kotlin Flow**: Reactive programming
- **StateFlow**: Observable state
- **Paging 3**: Efficient pagination with lazy loading

### 🧪 Testing
- **JUnit**: Unit testing
- **Mockito**: Dependency mocking
- **Coroutines Test**: Asynchronous code testing
- **Espresso**: UI testing (ready)

### 🎨 UI/UX
- **Material Design 3**: Modern design system
- **Compose Navigation**: Declarative navigation
- **Glide Compose**: Efficient image loading
- **Accompanist**: Additional utilities for Compose

### 🔧 Development Tools
- **Koin**: Dependency injection
- **KSP**: Annotation processing
- **Gradle**: Build system
- **Version Catalogs**: Centralized dependency management

### 🚀 CI/CD & DevOps
- **GitHub Actions**: Continuous Integration and Deployment
- **Automated Testing**: Unit tests run on every push/PR
- **Test Reports**: Automated test reporting with detailed results
- **Gradle Caching**: Optimized build performance with caching

## 📁 Project Structure

```
app/src/main/java/com/gonzalomonzon02/movieapp/
├── 📁 data/                    # Data layer
│   ├── 📁 local/              # Local database
│   │   ├── MovieDao.kt
│   │   ├── MovieDatabase.kt
│   │   └── MovieEntity.kt
│   ├── 📁 mapper/             # Layer mappers
│   │   └── MovieMappers.kt
│   ├── 📁 model/              # Data DTOs
│   │   ├── MovieDto.kt
│   │   └── MovieListResponse.kt
│   ├── 📁 remote/             # Remote API
│   │   ├── 📁 interceptor/    # HTTP interceptors
│   │   └── MovieService.kt
│   └── 📁 repository/         # Repository implementation
│       └── MovieRepositoryImpl.kt
├── 📁 di/                     # Dependency injection
│   └── AppModules.kt
├── 📁 domain/                 # Domain layer
│   ├── 📁 model/              # Domain models
│   │   └── Movie.kt
│   ├── 📁 repository/         # Repository interfaces
│   │   ├── MovieRepository.kt
│   │   └── Source.kt
│   └── 📁 usecase/            # Use cases
│       ├── GetMovieDetailUseCase.kt
│       ├── GetPopularMoviesUseCase.kt
│       └── GetRelatedMoviesUseCase.kt
└── 📁 presentation/           # Presentation layer
    ├── 📁 navigation/         # Navigation
    │   └── NavGraph.kt
    ├── 📁 screens/            # Application screens
    │   ├── 📁 components/     # Reusable components
    │   ├── 📁 main/           # Main screen
    │   ├── 📁 moviedetail/    # Detail screen
    │   └── 📁 splash/         # Splash screen
    └── 📁 theme/              # Themes and styles
        ├── Color.kt
        ├── Theme.kt
        └── Type.kt
```

## 🚀 Project Setup

### 📋 Prerequisites
- Android Studio Hedgehog or higher
- JDK 11 or higher
- [The Movie Database (TMDB)](https://developer.themoviedb.org/reference/intro/getting-started) API Key

### ⚙️ Configuration
1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/MovieApp.git
   cd MovieApp
   ```

2. **Configure the API Key**:
   - Create a `local.properties` file in the project root
   - Add your TMDB API key:
     ```properties
     TMDB_API_KEY=your_api_key_here
     ```

3. **Sync the project**:
   ```bash
   ./gradlew build
   ```

4. **Run the application**:
   - Open the project in Android Studio
   - Connect a device or start an emulator
   - Press `Run` (▶️)

## 🧪 Testing

### 📊 Test Coverage
- **Unit Tests**: Use cases and repositories
- **Integration Tests**: Complete data flow
- **UI Tests**: Ready with Espresso

### 🏃‍♂️ Running Tests
```bash
# Unit tests
./gradlew test

# Instrumentation tests
./gradlew connectedAndroidTest

# Code coverage
./gradlew jacocoTestReport
```

## 🔄 CI/CD Pipeline

### 🚀 Continuous Integration
The project uses **GitHub Actions** for automated CI/CD with the following workflows:

#### **Build & Test Pipeline** (`ci.yml`)
- **Triggers**: Push to main branch and Pull Requests
- **Environment**: Ubuntu Latest with JDK 17
- **Actions**:
  - ✅ Code checkout
  - ✅ JDK 17 setup with Gradle caching
  - ✅ Build debug APK
  - ✅ Environment variable management (API keys)

#### **Testing Pipeline** (`testing.yml`)
- **Triggers**: Every push
- **Actions**:
  - ✅ Unit test execution
  - ✅ Automated test reporting
  - ✅ Test result analysis

### 📈 CI/CD Benefits
- **Automated Quality Assurance**: Tests run on every code change
- **Early Bug Detection**: Issues caught before merging
- **Consistent Builds**: Standardized build environment
- **Fast Feedback**: Immediate test results and reports
- **Deployment Ready**: Automated build artifacts

## 📊 Project Metrics
 
- **Lines of Code**: ~2,000+ lines of Kotlin
- **Files**: 50+ organized files
- **Dependencies**: 20+ modern libraries
- **Test Coverage**: 80%+ in domain layer
- **Build Time**: < 30 seconds
- **CI/CD**: Automated testing and builds

## 🔧 Advanced Technical Features

### ⚡ Performance
- **Efficient Pagination**: Lazy data loading
- **Local Cache**: Offline storage
- **Image Optimization**: Efficient loading with Glide
- **Fast Compilation**: Optimized Gradle configuration

### 🔒 Security
- **Secure API Key**: Safe credential handling
- **HTTP Interceptors**: Automatic security headers
- **Data Validation**: Input sanitization

### 🌐 Connectivity
- **Offline Handling**: Functionality with local data
- **Automatic Retry**: Retries on error
- **Synchronization**: Automatic data sync

## 📈 Future Roadmap

- [ ] **Favorites**: Movie favorites system
- [ ] **Notifications**: New movie alerts
- [ ] **Dark Mode**: Automatic dark theme
- [ ] **Widgets**: Android widgets
- [ ] **Share**: Share movies on social media
- [ ] **Trailers**: Movie trailer playback
- [ ] **Reviews**: User review system

## 📄 License

This project is licensed under the MIT License. See the `LICENSE` file for details.

## 👨‍💻 Author

**Gonzalo Monzón**
- GitHub: [@gonzalomonzon02](https://github.com/gonzalomonzon02)
- LinkedIn: [@luciano-gonzalo-monzón-82265a130](https://linkedin.com/in/luciano-gonzalo-monzón-82265a130)

## 🙏 Acknowledgments

- [The Movie Database (TMDB)](https://www.themoviedb.org/) for providing the API
- [Jetpack Compose](https://developer.android.com/jetpack/compose) for the UI framework
- [Koin](https://insert-koin.io/) for dependency injection
- [Material Design](https://material.io/) for the design system

---

⭐ **If you like this project, give it a star!** 