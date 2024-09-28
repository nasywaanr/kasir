import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AplikasiKasir {
    // File untuk menyimpan transaksi
    static String fileName = "transaksi.txt";

    // Fungsi rekursif untuk mengelola transaksi kasir
    public static void kasir(double total) throws IOException {
        Scanner scanner = new Scanner(System.in);

        // Tampilkan total sementara
        System.out.println("Total sementara: " + total);

        // Minta input nama item
        System.out.print("Masukkan nama item (ketik 'selesai' untuk mengakhiri): ");
        String item = scanner.nextLine();

        // Base case: jika pengguna mengetik "selesai", akhiri transaksi
        if (item.equalsIgnoreCase("selesai")) {
            System.out.println("Transaksi selesai. Total yang harus dibayar: " + total);

            // Tambahkan total ke file transaksi
            FileWriter writer = new FileWriter(fileName, true);
            writer.write("\nTotal: " + total + "\n");
            writer.close();
            return;
        }

        // Minta input harga dan jumlah item
        System.out.print("Masukkan harga item: ");
        double harga = scanner.nextDouble();
        System.out.print("Masukkan jumlah item: ");
        int jumlah = scanner.nextInt();
        scanner.nextLine(); // Konsumsi newline

        // Hitung subtotal
        double subtotal = harga * jumlah;

        // Tambahkan item ke file transaksi
        FileWriter writer = new FileWriter(fileName, true);
        writer.write("Item: " + item + ", Harga: " + harga + ", Jumlah: " + jumlah + ", Subtotal: " + subtotal + "\n");
        writer.close();

        // Rekursi untuk menambah item lagi
        kasir(total + subtotal);
    }

    public static void main(String[] args) {
        try {
            // Buat file transaksi baru jika belum adap
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            // Mulai proses transaksi
            kasir(0.0);
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }
}
