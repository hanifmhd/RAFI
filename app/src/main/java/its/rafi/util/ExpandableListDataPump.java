package its.rafi.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by ASUS on 6/9/2017.
 */

public class ExpandableListDataPump {
    public static LinkedHashMap <String, List<String>> getData() {
        LinkedHashMap<String, List<String>> expandableListDetail = new LinkedHashMap <String, List<String>>();

        List<String> no1 = new ArrayList<String>();
        no1.add("Pastikan data terisi dengan lengkap.");
        no1.add("Pastikan tidak ada kolom input yang masih kosong.");
        expandableListDetail.put("Mengapa gagal menyimpan isian data?", no1);

        List<String> no2 = new ArrayList<String>();
        no2.add("Untuk dapat melakukan input dalam radius 500m");
        expandableListDetail.put("Mengapa tidak muncul sekolah/institusi yang dituju?", no2);

        //PROFILING
        List<String> no3 = new ArrayList<String>();
        no3.add("1. Apa itu Profiling?");
        no3.add("Profiling adalah field untuk mengisi isian data sekolah yang meliputi identitas kepala sekolah, identitas PIC atau penanggung jawab sekolah seperti TU, identitas ketua osis serta kelengkapan dari sekolah tersebut.");
        no3.add("2. Apakah bisa update setiap saat?");
        no3.add("Bisa.");
        expandableListDetail.put("PROFILING", no3);

        //MARKET SHARE
        List<String> no4 = new ArrayList<String>();
        no4.add("1. Apa itu Market Share");
        no4.add("Market Share ditujukan untuk mengetahui jenis provider yang digunakan oleh pelajar.");
        no4.add("2. Kenapa gagal saat menginputkan no msisdn?");
        no4.add("Karena No Msisdn hanya dapat di inputkan 1x.");
        expandableListDetail.put("MARKET SHARE", no4);

        //KANTIN
        List<String> no5 = new ArrayList<String>() ;
        no5.add("1. Apa itu Kantin OSS/OSK");
        no5.add("Kantin yang ada di setiap sekolah.");
        no5.add("2. Apa itu branding?");
        no5.add("Berisi foto promo yang ada di kantin.");
        no5.add("3. Apa bisa merubah ID Outlet Sefia?");
        no5.add("Tidak bisa.");
        expandableListDetail.put("KANTIN OSS/OSK", no5);

        //OUTLET
        List<String> no6 = new ArrayList<String>();
        no6.add("1. Apa itu Outlet OSS/OSK?");
        no6.add("Outlet yang ada disekitar sekolah/institusi.");
        no6.add("2. Mengapa daftar OUTLET kosong?");
        no6.add("Karena belum menambahkan outlet, untuk menambahkan pilih sekolah/institusi terdekat.");
        expandableListDetail.put("OUTLET OSS/OSK", no6);

        //MERCHANT 
        List<String> no7 = new ArrayList<String>();
        no7.add("1. Apa itu Merchant?");
        no7.add("Merchant adalah HANGOUT place yang dijadikan sebagai tempat pertemuan oleh anak muda didaerah itu.");
        no7.add("2. Mengapa tidak ada daftar MERCHANT?");
        no7.add("Karena tidak ada merchant pada cluster, untuk menambahkan klik tombol 'Tambah Merchant'.");
        expandableListDetail.put("MERCHANT", no7);

        //NEWDATA USER
        List<String> no8 = new ArrayList<String>();
        no8.add("1. Apa itu New Data User?");
        no8.add("Untuk mengetahui transaksi penjualan paket data di sekolah/institusi.");
        no8.add("2. Mengapa tidak bisa menyimpan NO MSISDN pada new data user?");
        no8.add("Karena NO MSISDN hanya dapat di inputkan 1x.");
        expandableListDetail.put("NEW DATA USER", no8);

        //USERGUIDE
        List<String> no9 = new ArrayList<String>();
        no9.add("1. Apa itu User Guide?");
        no9.add("User Guide adalah panduan untuk menggunakan aplikasi Nami.");
        expandableListDetail.put("USER GUIDE", no9);

        return expandableListDetail;
    }
}
