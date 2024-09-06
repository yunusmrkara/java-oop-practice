/** 
* 
* @author Yunus Emre Kara yunus.kara3@ogr.sakarya.edu.tr
* @since Nisan,2024 
* <p> 
*  Bu sınıf, her bir Java dosyasının özelliklerini hesaplar ve saklar. 
*  Bu özellikler arasında javadoc yorum satırı sayısı, diğer yorum satırı sayısı, kod satırı sayısı, 
*  LOC (Toplam Satır Sayısı), fonksiyon sayısı, yorum sapma yüzdesi ve sınıf adı bulunur.
* </p> 
*/ 

package pkt_pdp_proje;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Ozellikler {
	
	int javadocYorumSatiriSayisi=0; 
	int digerYorumSatiriSayisi=0;   
	int kodSatirSayisi=0;           
	int lineOfCode=0;              
	int fonksiyonSayisi=0;          
	double yorumSapmaYuzdesi=0;    
	String classAdi="";     
	
	public int javadocYorumSatiriSayisiBul(File file) {
	    
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	        String line;
	        boolean javadocIcerisinde = false;

	        while ((line = reader.readLine()) != null) {
	            line = line.trim();

	            if (line.startsWith("/**")) {
	                javadocIcerisinde = true;
	                continue; // Javadoc başlangıcı satırını yorum satırı olarak saymıyoruz.
	            }
	            if (line.startsWith("*") && javadocIcerisinde) {
	                javadocYorumSatiriSayisi++;
	            }
	            if (line.startsWith("*/") && javadocIcerisinde) {
	            	javadocYorumSatiriSayisi--;//Satır başında * olduğundan fazladan saydığımız 1 satırı siliyoruz.
	            	javadocIcerisinde = false;
	                continue; // Javadoc bitiş satırını yorum satırı olarak saymıyoruz.
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return javadocYorumSatiriSayisi;
	}
	public int digerYorumSatiriSayisiniBul(File file) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	        String line;

	        while ((line = reader.readLine()) != null) {
	            line = line.trim();

	            // Satır içerisinde "//" geçiyorsa, yorum satırı olarak kabul edilir.
	            if (line.contains("//")) {
	                digerYorumSatiriSayisi++;
	            } else if (line.startsWith("/*") && !line.startsWith("/**") && !line.endsWith("*/")) {
	                // Eğer satır "/*" ile başlıyorsa ve "*/" ile bitmiyorsa, çok satırlı bir yorumun içindeyizdir.
	                digerYorumSatiriSayisi++;
	            } 
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return digerYorumSatiriSayisi;
}
	public int kodSatirSayisiniBul(File file) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	        String line;

	        while ((line = reader.readLine()) != null) {
	     
	        	line = line.trim();
	            if (!line.isEmpty() && !line.startsWith("//") && !line.startsWith("/*") &&  !line.startsWith("/**") && !line.startsWith("*") &&!line.endsWith("*/")) {
	                kodSatirSayisi++;
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	return kodSatirSayisi;
}
	public int lineOfCodeBul(File file) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	        while (reader.readLine() != null) {
	            lineOfCode++;
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
	return lineOfCode;
}
	public int fonksiyonSayisiniBul(File file) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	        String line;
	        boolean fonksiyonIcerisinde = false;
	        int fonksiyonSayisi = 0;

	        while ((line = reader.readLine()) != null) {
	            line = line.trim();

	            if ((line.startsWith("public") || line.startsWith("private") || line.startsWith("protected")) && line.contains("(") && line.contains(")")) {
	                fonksiyonIcerisinde = true;
	                fonksiyonSayisi++;
	            }

	            if (line.contains("}") && fonksiyonIcerisinde) {
	                fonksiyonIcerisinde = false;
	            }
	        }

	        return fonksiyonSayisi;
	    } catch (IOException e) {
	        e.printStackTrace();
	    }    
	return fonksiyonSayisi;
}
	public double yorumSapmaYuzdesiniBul(int javadocTorumSatiriSayisi,int digerYorumSatiriSayisi,int kodSatirSayisi,int fonksiyonSayisi) {
		
		double YG=((javadocTorumSatiriSayisi+digerYorumSatiriSayisi)*0.8)/fonksiyonSayisi;
		double YH=((double)kodSatirSayisi/fonksiyonSayisi)*0.3;
		
		yorumSapmaYuzdesi=((100*YG)/YH)-100;
		yorumSapmaYuzdesi = Math.round(yorumSapmaYuzdesi * 100.0) / 100.0;
		
	return yorumSapmaYuzdesi;
}	
	public String classAdiniBul(File file) {
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim(); // Satırın başındaki ve sonundaki boşlukları temizle
                if (line.startsWith("public class ")) {
                    classAdi = line.substring(13).trim();
                    int index = classAdi.indexOf(" ");
                    if (index != -1) {
                        classAdi = classAdi.substring(0, index);
                    }
                    break;                 
                    }
            }
        } catch (IOException e) {
            System.out.println("Dosya okunurken bir hata oluştu: " + e.getMessage());
        }
		
        return classAdi;
    
    }
		
	public int getJavadocYorumSatiriSayisi() {
		return javadocYorumSatiriSayisi;
	}
	public void setJavadocYorumSatiriSayisi(int javadocYorumSatiriSayisi) {
		this.javadocYorumSatiriSayisi = javadocYorumSatiriSayisi;
	}
	public int getDigerYorumSatiriSayisi() {
		return digerYorumSatiriSayisi;
	}
	public void setDigerYorumSatiriSayisi(int digerYorumSatiriSayisi) {	
		this.digerYorumSatiriSayisi = digerYorumSatiriSayisi;
	}
	public int getKodSatirSayisi() {
		return kodSatirSayisi;
	}
	public void setKodSatirSayisi(int kodSatirSayisi) {
		this.kodSatirSayisi = kodSatirSayisi;
	}
	public int getLineOfCode() {
		return lineOfCode;
	}
	public void setLineOfCode(int lineOfCode) {
		this.lineOfCode = lineOfCode;
	}
	public int getFonksiyonSayisi() {
		return fonksiyonSayisi;
	}
	public void setFonksiyonSayisi(int fonksiyonSayisi) {
		this.fonksiyonSayisi = fonksiyonSayisi;
	}
	public double getYorumSapmaYuzdesi() {
		return yorumSapmaYuzdesi;
	}
	public void setYorumSapmaYuzdesi(double yorumSapmaYuzdesi) {
		this.yorumSapmaYuzdesi = yorumSapmaYuzdesi;
	}
	public String setClassAdi(String classAdi) {	
		this.classAdi=classAdi;
		return classAdi;
	}
	public String getClassAdi() {		
		return classAdi;
	}
}
