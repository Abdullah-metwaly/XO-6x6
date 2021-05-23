package Game.results;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Duration;
import java.time.ZonedDateTime;

/**
 * Class representing the result of a game played by a specific player.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class GameResult {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * The name of the player one.
     */
    @Column(nullable = false)
    private String playerOne;

    /**
     * The name of the player two.
     */
    @Column(nullable = false)
    private String playerTwo;


    /**
     * The number of steps made by the player one.
     */
    private int stepsPlayerOne;

    /**
     * The number of steps made by the player one.
     */
    private int stepsPlayerTwo;
    /**
     * The name of the winner.
     */
    @Column(nullable = false)
    private String winner;


    /**
     * The duration of the game.
     */
    @Column(nullable = false)
    private Duration duration;


    /**
     * The timestamp when the result was saved.
     */
    @Column(nullable = false)
    private ZonedDateTime created;

    @PrePersist
    protected void onPersist() {
        created = ZonedDateTime.now();
    }

}
