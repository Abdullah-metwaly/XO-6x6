package Game.state;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class GameStateTest {


    @Test
    void testGameStateByteArrayArrayValidArgument() {

        GameState StoneGame = new GameState(new Player ("john"),new Player("doe"));
        assertEquals(new GameState((new int[][]{
                {0, 0, 0,0,0,0},
                {0, 0, 0,0,0,0},
                {0, 0, 0,0,0,0},
                {0, 0, 0,0,0,0},
                {0, 0, 0,0,0,0},
                {0, 0, 0,0,0,0}
        })).toString(),StoneGame.toString());
    }

    @Test
    void testIsGameFinished() {
        assertFalse(new GameState().isGameFinished());
        assertFalse(new GameState(new int[][] {
                {0, 2, 2,2,0,0},
                {1, 0, 2,1,0,0},
                {1, 1, 1,1,1,0},
                {1, 0, 1,1,0,2},
                {2, 1, 1,1,2,2},
                {2, 2, 1,0,0,2}}).isGameFinished());
        assertFalse(new GameState(new int[][] {
                {0, 0, 0,0,0,2},
                {1, 0, 0,1,2,2},
                {1, 1, 1,1,1,2},
                {1, 0, 1,1,0,2},
                {2, 1, 1,1,2,2},
                {2, 2, 1,0,0,2}}).isGameFinished());
        assertFalse(new GameState(new int[][] {
                {0, 1, 1,1,0,2},
                {0, 0, 1,1,2,2},
                {0, 0, 1,1,1,2},
                {0, 0, 1,1,0,2},
                {2, 1, 1,1,2,2},
                {2, 2, 1,0,0,2}}).isGameFinished());

        assertTrue(new GameState(new int[][] {
                {0, 2, 2,2,0,2},
                {1, 0, 2,1,2,2},
                {1, 1, 1,1,1,2},
                {1, 0, 1,1,0,2},
                {2, 1, 1,1,2,2},
                {2, 2, 1,0,0,2}}).isGameFinished());


    }

    @Test
    void testcanPlayerplay() {
        assertTrue(new GameState().canPlayerplay(0,0));
        assertTrue(new GameState().canPlayerplay(0,5));
        assertTrue(new GameState().canPlayerplay(5,0));
        assertTrue(new GameState().canPlayerplay(5,5));
        assertTrue(new GameState().canPlayerplay(0,2));
        assertTrue(new GameState().canPlayerplay(3,5));
        assertTrue(new GameState().canPlayerplay(3,0));
        assertTrue(new GameState().canPlayerplay(5,3));
        assertTrue(new GameState().canPlayerplay(3,3));

        assertFalse(new GameState().canPlayerplay(-5,3));
        assertFalse(new GameState().canPlayerplay(50,3));

        GameState gameState2 =new GameState(
                new int[][] {
                        {1, 1, 0,0,0,0},
                        {1, 0, 0,0,0,2},
                        {0, 0, 0,0,2,2},
                        {0, 0, 0,0,0,2},
                        {0, 0, 0,0,0,0},
                        {0, 0, 0,0,0,0}});

        assertFalse(gameState2.canPlayerplay(0,0));
        assertFalse(gameState2.canPlayerplay(0,1));
        assertFalse(gameState2.canPlayerplay(1,0));
        assertFalse(gameState2.canPlayerplay(1,1));
        assertFalse(gameState2.canPlayerplay(3,5));


    }



    @Test
    void testPlay(){
        GameState gameState = new GameState();
        gameState.Play(0,0);
        assertEquals(new GameState(
                       new int[][] {
                        {1, 1, 0,0,0,0},
                        {1, 0, 0,0,0,0},
                        {0, 0, 0,0,0,0},
                        {0, 0, 0,0,0,0},
                        {0, 0, 0,0,0,0},
                        {0, 0, 0,0,0,0}}).toString(),gameState.toString());
        gameState.Play(0,3);

        assertEquals(new GameState(
                new int[][] {
                        {1, 1, 2,2,2,0},
                        {1, 0, 0,2,0,0},
                        {0, 0, 0,0,0,0},
                        {0, 0, 0,0,0,0},
                        {0, 0, 0,0,0,0},
                        {0, 0, 0,0,0,0}}).toString(),gameState.toString());
        gameState.Play(3,3);

        assertEquals(new GameState(
                new int[][] {
                        {1, 1, 2,2,2,0},
                        {1, 0, 0,2,0,0},
                        {0, 0, 0,1,0,0},
                        {0, 0, 1,1,1,0},
                        {0, 0, 0,1,0,0},
                        {0, 0, 0,0,0,0}}).toString(),gameState.toString());
        gameState.Play(4,0);

        assertEquals(new GameState(
                new int[][] {
                        {1, 1, 2,2,2,0},
                        {1, 0, 0,2,0,0},
                        {0, 0, 0,1,0,0},
                        {2, 0, 1,1,1,0},
                        {2, 2, 0,1,0,0},
                        {2, 0, 0,0,0,0}}).toString(),gameState.toString());

        gameState.Play(5,2);
        assertEquals(new GameState(
                new int[][] {
                        {1, 1, 2,2,2,0},
                        {1, 0, 0,2,0,0},
                        {0, 0, 0,1,0,0},
                        {2, 0, 1,1,1,0},
                        {2, 2, 1,1,0,0},
                        {2, 1, 1,1,0,0}}).toString(),gameState.toString());
    }

    @Test
    void testToString() {
        GameState StoneGame = new GameState();
        assertEquals("EMPTY EMPTY EMPTY EMPTY EMPTY EMPTY \n"
                     + "EMPTY EMPTY EMPTY EMPTY EMPTY EMPTY \n"
                     + "EMPTY EMPTY EMPTY EMPTY EMPTY EMPTY \n"
                     + "EMPTY EMPTY EMPTY EMPTY EMPTY EMPTY \n"
                     + "EMPTY EMPTY EMPTY EMPTY EMPTY EMPTY \n"
                     + "EMPTY EMPTY EMPTY EMPTY EMPTY EMPTY \n", StoneGame.toString());
        GameState StoneGame2 = new GameState(new int[][] {
                {0, 0, 0,0,0,0},
                {0, 0, 0,1,0,0},
                {0, 0, 1,1,1,0},
                {0, 0, 2,1,0,0},
                {0, 2, 2,2,0,0},
                {0, 0, 2,0,0,0}});
        assertEquals("EMPTY EMPTY EMPTY EMPTY EMPTY EMPTY \n"
                     + "EMPTY EMPTY EMPTY X EMPTY EMPTY \n"
                     + "EMPTY EMPTY X X X EMPTY \n"
                     + "EMPTY EMPTY O X EMPTY EMPTY \n"
                     + "EMPTY O O O EMPTY EMPTY \n"
                     + "EMPTY EMPTY O EMPTY EMPTY EMPTY \n", StoneGame2.toString());
    }
}

