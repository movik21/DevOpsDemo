package ch.zhaw.iwi.devops.demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class MyNotesController {

    private Map<Integer, MyNotes> mynote = new HashMap<Integer, MyNotes>();

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        this.mynote.put(1,new MyNotes(1, "Ein wichtiger Gedanke", "WIE erstelle ich eine neue Java Klasse?"));
        this.mynote.put(2,new MyNotes(2, "2ter Gedanke", "WARUM erstelle ich eine neue Java Klasse?"));
        this.mynote.put(3,new MyNotes(3, "3er Gedanke", "Ich erstelle eine neue Java Klasse, um wichtige Gedanken zu notieren?"));
        System.out.println("Init Data");
    }

    @GetMapping("/test")
    public String test() {
        return "MyNotes app is up and running!";
    }

    @GetMapping("/services/ping")
    public String ping() {
        String languageCode = "de";
        return "{ \"status\": \"ok\", \"userId\": \"admin"+ "\", \"languageCode\": \"" + languageCode + "\",\"version\": \"0.0.1" + "\"}";
    }

    @GetMapping("/count")
    public int count() {
        return this.mynote.size();
    }

    @GetMapping("/services/mynote")
    public List<PathListEntry<Integer>> mynote() {
        var result = new ArrayList<PathListEntry<Integer>>();
        for (var mynote : this.mynote.values()) {
            var entry = new PathListEntry<Integer>();
            entry.setKey(mynote.getId(), "mynoteKey");
            entry.setName(mynote.getTitle());
            entry.getDetails().add(mynote.getDescription());
            entry.setTooltip(mynote.getDescription());
            result.add(entry);
        }
        return result;
    }

    @GetMapping("/services/mynote/{key}")
    public MyNotes getMyNote(@PathVariable Integer key) {
        return this.mynote.get(key);
    }

    @PostMapping("/services/mynote")
    public void createMyNote(@RequestBody MyNotes mynote) {
        var newId = this.mynote.keySet().stream().max(Comparator.naturalOrder()).orElse(0) + 1;
        mynote.setId(newId);
        this.mynote.put(newId, mynote);
    }

    @PutMapping("/services/mynote/{id}")
    public void createMyNote(@PathVariable Integer id, @RequestBody MyNotes mynote) {
        mynote.setId(id);
        this.mynote.put(id, mynote);
    }

    @DeleteMapping("/services/mynote/{key}")
    public MyNotes deleteMyNote(@PathVariable Integer key) {
        return this.mynote.remove(key);
    }

    
}
