# JellyJelly

JellyJelly is a modern Android social networking application featuring a sleek dark theme UI, seamless navigation, and integrated media sharing capabilities.

![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Platform](https://img.shields.io/badge/platform-Android-green.svg)
![API](https://img.shields.io/badge/API-24%2B-brightgreen.svg)

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Architecture](#architecture)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Setup](#setup)
- [How to Contribute](#how-to-contribute)
- [Roadmap](#roadmap)
- [Contact](#contact)
- [License](#license)

## Overview

JellyJelly is designed to deliver a streamlined social networking experience with a focus on visual content and user interactions. The application uses a modular architecture with clear separation of concerns, making it easily extendable and maintainable.

## Features

- **Modern Dark UI**: Sleek black-themed interface with white accents for optimal readability
- **Multi-tab Navigation**: Intuitive bottom navigation with Home, Add Friends, Camera, Library, and Wallet sections
- **Authentication System**: Secure login and signup flows built with MVP architecture
- **Modular Design**: Feature-based modules for enhanced maintainability and scalability

## Architecture

JellyJelly follows the **MVP (Model-View-Presenter)** architectural pattern:

- **Model**: Data layer that handles business logic and data operations
- **View**: UI layer that displays data and captures user interactions
- **Presenter**: Intermediary layer that processes user actions and updates the view accordingly

This separation allows for:
- Better testability of business logic
- Cleaner code organization
- Easier maintenance and feature additions

### Flow Diagram

```
┌─────────┐      ┌───────────┐      ┌─────────┐
│   View  │◄─────┤ Presenter │◄─────┤  Model  │
│ (UI)    │─────►│ (Logic)   │─────►│ (Data)  │
└─────────┘      └───────────┘      └─────────┘
```

## Technology Stack

- **Language**: Kotlin
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34 (Android 14)
- **UI Components**: AndroidX, Material Design Components
- **Dependency Injection**: Dagger Hilt
- **Navigation**: Navigation Component
- **View Binding**: Android View Binding
- **Coroutines**: For asynchronous programming
- **Build System**: Gradle with Kotlin DSL

## Project Structure

JellyJelly uses a modular approach with feature-based organization:

```
jelly-android/
├── app/                 # Application module with main app components
├── common/              # Shared utilities and base classes
├── feed/                # Feed feature module
├── camera/              # Camera feature module (to be implemented)
├── library/             # Library feature module (to be implemented)
├── wallet/              # Wallet feature module (to be implemented)
└── friends/             # Friends management module (to be implemented)
```

## Setup

### Prerequisites

- Android Studio Arctic Fox (2020.3.1) or newer
- JDK 11 or newer
- Android SDK 31 or newer

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/JellyJelly-Video-Chats/jelly-android.git
   ```

2. Open the project in Android Studio:
   ```
   File -> Open -> Select the jelly-android directory
   ```

3. Build the project:
   ```
   Build -> Make Project
   ```

4. Run the application on an emulator or physical device:
   ```
   Run -> Run 'app'
   ```

## How to Contribute

We welcome contributions to JellyJelly! Here's how you can help:

1. **Fork the repository** and create your feature branch:
   ```bash
   git checkout -b feature/amazing-feature
   ```

2. **Commit your changes** following our commit message conventions:
   ```bash
   git commit -m 'feat: Add some amazing feature'
   ```

3. **Push to your branch**:
   ```bash
   git push origin feature/amazing-feature
   ```

4. **Open a Pull Request** against the `develop` branch

### Development Guidelines

- Follow Kotlin coding conventions
- Maintain the MVP pattern
- Add appropriate unit tests for new functionality
- Update documentation for significant changes
- Keep UI consistent with the established design language

## Roadmap

Here are the planned enhancements for future releases:

- [ ] Implement real authentication backend
- [ ] Add friend discovery and recommendation system
- [ ] Develop media capture and editing features
- [ ] Create content library organization system
- [ ] Integrate digital wallet functionality
- [ ] Add push notifications
- [ ] Implement offline mode support
- [ ] Add end-to-end encryption for messages
- [ ] Support for multiple themes
- [ ] Optimize for tablet layouts

## Contact

For assistance, feature requests, or general inquiries:

- **Support Email**: support@jellyjelly.dev
- **Issue Tracker**: [GitHub Issues](https://github.com/JellyJelly-Video-Chats/jelly-android/issues)

## License

JellyJelly is released under the Apache 2.0 License. See the [LICENSE](LICENSE) file for details.


