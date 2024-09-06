/** 
* 
* @author Yunus Emre Kara yunus.kara3@ogr.sakarya.edu.tr
* @since Nisan,2024 
* <p> 
*  Ana çalıştırma noktasıdır. 
*  Kullanıcıdan bir depo linki alır, depoyu klonlar, analiz yapar ve sonuçları ekrana yazdırır.
* </p> 
*/ 

package pkt_pdp_proje;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		//1. Kullanıcıdan link alınan kısım
		Scanner scanner = new Scanner(System.in);
        System.out.println("Lütfen bir depo linki giriniz:");
        String girdi = scanner.nextLine();
        scanner.close();
        
        //2. Linkteki depoyu klonlama
        DepoGetirme depogtr = new DepoGetirme(girdi);
        depogtr.depoyuKlonla();
        
		//3. Klonlanan deponun analizi. 
        Analiz javadosya = new Analiz(); // Analiz yapılacak fonksiyonları kullanabilmek için nesne oluşturuldu.
        List<File> javaDosyaListesi = new ArrayList<>(); // .java uzantılı dosyalar File tipinde bir listede tutuldu.
        javaDosyaListesi=javadosya.dosyaAnaliz(girdi);	
        javaDosyaListesi=javadosya.javaSiniflariniTara(javaDosyaListesi);
        List<Ozellikler> doluOzelliklerNesneleriListesi = new ArrayList<>();	
        // Analiz edilen her bir java dosyası için Ozellikler sınıfından bir nesne üretildi.
        // Bu nesneler doluOzelliklerNesneleriListesi'nde tutuldu.
        doluOzelliklerNesneleriListesi=javadosya.javaDosyasiOzellikBulucusu(javaDosyaListesi);
        
        //4. Analizin ekrana basılması
        Yazdir yaz = new Yazdir();
        yaz.ListeYazdir(doluOzelliklerNesneleriListesi); // İlgili liste uygun şekilde ekrana basıldı.
	}
}
