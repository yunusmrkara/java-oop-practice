/** 
* 
* @author Yunus Emre Kara yunus.kara3@ogr.sakarya.edu.tr
* @since Nisan,2024 
* <p> 
*  Bu sınıf, bir GitHub depo linki alır ve bu linkteki depoyu klonlar. 
*  Klonlama işlemi, verilen linkteki depoyu kullanıcının bilgisayarına indirir.
* </p> 
*/ 

package pkt_pdp_proje;

import java.io.File;
import java.io.IOException;

public class DepoGetirme {
    private String depoLinki;
    
    public DepoGetirme(String depoLinki) {
    	this.depoLinki=depoLinki;
    }
   
    // Alınan linkteki depo proje klasörüne klonlandı.
    public void depoyuKlonla() throws IOException, InterruptedException{
        
        ProcessBuilder processBuilder = new ProcessBuilder("git", "clone", depoLinki);
        processBuilder.directory(new File(System.getProperty("user.dir")));
        Process process = processBuilder.start();
        process.waitFor();

    }
}



	