/** 
* 
* @author Yunus Emre Kara yunus.kara3@ogr.sakarya.edu.tr
* @since Nisan,2024 
* <p> 
*  Bu sınıf, analiz sonuçlarını ekrana yazdırır. 
*  Analiz edilen her bir Java dosyası için bulunan özellikleri kullanıcıya sunar.
* </p> 
*/ 

package pkt_pdp_proje;

import java.util.List;

public class Yazdir {
	
	// Yazdırılacak nesneler liste halinde alındı ve uygun şekilde ekrana basıldı.
	
    public void ListeYazdir(List<Ozellikler> dosyaListesi) {
    	for (Ozellikler dosya : dosyaListesi) {
       
    		String sinifAdi=dosya.getClassAdi();
            int javadocSatirSayisi = dosya.getJavadocYorumSatiriSayisi();
            int digerYorumSatirSayisi = dosya.getDigerYorumSatiriSayisi();
            int kodSatirSayisi = dosya.getKodSatirSayisi();
            int loc = dosya.getLineOfCode();
            int fonksiyonSayisi = dosya.getFonksiyonSayisi();
            double yorumSapmaYuzdesi = dosya.yorumSapmaYuzdesiniBul(javadocSatirSayisi, digerYorumSatirSayisi, kodSatirSayisi, fonksiyonSayisi);

            System.out.println("Sınıf: " + sinifAdi+".java");
            System.out.println("Javadoc Satır Sayısı: " + javadocSatirSayisi);
            System.out.println("Yorum Satır Sayısı: " + digerYorumSatirSayisi);
            System.out.println("Kod Satır Sayısı: " + kodSatirSayisi);
            System.out.println("LOC: " + loc);
            System.out.println("Fonksiyon Sayısı: " + fonksiyonSayisi);
            System.out.println("Yorum Sapma Yüzdesi: %" + yorumSapmaYuzdesi);

            System.out.println("----------------------------");
        }
    }
}
