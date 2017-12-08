package hello;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World Basrah") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping("/data")
    public List<String> dataNegara(@RequestParam("pre")String prefix){
    	List<String> data=new ArrayList<>();
    	data.add("Indonesia");
    	data.add("Malaysia");
    	data.add("Brunei");
    	data.add("Timor Leste");
    	
    	return data.stream().filter(line->line.contains(prefix)).collect(Collectors.toList());
    }
}
