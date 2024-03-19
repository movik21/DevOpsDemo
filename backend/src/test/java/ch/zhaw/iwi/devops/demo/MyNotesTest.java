package ch.zhaw.iwi.devops.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MyNotesTest {

    @Test
    public void testToDo() {
        var myNote1 = new MyNotes(1, "title", "description");
        assertEquals("title", myNote1.getTitle());
        assertEquals("description", myNote1.getDescription());
        assertEquals(1, myNote1.getId());
    }
    
}
