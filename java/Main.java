public class Main {
    public static void main(String[] args) {

        System.out.println("=== 1. Objek Valid Dibuat ===");
        HotelRoom kamar = new HotelRoom("101", "Deluxe", 2, 500000);
        kamar.tampilkan();

        System.out.println("\n=== 2. Perubahan Sah: Check-in 2 Tamu ===");
        kamar.checkIn(2);
        kamar.tampilkan();

        System.out.println("\n=== 3. Operasi Tidak Sah #1: Check-in Melebihi Kapasitas ===");
        try {
            kamar.checkIn(1);
            System.out.println("GAGAL: seharusnya operasi ini ditolak!");
        } catch (IllegalStateException e) {
            System.out.println("Ditolak sesuai harapan -> " + e.getMessage());
        }

        System.out.println("\n=== 4. Operasi Tidak Sah #2: Ubah Harga Jadi Negatif ===");
        try {
            kamar.ubahHarga(-100000);
            System.out.println("GAGAL: seharusnya operasi ini ditolak!");
        } catch (IllegalArgumentException e) {
            System.out.println("Ditolak sesuai harapan -> " + e.getMessage());
        }

        System.out.println("\n=== 5. Kondisi Akhir Objek (Invarian Tetap Terjaga) ===");
        kamar.tampilkan();
    }
}
