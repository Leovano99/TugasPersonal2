import java.text.SimpleDateFormat;
import java.util.Date;

public class Pasien {
    private String nama;
    private String alamat;
    private long NIK;
    private Date tanggalLahir;

    public Pasien(String nama, String alamat, long NIK, Date tanggalLahir) {
        this.nama = nama;
        this.alamat = alamat;
        this.NIK = NIK;
        this.tanggalLahir = tanggalLahir;
    }

    // Getter dan Setter untuk semua atribut

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public long getNIK() {
        return NIK;
    }

    public void setNIK(long NIK) {
        this.NIK = NIK;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
        return "Nama: " + nama + ", NIK: " + NIK + ", Tanggal Lahir: " + sdf.format(tanggalLahir) + ", Alamat: " + alamat;
    }
}
