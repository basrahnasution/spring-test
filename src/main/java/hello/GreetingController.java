package hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    
    @Autowired
    private Greeting x;
    
    @Autowired
    private EntityManagerFactory em;
    
    @RequestMapping("/actors")
    public List<Actor> allActors(){
    	return em.createEntityManager().createQuery("from Actor").getResultList();
    }
    
    

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World Basrah") String name) {
        return x;
    }
    
    @RequestMapping("/data")
    public List<String> dataNegara(@RequestParam("pre")String prefix){
    	List<String> data=new ArrayList<>();
    	data.add("Indonesia");
    	data.add("Malaysia");
    	data.add("Brunei");
    	data.add("Timor Leste");
    	
    	return data.stream().filter(line->line.startsWith(prefix)).collect(Collectors.toList());
    }
    
    @RequestMapping("/countries")
    public String getCountries() throws IOException{
    URL url=new URL("http://www.webservicex.net/globalweather.asmx/GetCitiesByCountry");
    URLConnection connection=url.openConnection();
   // connection.setDoOutput(true);
    connection.setRequestProperty("Content-Type", "aplication/x-www-form-urlencoded");
    connection.setRequestProperty("Content-Length", "0");
    
    InputStream stream=url.openConnection().getInputStream(); //stream adalah aliran data
    InputStreamReader reader=new InputStreamReader(stream); //untuk membaca aliran data stream
    BufferedReader buffer=new BufferedReader(reader);
    
    String line;
    StringBuilder builder= new StringBuilder();
    while ((line=buffer.readLine()) !=null) {
    	builder.append(line);
    }
	return builder.toString();
    
    //return new BufferedReader(InputStreamReader(stream)).lines().collect(collector.joining("\n"));
    }
}
