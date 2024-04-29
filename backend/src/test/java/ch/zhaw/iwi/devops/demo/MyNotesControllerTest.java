package ch.zhaw.iwi.devops.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MyNotesControllerTest {
    
    @Test
    public void testCreate() {
        MyNotesController controller = new MyNotesController();
        var mynote = new MyNotes(1, "t", "d");
        controller.createMyNote(1, mynote);
        assertEquals(1, controller.count());
        assertEquals(1, controller.mynote().size());
    }
    @Test
    public void testDelete() {
        MyNotesController controller = new MyNotesController();
        var mynote = new MyNotes(1, "title", "description");
        controller.createMyNote(1, mynote);

        controller.deleteMyNote(1);
        assertEquals(0, controller.count()); 
        assertTrue(controller.mynote().isEmpty());
    }

}
