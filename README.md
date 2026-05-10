# Arogya_Nidhi
<div align="center">

<img src="https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white"/>
<img src="https://img.shields.io/badge/Language-Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white"/>
<img src="https://img.shields.io/badge/UI-Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white"/>
<img src="https://img.shields.io/badge/Backend-Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=black"/>
<img src="https://img.shields.io/badge/AI-Gemini%20Nano-8E75B2?style=for-the-badge&logo=google&logoColor=white"/>

<br/><br/>

# 🏥 Arogya-Nidhi
### *Health Scheme Eligibility Checker*

**A digital counselor that helps rural and low-income families discover government health schemes they are eligible for — instantly, offline, and in their own language.**

<br/>

> *"Bridging the gap between rural families and life-saving government health schemes."*

<br/>

**Mohammad Kaif** · `3PD22EC054` · Android App Development using GenAI

</div>

---

## 📖 Table of Contents

- [Problem Statement](#-problem-statement)
- [About the App](#-about-the-app)
- [Screenshots](#-screenshots)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Architecture](#-architecture)
- [Firebase Integration](#-firebase-integration)
- [Decision Engine](#-decision-engine)
- [Project Structure](#-project-structure)
- [Getting Started](#-getting-started)
- [How to Run](#-how-to-run)
- [Supported Schemes](#-supported-schemes)
- [Success Criteria](#-success-criteria)
- [Impact Goals](#-impact-goals)
- [License](#-license)

---

## ⚠️ Problem Statement

India has one of the world's most extensive networks of government-backed healthcare schemes — **Ayushman Bharat (PMJAY)**, Rashtriya Arogya Nidhi, Janani Suraksha Yojana, and dozens of state-level programmes.

Yet the very communities these schemes are designed to protect **remain unaware of their entitlements**, leading to:

- 💸 Families paying out-of-pocket for surgeries covered by free government schemes
- 📋 Rejected applications due to wrong or missing documents
- 🏥 No knowledge of which empanelled hospitals accept their scheme
- 🌐 Government portals that are English-first, text-heavy, and inaccessible to rural users

> **55 million Indians** are pushed below the poverty line each year due to healthcare costs — most of which could be avoided through better scheme awareness.

---

## 🌟 About the App

**Arogya-Nidhi** (meaning *Health Treasury*) is a mobile-first Android application that acts as a **digital health scheme counselor**.

The user answers **5 simple questions** about their family — income, occupation, BPL status, family size, and state — and the app instantly:

1. ✅ Lists all government schemes they are **eligible for**
2. 📋 Provides a **document checklist** for each scheme
3. 🏥 Shows the nearest **empanelled hospitals** by district
4. 🤖 Answers follow-up questions via a **GenAI-powered chatbot**

---

## 📱 Screenshots

| Home Screen | Eligibility Quiz | BPL Question | Result Screen |
|:-----------:|:----------------:|:------------:|:-------------:|
| *(Launch screen with CTA)* | *(5-step stepper)* | *(Yes/No toggle)* | *(Scheme cards)* |

> *Run the app to view all screens in action.*

---

## ✨ Features

### 🔒 Must-Have (MVP — v1.0)

| # | Feature | Description |
|---|---------|-------------|
| M1 | **Eligibility Quiz** | 5-step Stepper UI — income, BPL, occupation, state, family size |
| M2 | **Decision Tree Engine** | Kotlin rule engine mapping answers → eligible schemes |
| M3 | **Scheme Result Screen** | "You are eligible for [Scheme A] and [Scheme B]" |
| M4 | **Document Checklist** | Per-scheme document list with tick-off, stored in Room DB |
| M5 | **Hospital Finder** | District-searchable empanelled hospital list |
| M6 | **Offline Mode** | Full quiz, results, checklist — zero internet required |
| M7 | **Multi-language** | English, Kannada, Hindi at launch |
| M8 | **Scheme Cards** | Plain-language descriptions with benefit amounts |

### 💡 Good-to-Have (v1.x — v2.0)

| # | Feature | Description |
|---|---------|-------------|
| G1 | **GenAI FAQ Chatbot** | Gemini Nano on-device assistant in regional language |
| G2 | **Voice Input** | Speech-to-text via Bhashini API for zero-literacy users |
| G3 | **Push Notifications** | Scheme enrollment deadline alerts via Firebase FCM |
| G4 | **PDF Export** | Share eligibility certificate + document checklist |
| G5 | **Scheme Comparison** | Side-by-side benefit comparison of two schemes |
| G6 | **ASHA Worker Mode** | Bulk eligibility check for multiple families |
| G7 | **Live Sync** | OTA scheme data updates via Firebase Cloud Functions |
| G8 | **Dark Mode** | System-aware dark theme |

---

## 🛠️ Tech Stack

```
Language        →  Kotlin 2.0
UI Framework    →  Jetpack Compose + Material 3
Architecture    →  MVVM + Repository Pattern
State Mgmt      →  StateFlow / collectAsState()
Local Storage   →  Room Database + JSON assets
Navigation      →  Jetpack Navigation Compose
Backend         →  Firebase (Firestore, Auth, FCM, Functions)
Maps            →  Google Maps SDK
GenAI           →  Gemini Nano (on-device)
Preferences     →  DataStore Preferences
Build System    →  Gradle 8.7 + Version Catalog (libs.versions.toml)
Min SDK         →  Android 8.0 (API 26)
Target SDK      →  Android 14 (API 35)
```

---

## 🏗️ Architecture

Arogya-Nidhi follows **MVVM (Model-View-ViewModel)** architecture with a Repository pattern for clean separation of concerns.

```
┌─────────────────────────────────────────────────────────┐
│                     UI LAYER                            │
│   HomeScreen · QuizScreen · ResultScreen                │
│   SchemeDetailScreen · HospitalScreen                   │
└─────────────────┬───────────────────────────────────────┘
                  │  StateFlow / collectAsState()
┌─────────────────▼───────────────────────────────────────┐
│                  VIEWMODEL LAYER                        │
│        QuizViewModel · HospitalViewModel                │
└─────────────────┬───────────────────────────────────────┘
                  │  suspend functions / Flow
┌─────────────────▼───────────────────────────────────────┐
│                 REPOSITORY LAYER                        │
│      SchemeRepository · HospitalRepository              │
│      (Single source of truth — local + remote)          │
└──────────┬──────────────────────────┬───────────────────┘
           │                          │
┌──────────▼──────────┐  ┌───────────▼───────────────────┐
│    LOCAL (Room DB)   │  │     REMOTE (Firebase)          │
│  SchemesDao          │  │  Firestore · Auth · FCM        │
│  HospitalsDao        │  │  Cloud Functions · Remote Cfg  │
│  ChecklistDao        │  │  Firebase Storage              │
└─────────────────────┘  └───────────────────────────────┘
```

---

## 🔥 Firebase Integration

| Service | Usage |
|---------|-------|
| **Firebase Authentication** | Anonymous auth on launch; OTP phone login for returning users |
| **Cloud Firestore** | Scheme catalog, eligibility rules, hospital dataset |
| **Firebase Storage** | Scheme JSON files, document checklist PDFs |
| **Cloud Functions** | OTA trigger to update scheme data without app release |
| **Remote Config** | Feature flags — toggle GenAI chatbot, new schemes |
| **Firebase Analytics** | Track quiz completion, district searches, scheme views |
| **Crashlytics** | Real-time crash reporting and ANR monitoring |
| **Firebase Cloud Messaging** | Push notifications for scheme deadlines |

### Offline-First Sync Strategy

```
App Launch
    │
    ├── Room DB has data? ──YES──▶ Show instantly
    │        │
    │        NO
    │        ▼
    │   Seed from res/raw/schemes.json  (no internet needed)
    │
    └── WorkManager (every 24h, background)
            │
            ▼
        Pull Firestore diff (version-stamped)
            │
            ▼
        Update Room DB (server wins on conflict)
```

---

## 🧠 Decision Engine

The core eligibility logic lives in `DecisionEngine.kt` — a modular Kotlin rule-based system.

```kotlin
// Each scheme has its own eligibility function
private fun isEligibleAyushmanBharat(input: QuizInput): Boolean {
    return (input.isBPL || input.annualIncome < 150_000) &&
            input.occupation != Occupation.GOVT_EMPLOYEE
}

// Main evaluate() collects all passing schemes
fun evaluate(input: QuizInput): EligibilityResult {
    val ruleMap = mapOf(
        "pmjay"   to ::isEligibleAyushmanBharat,
        "pmfby"   to ::isEligiblePMFBY,
        "esic"    to ::isEligibleESIC,
        "ran"     to ::isEligibleRAN,
        "jsy"     to ::isEligibleJSY,
        "cghs"    to ::isEligibleCGHS,
        "arogya_ka" to ::isEligibleArogyaKarnataka
    )
    return EligibilityResult(
        eligibleSchemes = ruleMap
            .filter { (_, rule) -> rule(input) }
            .mapNotNull { (id, _) -> SCHEME_CATALOG[id] }
    )
}
```

**To add a new scheme:**
1. Add scheme data to `SCHEME_CATALOG` in `DecisionEngine.kt`
2. Write `private fun isEligible<Name>(input: QuizInput): Boolean`
3. Register it in the `ruleMap`

---

## 📁 Project Structure

```
ArogyaNidhi/
├── app/
│   ├── build.gradle.kts
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── kotlin/com/arogyaNidhi/
│       │   ├── MainActivity.kt
│       │   ├── data/
│       │   │   ├── DecisionEngine.kt          ← Core eligibility logic
│       │   │   ├── model/
│       │   │   │   └── Models.kt              ← Data classes
│       │   │   └── repository/
│       │   │       ├── SchemeRepository.kt    ← Room + Firestore
│       │   │       └── HospitalRepository.kt  ← Hospital data
│       │   ├── ui/
│       │   │   ├── NavGraph.kt                ← Navigation routes
│       │   │   ├── theme/
│       │   │   │   └── Theme.kt               ← Material 3 colors
│       │   │   └── screens/
│       │   │       ├── HomeScreen.kt          ← Landing screen
│       │   │       ├── QuizScreen.kt          ← 5-step Stepper
│       │   │       ├── ResultScreen.kt        ← Eligible schemes
│       │   │       ├── SchemeDetailScreen.kt  ← Document checklist
│       │   │       └── HospitalScreen.kt      ← Hospital finder
│       │   └── viewmodel/
│       │       ├── QuizViewModel.kt
│       │       └── HospitalViewModel.kt
│       └── res/
│           ├── raw/schemes.json               ← Seed data
│           └── values/themes.xml
├── gradle/
│   └── libs.versions.toml                     ← All dependency versions
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

---

## 🚀 Getting Started

### Prerequisites

- **Android Studio** Iguana 2023.2.1 or newer
- **JDK** 11 or higher
- **Android SDK** API 26+
- **Kotlin** 2.0+
- **Firebase** project (for backend features)

### Clone the Repository

```bash
git clone https://github.com/yourusername/arogya-nidhi.git
cd arogya-nidhi
```

### Firebase Setup

1. Go to [Firebase Console](https://console.firebase.google.com/) → Create project
2. Add Android app with package name `com.arogyaNidhi`
3. Download `google-services.json` → place in `app/` folder
4. Enable **Authentication**, **Firestore**, **Storage**, **FCM**

---

## ▶️ How to Run

### Step 1 — Open in Android Studio
```
File → Open → Select ArogyaNidhi folder → OK
```

### Step 2 — Sync Gradle
Wait for Gradle sync to complete (~2–3 min on first run).
If it fails: `File → Invalidate Caches → Invalidate and Restart`

### Step 3 — Set Up Emulator
```
Tools → Device Manager → Create Virtual Device
→ Select Pixel 6 → API 33/34 → Finish → ▶ Start
```

### Step 4 — Run
```
Press Shift + F10  or  click the ▶ Run button
```

### Common Fixes

| Error | Fix |
|-------|-----|
| `SDK not found` | `File → Project Structure → SDK Location` |
| `Unresolved reference` | `Build → Clean → Rebuild Project` |
| `Kotlin version mismatch` | `Tools → Kotlin → Configure Kotlin Plugin Updates` |
| `KSP not found` | Ensure `libs.versions.toml` is inside `gradle/` folder |
| Emulator slow | Enable hardware acceleration (HAXM / Hyper-V) |

---

## 🏥 Supported Schemes

| Scheme | Eligibility | Benefit |
|--------|-------------|---------|
| **Ayushman Bharat – PMJAY** | Income < ₹1.5L or BPL | Up to ₹5,00,000 / year |
| **Rashtriya Swasthya Bima Yojana** | BPL + income < ₹3L | Up to ₹30,000 / year |
| **Rashtriya Arogya Nidhi** | BPL + life-threatening illness | Up to ₹15,00,000 (one-time) |
| **Janani Suraksha Yojana** | BPL or income < ₹2L | ₹1,000 – ₹1,400 per delivery |
| **PMFBY** | Farmers | Crop cover + health camps |
| **ESIC** | Daily wage workers (income ≤ ₹2.52L) | Full cashless treatment |
| **CGHS** | Central Govt employees | Full OPD + IPD coverage |
| **Arogya Karnataka** | Karnataka residents | Free (BPL) / 70% subsidy (APL) |

---

## 🎯 Success Criteria

| # | Criterion | Pass Condition |
|---|-----------|----------------|
| SC-1 | Dynamic Results | Scheme result changes correctly based on income / BPL input |
| SC-2 | Hospital Search | Searchable and filterable by district; results < 1 second |
| SC-3 | Empathetic UI | SUS score ≥ 70; tested with 3 non-tech users |
| SC-4 | Offline Mode | All core flows work in airplane mode |
| SC-5 | Document Checklist | Correct documents shown per scheme; persistent tick-off |
| SC-6 | GenAI Feature | At least one AI-powered feature functional |

---

## 🌱 Impact Goals

- 🌍 **Universal Health Coverage** — Ensure the poorest benefit from government safety nets
- ⚖️ **Social Justice** — Remove the information barrier for the most vulnerable
- 💰 **Financial Protection** — Prevent families from falling into medical debt
- 👩‍⚕️ **Women & Child Welfare** — Surface maternal and child health schemes
- 💪 **Empowerment** — Give citizens knowledge to assert their rights without middlemen

---

## 📄 License

```
MIT License — Built for academic purposes.
Android App Development using GenAI curriculum.
```

---

<div align="center">

**Built with ❤️ for rural India**

`Mohammad Kaif` · `3PD22EC054` · `Android App Development using GenAI` · `2026`

⭐ Star this repo if you found it helpful!

</div>
