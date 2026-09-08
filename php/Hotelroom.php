<?php

declare(strict_types=1);

/**
 * Class HotelRoom merepresentasikan satu kamar di sebuah hotel.
 *
 * Invarian yang dijaga sepanjang hidup objek:
 *  1) hargaPerMalam harus selalu > 0 (tidak boleh nol atau negatif).
 *  2) jumlahTamuMenginap tidak boleh negatif dan tidak boleh melebihi kapasitasTamu.
 *
 * Field nomorKamar, tipeKamar, dan kapasitasTamu ditulis readonly (padanan
 * final di Java): sekali diisi lewat constructor, tidak bisa ditulis ulang.
 * Tidak ada setter untuk hargaPerMalam maupun jumlahTamuMenginap karena
 * keduanya punya invarian — perubahan hanya lewat method yang memvalidasi
 * (checkIn, checkOut, ubahHarga).
 */
final class HotelRoom
{
    private float $hargaPerMalam;
    private int $jumlahTamuMenginap;

    public function __construct(
        private readonly string $nomorKamar,
        private readonly string $tipeKamar,
        private readonly int $kapasitasTamu,
        float $hargaPerMalam
    ) {
        if ($this->kapasitasTamu <= 0) {
            throw new InvalidArgumentException('Kapasitas tamu harus lebih besar dari nol');
        }
        if ($hargaPerMalam <= 0) {
            throw new InvalidArgumentException('Harga per malam harus lebih besar dari nol');
        }

        $this->hargaPerMalam = $hargaPerMalam;
        $this->jumlahTamuMenginap = 0;
    }

    /**
     * Menambahkan tamu yang check-in ke kamar ini.
     * Menegakkan invarian #2: total tamu tidak boleh melebihi kapasitas.
     */
    public function checkIn(int $jumlahTamu): void
    {
        if ($jumlahTamu <= 0) {
            throw new InvalidArgumentException('Jumlah tamu check-in harus lebih besar dari nol');
        }
        if ($this->jumlahTamuMenginap + $jumlahTamu > $this->kapasitasTamu) {
            throw new DomainException(
                "Jumlah tamu melebihi kapasitas kamar (kapasitas: {$this->kapasitasTamu})"
            );
        }
        $this->jumlahTamuMenginap += $jumlahTamu;
    }

    /**
     * Mengosongkan kamar. Menolak jika memang sudah tidak ada tamu,
     * supaya jumlahTamuMenginap tidak pernah bisa jadi negatif (invarian #2).
     */
    public function checkOut(): void
    {
        if ($this->jumlahTamuMenginap === 0) {
            throw new DomainException('Tidak ada tamu untuk check-out, kamar sudah kosong');
        }
        $this->jumlahTamuMenginap = 0;
    }

    /**
     * Mengubah harga per malam. Menegakkan invarian #1.
     */
    public function ubahHarga(float $hargaBaru): void
    {
        if ($hargaBaru <= 0) {
            throw new InvalidArgumentException('Harga per malam harus lebih besar dari nol');
        }
        $this->hargaPerMalam = $hargaBaru;
    }

    public function getHargaPerMalam(): float
    {
        return $this->hargaPerMalam;
    }

    public function getJumlahTamuMenginap(): int
    {
        return $this->jumlahTamuMenginap;
    }

    public function tampilkan(): void
    {
        echo "Nomor Kamar     : {$this->nomorKamar}\n";
        echo "Tipe Kamar      : {$this->tipeKamar}\n";
        echo "Kapasitas Tamu  : {$this->kapasitasTamu}\n";
        echo "Harga per Malam : Rp{$this->hargaPerMalam}\n";
        echo "Tamu Menginap   : {$this->jumlahTamuMenginap}\n";
    }
}