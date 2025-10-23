## Bionic API — Java Spring Boot + PostgreSQL (Docker + Swagger)

Proyek ini mengimplementasikan layanan **RESTful API** yang dikembangkan menggunakan **Java Spring Boot**. Proyek ini memanfaatkan **PostgreSQL** sebagai sistem manajemen basis data (DBMS) dan dioperasikan menggunakan **Docker Compose** untuk memfasilitasi *deployment* yang efisien dan terisolasi.

Aplikasi ini dilengkapi dengan integrasi **Swagger UI** untuk dokumentasi *endpoint* API secara interaktif dan alat pengujian (testing).

---

### Prasyarat Sistem

Pastikan lingkungan kerja Anda telah terinstal perangkat lunak berikut:

- **Docker**
- **Docker Compose**
- (Opsional) **Git** (untuk operasi *cloning* repositori)
- (Opsional) **Java Development Kit (JDK) 17+** (diperlukan jika Anda berencana melakukan modifikasi pada kode sumber aplikasi Spring Boot)

---

### Step by Step Menjalankan Aplikasi

Aplikasi ini dirancang untuk dijalankan dengan cepat menggunakan Docker Compose. Seluruh proses *cloning*, *building*, dan *running* dapat dilakukan dalam satu blok perintah.

#### 1. Eksekusi Skrip Instalasi dan Deployment

Ganti `https://github.com/Anggi125/TES-PT-BIONIC-TEKNOLOGI-INDONESIA.git` dengan URL repositori Git Anda dan `TES-PT-BIONIC-TEKNOLOGI-INDONESIA` dengan nama direktori tujuan.

```bash
# 1. Clone repositori dan navigasi ke direktori proyek
git clone `https://github.com/Anggi125/TES-PT-BIONIC-TEKNOLOGI-INDONESIA.git`
cd `TES-PT-BIONIC-TEKNOLOGI-INDONESIA`

# 2. Bangun image dan jalankan semua kontainer (aplikasi & database) dalam mode detached (-d)
# Opsi '--build' memastikan image Spring Boot terbaru dibuat sebelum deployment
echo "Memulai proses pembangunan (build) dan deployment kontainer (Spring Boot & PostgreSQL)..."
docker-compose up --build -d

# 3. Verifikasi Status Kontainer
echo -e "\nMemeriksa status kontainer yang berjalan:"
docker ps

# 4. Tampilkan Informasi Akses Swagger UI
echo -e "\nAplikasi berhasil di-deploy."
echo "Dokumentasi API (Swagger UI) tersedia pada URL:"
echo "http://localhost:8080/swagger-ui/index.html"
