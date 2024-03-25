package ch.zhaw.iwi.devops.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
