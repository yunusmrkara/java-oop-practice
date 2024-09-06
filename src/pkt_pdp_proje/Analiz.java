/** 
* 
* @author Yunus Emre Kara yunus.kara3@ogr.sakarya.edu.tr
* @since Nisan,2024 
* <p> 
*  Bu sınıf, bir GitHub deposundan klonlanan Java projesinin analizini yapar. 
*  Kullanıcıdan alınan depo linkiyle depoyu klonlar, ardından Java dosyalarını tarar ve analiz eder. 
*  Son olarak, analiz sonuçlarını elde eder. 
* </p> 
*/ 

package pkt_pdp_proje;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Analiz {
	
	/*
	 * Bu fonksiyon kullanıcıdan alınan dizindeki dosyaları analiz eder.
	 * 
	 */
	
	public List<File> dosyaAnaliz(String girdi) {
	    
		List<File> javaDosyaListesi = new ArrayList<>();
	    
	    String[] linkParcalari = girdi.split("/");
	    String klasorIsmi = linkParcalari[linkParcalari.length - 1];
	    String anlikDizin = System.getProperty("user.dir") + File.separator + klasorIsmi;
	    File dizin = new File(anlikDizin); 

	    if (dizin.exists() && dizin.isDirectory()) {
	        javaDosyalariniTara(dizin, javaDosyaListesi);
	    } else {
	        System.out.println("Mevcut dizin bulunamadı veya bir klasör değil.");
	    }
	    
	    return javaDosyaListesi;
	} 
	
	/*
	 * Bu fonksiyon ilgili dizindeki tüm dosyaları tarayarak .java uzantılı olanları
	 * javaDosyaListesi'ne ekler.
	 */
	
	private void javaDosyalariniTara(File dizin, List<File> javaDosyaListesi) {
	    File[] dosyalar = dizin.listFiles();
	    
	    if (dosyalar != null) {
	        for (File dosya : dosyalar) {
	            if (dosya.isFile() && dosya.getName().endsWith(".java")) {
	                javaDosyaListesi.add(dosya);
	            } else if (dosya.isDirectory()) {
	                javaDosyalariniTara(dosya, javaDosyaListesi);
	            }
	        }
	    }
	}
	
	/*
	 * Bu fonksiyon .java uzantılı dosyaları bir listede parametre olarak alır ve  
	 * içerisinde sınıf bulunan dosyaları bulup bir listede döndürür.
	 */
	public List<File> javaSiniflariniTara(List<File> javaDosyaListesi) {
	    List<File> sadeceSinifDosyalari = new ArrayList<>();
	    
	    for (File file : javaDosyaListesi) {
	        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	            String line;
	            boolean sinifBulundu = false;

	            while ((line = reader.readLine()) != null) {
	                if (line.contains("class")) {
	                    sinifBulundu = true;
	                    break;
	                }
	            }

	            if (sinifBulundu) {
	                sadeceSinifDosyalari.add(file);
	            }
	        } catch (IOException e) {
	            System.out.println("Dosya okunurken bir hata oluştu: " + e.getMessage());
	        }
	    }
	    
	    return sadeceSinifDosyalari;
	}
	
	/* 
	 * Bu fonksiyon seçilmiş java dosyalarından oluşturulmuş File tipinde bir Listeyi parametre olarak alır ve listenin her bir
	 * elemanı için Ozellikler sınıfından bir nesne üretir. Bu nesnenin değişkenlerine, Ozellikler sınıfında yazılmış olan fonksiyonlar
	 * kullanılarak projede istenen değerler atanmıştır.
	 * 
	 * Sonuç itibariyle seçilen her bir java dosyası için gereken taramalar yapılmış ve 
	 * her bir java dosyası için gereken bilgiler ilgili nesnede tutulmuştur. Fonksiyon bu nesneleri bir 
	 * Liste olarak döndürmüştür.
	 */
	
	public List<Ozellikler> javaDosyasiOzellikBulucusu(List<File> javaDosyaListesi) {
	   
		List<Ozellikler> ozelliklerListesi = new ArrayList<>();

	    for (File dosya : javaDosyaListesi) {
	        Ozellikler gecerliDosyaOzellikleri = new Ozellikler();
	        
	        gecerliDosyaOzellikleri.setJavadocYorumSatiriSayisi(gecerliDosyaOzellikleri.javadocYorumSatiriSayisiBul(dosya));
	        gecerliDosyaOzellikleri.setDigerYorumSatiriSayisi(gecerliDosyaOzellikleri.digerYorumSatiriSayisiniBul(dosya));
	        gecerliDosyaOzellikleri.setKodSatirSayisi(gecerliDosyaOzellikleri.kodSatirSayisiniBul(dosya));
	        gecerliDosyaOzellikleri.setLineOfCode(gecerliDosyaOzellikleri.lineOfCodeBul(dosya));
	        gecerliDosyaOzellikleri.setFonksiyonSayisi(gecerliDosyaOzellikleri.fonksiyonSayisiniBul(dosya));
	        gecerliDosyaOzellikleri.setYorumSapmaYuzdesi(gecerliDosyaOzellikleri.getYorumSapmaYuzdesi());
	        gecerliDosyaOzellikleri.setClassAdi(gecerliDosyaOzellikleri.classAdiniBul(dosya));
	        
	        ozelliklerListesi.add(gecerliDosyaOzellikleri);
	    }
	    
	    return ozelliklerListesi;
	}

}


