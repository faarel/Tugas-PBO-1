/**
 * Class HotelRoom merepresentasikan satu kamar di sebuah hotel.
 *
 * Invarian yang dijaga sepanjang hidup objek:
 *  1) hargaPerMalam harus selalu > 0 (tidak boleh nol atau negatif).
 *  2) jumlahTamuMenginap tidak boleh negatif dan tidak boleh melebihi kapasitasTamu.
 *
 * Field nomorKamar, tipeKamar, dan kapasitasTamu dideklarasikan final:
 * sekali diisi lewat constructor, tidak bisa diubah lagi (identitas kamar tetap).
 * Tidak ada setter untuk hargaPerMalam maupun jumlahTamuMenginap karena keduanya
 * punya invarian — perubahan hanya boleh lewat method yang memvalidasi (checkIn,
 * checkOut, ubahHarga), bukan lewat penulisan langsung ke field.
 */
public class HotelRoom {

    private final String nomorKamar;
    private final String tipeKamar;
    private final int kapasitasTamu;
    private double hargaPerMalam;
    private int jumlahTamuMenginap;

    public HotelRoom(String nomorKamar, String tipeKamar, int kapasitasTamu, double hargaPerMalam) {
        if (kapasitasTamu <= 0) {
            throw new IllegalArgumentException("Kapasitas tamu harus lebih besar dari nol");
        }
        if (hargaPerMalam <= 0) {
            throw new IllegalArgumentException("Harga per malam harus lebih besar dari nol");
        }
        this.nomorKamar = nomorKamar;
        this.tipeKamar = tipeKamar;
        this.kapasitasTamu = kapasitasTamu;
        this.hargaPerMalam = hargaPerMalam;
        this.jumlahTamuMenginap = 0;
    }

    /**
     * Menambahkan tamu yang check-in ke kamar ini.
     * Menegakkan invarian #2: total tamu tidak boleh melebihi kapasitas.
     */
    public void checkIn(int jumlahTamu) {
        if (jumlahTamu <= 0) {
            throw new IllegalArgumentException("Jumlah tamu check-in harus lebih besar dari nol");
        }
        if (this.jumlahTamuMenginap + jumlahTamu > this.kapasitasTamu) {
            throw new IllegalStateException(
                "Jumlah tamu melebihi kapasitas kamar (kapasitas: " + this.kapasitasTamu + ")"
            );
        }
        this.jumlahTamuMenginap += jumlahTamu;
    }

    /**
     * Mengosongkan kamar. Menolak jika memang sudah tidak ada tamu,
     * supaya jumlahTamuMenginap tidak pernah bisa jadi negatif (invarian #2).
     */
    public void checkOut() {
        if (this.jumlahTamuMenginap == 0) {
            throw new IllegalStateException("Tidak ada tamu untuk check-out, kamar sudah kosong");
        }
        this.jumlahTamuMenginap = 0;
    }

    /**
     * Mengubah harga per malam. Menegakkan invarian #1.
     */
    public void ubahHarga(double hargaBaru) {
        if (hargaBaru <= 0) {
            throw new IllegalArgumentException("Harga per malam harus lebih besar dari nol");
        }
        this.hargaPerMalam = hargaBaru;
    }

    public double getHargaPerMalam() {
        return this.hargaPerMalam;
    }

    public int getJumlahTamuMenginap() {
        return this.jumlahTamuMenginap;
    }

    public void tampilkan() {
        System.out.println("Nomor Kamar     : " + this.nomorKamar);
        System.out.println("Tipe Kamar      : " + this.tipeKamar);
        System.out.println("Kapasitas Tamu  : " + this.kapasitasTamu);
        System.out.println("Harga per Malam : Rp" + this.hargaPerMalam);
        System.out.println("Tamu Menginap   : " + this.jumlahTamuMenginap);
    }
}
