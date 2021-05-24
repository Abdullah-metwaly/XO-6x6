package Game.state;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class LetterTest {


    @Test
    void testToString() {
        Letter letter =new Letter(2,2);
        assertEquals("EMPTY", letter.toString());
        letter.setStatus(2);
        assertEquals("O", letter.toString());
        letter.setStatus(1);
        assertEquals("X", letter.toString());
    }
}
