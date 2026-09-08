<?php

declare(strict_types=1);

require_once __DIR__ . '/HotelRoom.php';

echo "=== 1. Objek Valid Dibuat ===\n";
$kamar = new HotelRoom('101', 'Deluxe', 2, 500000);
$kamar->tampilkan();

echo "\n=== 2. Perubahan Sah: Check-in 2 Tamu ===\n";
$kamar->checkIn(2);
$kamar->tampilkan();

echo "\n=== 3. Operasi Tidak Sah #1: Check-in Melebihi Kapasitas ===\n";
try {
    $kamar->checkIn(1);
    echo "GAGAL: seharusnya operasi ini ditolak!\n";
} catch (DomainException $e) {
    echo "Ditolak sesuai harapan -> {$e->getMessage()}\n";
}

echo "\n=== 4. Operasi Tidak Sah #2: Ubah Harga Jadi Negatif ===\n";
try {
    $kamar->ubahHarga(-100000);
    echo "GAGAL: seharusnya operasi ini ditolak!\n";
} catch (InvalidArgumentException $e) {
    echo "Ditolak sesuai harapan -> {$e->getMessage()}\n";
}

echo "\n=== 5. Kondisi Akhir Objek (Invarian Tetap Terjaga) ===\n";
$kamar->tampilkan();