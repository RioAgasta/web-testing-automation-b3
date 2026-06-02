## 💻 **Deskripsi Proyek**
Proyek ini merupakan web automation testing untuk aplikasi **JTK Learn** menggunakan **Selenium**, **Cucumber**, dan **TestNG**. Pengujian dilakukan secara otomatis berdasarkan skenario yang ditulis dalam format Gherkin.

---

## 🛠️ **Tools & Dependencies**
- **Java**: Bahasa pemrograman utama
- **Maven**: Build automation & dependency management
- **Selenium**: Framework automation browser
- **Cucumber**: Penulisan skenario pengujian berbasis Gherkin
- **TestNG**: Framework eksekusi dan laporan pengujian
- **WebDriverManager**: Mengelola otomatisasi driver Chrome/Firefox tanpa konfigurasi manual.
- **Net Masterthought Cucumber Reporting**: Menghasilkan laporan pengujian dalam bentuk HTML yang interaktif dan mudah dipahami

---

## ▶️ **Cara Menjalankan Pengujian**
1. Pastikan Java (17+) dan Maven sudah terinstal.
2. Jalankan perintah berikut di terminal:
   ```bash
   mvn clean test
   ```
3. Laporan pengujian akan tersedia di:
   - 'target/cucumber-report-html/' (Net Masterthought Report)
   - 'reports/' (Laporan TestNG)

