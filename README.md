# H-Shop
![Android](https://img.shields.io/badge/Android-API%2024+-green)
![Kotlin](https://img.shields.io/badge/Kotlin-1.9.x-purple)
![License](https://img.shields.io/badge/License-MIT-blue)

## Table of Contents
1. [Overview](#overview)
2. [Features](#features)
3. [Architecture](#architecture)
4. [Tech Stack](#tech-stack)
5. [Installation](#installation)
6. [Environment Configuration](#environment-configuration)
7. [Usage Guide](#usage-guide)
8. [Screenshots](#screenshots)
9. [Localization](#localization)
10. [State Management](#state-management)
11. [Contributing](#contributing)
12. [License](#license)
13. [Roadmap](#roadmap)
14. [Acknowledgements](#acknowledgements)

---

## Overview
H-Shop delivers a seamless shopping experience on Android with a clean Compose UI, offline-first caching and lightning-fast search.  
Built **100 % Kotlin + Jetpack Compose**, it follows a single-activity architecture and is ready for payment & analytics extensions.

---

## Features
- **Home** – flash deals & banners
- **Categories** – multi-level tree with filters
- **Product Details** – gallery, specs, reviews
- **Cart & Checkout** – local persistence, coupons
- **Profile** – orders, addresses, dark-mode toggle
- **Localization** – English & Persian built-in

---

## Architecture
```mermaid
flowchart LR
    UI[Compose UI] -->|Events| VM[ViewModel]
    VM -->|Flows| Repo[Repository]
    Repo --> API[Retrofit]
    Repo --> DB[(Room DB)]
    API --> Backend[(REST API)]



## Installation
```bash
# clone
git clone https://github.com/your-org/h-shop.git
cd h-shop

# dependencies
./gradlew build

# install on connected device/emulator
./gradlew installDebug

## Environment Configuration
Duplicate `.env.example` → `.env` and edit:

## Usage Guide
1. Launch the app – Home feed loads.  
2. Browse **Categories** and pick a product.  
3. Add to cart; subtotal updates instantly.  
4. Switch language in **Profile ▸ Settings**.  
5. Toggle dark mode to see dynamic theming.

## Localization
Strings live in `res/values` & `res/values-fa`.  
Call `LocalUtils.setLocale(context, Constants.PERSIAN)` at runtime to switch.

## License
Distributed under the MIT License. See `LICENSE` for details.

## Roadmap
- [ ] In-app payments (Stripe)
- [ ] Wish-list & push notifications
- [ ] GraphQL migration

## Acknowledgements
- JetBrains & AndroidX
- Square OSS (Retrofit, OkHttp, Moshi)
- Coil contributors
