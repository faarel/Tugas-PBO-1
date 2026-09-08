# Tugas 1 — Pengantar OOP

**Nama:** [Nama Lu]
**NPM:** [NPM Lu]

## Nama Domain
**Kamar Hotel** — mencatat data satu kamar hotel: nomor kamar, tipe kamar, kapasitas tamu, harga per malam, dan jumlah tamu yang sedang menginap. Kamar dapat menerima check-in tamu, check-out, dan perubahan harga.

## Invarian & Alasannya
1. **`hargaPerMalam` harus selalu lebih besar dari nol.**
   Alasan: kamar hotel adalah produk yang dijual, tidak mungkin gratis (nol) atau bernilai negatif. Ini aturan bisnis, bukan sekadar validasi teknis.
2. **`jumlahTamuMenginap` tidak boleh negatif dan tidak boleh melebihi `kapasitasTamu`.**
   Alasan: kamar punya batas fisik (jumlah tempat tidur/ruang), jadi jumlah tamu yang menginap harus berada di rentang `0 .. kapasitasTamu`. Melanggar batas ini berarti data tidak merepresentasikan kondisi nyata kamar.

Kedua invarian dijaga oleh method `checkIn()`, `checkOut()`, dan `ubahHarga()`. Tidak ada setter langsung untuk `hargaPerMalam` maupun `jumlahTamuMenginap`, sehingga field ini hanya bisa berubah lewat method-method tersebut yang selalu memvalidasi sebelum mengubah nilai.

Field `nomorKamar`, `tipeKamar`, dan `kapasitasTamu` bersifat tetap (`final` di Java, `readonly` di PHP) karena merupakan identitas kamar yang tidak berubah selama objek hidup.

## Cara Menjalankan

**Java**
Masuk ke folder tempat file berada:
   cd java (atau sesuaikan dengan nama folder Anda)

Compile kode-nya:
   javac HotelRoom.java Main.java

Jalankan:
   java Main

**PHP**
Masuk ke folder tempat file berada:
   cd php (atau sesuaikan dengan nama folder Anda)

Jalankan:
   php main.php

## Contoh Output (Java & PHP menghasilkan pola yang sama)

=== 1. Objek Valid Dibuat ===
Nomor Kamar     : 101
Tipe Kamar      : Deluxe
Kapasitas Tamu  : 2
Harga per Malam : Rp500000
Tamu Menginap   : 0

=== 2. Perubahan Sah: Check-in 2 Tamu ===
Nomor Kamar     : 101
Tipe Kamar      : Deluxe
Kapasitas Tamu  : 2
Harga per Malam : Rp500000
Tamu Menginap   : 2

=== 3. Operasi Tidak Sah #1: Check-in Melebihi Kapasitas ===
Ditolak : Jumlah tamu melebihi kapasitas kamar (kapasitas: 2)

=== 4. Operasi Tidak Sah #2: Ubah Harga Jadi Negatif ===
Ditolak : Harga per malam harus lebih besar dari nol

=== 5. Kondisi Akhir Objek (Invarian Tetap Terjaga) ===
Nomor Kamar     : 101
Tipe Kamar      : Deluxe
Kapasitas Tamu  : 2
Harga per Malam : Rp500000
Tamu Menginap   : 2

## Cara kerja Sederhana
*   **Constructor** mengisi `nomorKamar`, `tipeKamar`, `kapasitasTamu`, dan `hargaPerMalam` di awal (dengan tamu menginap = 0).
*   `checkIn()` menolak (melempar exception) kalau jumlah tamu yang ditambah akan melebihi kapasitas kamar (menjaga Invarian 2).
*   `ubahHarga()` menolak (melempar exception) kalau harga baru diisi nol atau negatif (menjaga Invarian 1).
*   Tidak ada setter murni. Field yang punya invarian hanya bisa berubah lewat method spesifik karena method tersebut memvalidasi data sebelum mengubah nilai.

## Deklarasi Penggunaan AI
Saya menyusun struktur class dan implementasi kode ini dengan bantuan AI (Claude, Gemini) untuk membantu merancang logika invarian, memahami pesan error di terminal, dan merapikan struktur dokumentasi tugas agar sesuai dengan materi Pertemuan 01.