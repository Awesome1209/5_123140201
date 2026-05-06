# 📝 Tugas 5 - Notes App Navigation

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Compose%20Multiplatform-blue?style=for-the-badge&logo=kotlin" alt="Platform">
  <img src="https://img.shields.io/badge/Architecture-MVVM-green?style=for-the-badge" alt="Architecture">
  <img src="https://img.shields.io/badge/Course-Mobile%20Development-orange?style=for-the-badge" alt="Course">
</p>

## 👤 Informasi Mahasiswa
| Data Diri | Keterangan |
| :--- | :--- |
| **Nama** | Awi Septian Prasetyo |
| **NIM** | 123140201 |
| **Mata Kuliah** | Pengembangan Aplikasi Mobile (PAM) |
| **Program Studi** | Teknik Informatika |
| **Institusi** | Institut Teknologi Sumatera (ITERA) |

---

## 📖 Deskripsi Proyek
Proyek ini merupakan implementasi **Tugas Praktikum Minggu 5** yang berfokus pada **Navigasi Antar Layar** menggunakan **Navigation Compose** pada **Compose Multiplatform**.

Aplikasi yang dikembangkan adalah **Notes App**. Selain fitur navigasi standar, aplikasi ini mengintegrasikan **Halaman Profile dari Tugas 4**, yang sudah memiliki pola **MVVM**, tampilan responsif, dan dukungan *Dark Mode* yang matang.

### 🎯 Tujuan Tugas
1.  **Bottom Navigation:** Implementasi 3 tab utama (**Notes**, **Favorites**, **Profile**).
2.  **Detail Navigation:** Perpindahan dari *Note List* ke *Note Detail* dengan **passing argument** `noteId`.
3.  **Floating Action Button (FAB):** Navigasi cepat untuk membuka *Add Note Screen*.
4.  **Back Stack Management:** Penerapan navigasi balik (*back navigation*) yang benar pada seluruh layar.
5.  **Edit Feature:** Implementasi *Edit Note Screen* dengan sinkronisasi data via `noteId`.

---

## 📱 Hasil Video & Screenshot

### 🎥 Demo Video
> **Tonton demo aplikasi di sini:** > [🔗 Video Demo Aplikasi - Google Drive](https://drive.google.com/drive/folders/1_LfpLpUr39LGJHy_cD_H-1eySeB6txRK?usp=drive_link)

### 📸 Screenshot Dokumentasi

| Fitur | Light Mode | Dark Mode |
| :--- | :---: | :---: |
| **Notes Screen** | <img src="https://github.com/user-attachments/assets/fd4c5f85-d2a7-430f-9f3b-0782e269bc51" width="280" /> | <img src="https://github.com/user-attachments/assets/320b34ca-6483-4dc3-b7fa-bf0dbc1c0c93" width="280" /> |
| **Favorites Screen** | <img src="https://github.com/user-attachments/assets/963fcac0-b4a8-4416-9645-0d8b47f65c72" width="280" /> | <img src="https://github.com/user-attachments/assets/734afb9d-3ba5-4d6e-8cfe-2ba4af943cec" width="280" /> |
| **Profile Screen** | <img width="720" height="1600" alt="5-1" src="https://github.com/user-attachments/assets/0f5d60f6-9656-40d1-bb64-e0c36b171fca" /> | <img width="720" height="1600" alt="5-2" src="https://github.com/user-attachments/assets/bd49a99b-3fd6-4815-a1bd-0e8b034eaf7f" /> |
| **Add Note Screen** | <img src="https://github.com/user-attachments/assets/3522c61d-64c2-4071-9c4f-d554f20b5620" width="280" /> | <img src="https://github.com/user-attachments/assets/a3437e90-2659-4175-97e5-d539dee8d043" width="280" /> |

---

## 🛠️ Konsep & Teknologi
Aplikasi ini dibangun dengan prinsip-prinsip berikut:
* **Navigation Compose:** `NavHost`, `NavController`, dan `NavBackStackEntry`.
* **State Management:** Menggunakan ViewModel untuk menjaga integritas data antar screen.
* **UI Components:** Material 3, Scaffold, BottomAppBar, dan FloatingActionButton.

---

## 🚦 Alur Navigasi (Navigation Flow)

```mermaid
graph LR
    Main[Bottom Nav] --> Notes
    Main --> Fav[Favorites]
    Main --> Prof[Profile]
    
    Notes -->|Click Card| Detail[Note Detail]
    Notes -->|Click FAB| Add[Add Note]
    
    Fav -->|Click Card| Detail
    
    Detail -->|Click Edit| Edit[Edit Note]
    Detail -->|Back| Main
    
    Add -->|Save/Back| Notes
    Edit -->|Save/Back| Detail
```

-----

## 📂 Struktur Folder

```text
composeApp/src/commonMain/kotlin/org/example/project/
├── data/               # Model data (Note, Profile)
├── viewmodel/          # Business logic & UI State
├── navigation/         # Konfigurasi Routes & NavHost
│   ├── Screen.kt       # Definisi Route
│   ├── BottomNavBar.kt # UI Bottom Navigation
│   └── AppNavigation.kt
├── components/         # Reusable UI (Card, Form, dll)
└── ui/screens/         # Layout halaman utama
    ├── NotesScreen.kt
    ├── FavoritesScreen.kt
    ├── ProfileScreen.kt
    ├── NoteDetailScreen.kt
    ├── AddNoteScreen.kt
    └── EditNoteScreen.kt
```
