package com.example.android.courtcounterpro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int scoreTeamA; //Tracks the score of Team A
    int scoreTeamB; //Tracks the score of Team B
    boolean currentTeam; // Tracks last updated team: true = Team A and false = Team B
    ArrayList<Integer> priorScoreA;
    ArrayList<Integer> priorScoreB;
    TextView scoreViewA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreTeamA = 0; // Set Team A Score to 0
        scoreTeamB = 0; // Set Team B Score to 0
        displayForTeamA(0); // Display Team A Score
        displayForTeamB(0); // Display Team B Score
        currentTeam = false; // set initial current team to false aka team B
        priorScoreA = new ArrayList(); //initialize priorScoreA ArrayList
        priorScoreA.add(0); // Set priorScoreA ArrayList to [0]
        priorScoreA.add(0); // Set priorScoreA ArrayList to [0]
        priorScoreB = new ArrayList(); //initialize priorScoreB ArrayList
        priorScoreB.add(0); // Set priorScoreB ArrayList to [0]
        priorScoreB.add(0); // Set priorScoreB ArrayList to [0,0]
        scoreViewA = (TextView) findViewById(R.id.team_a_score);
    }

    /**
     * This method is called when the 3 Point button is clicked fir Team A
     */
    public void addThreePointA(View view) {
        scoreTeamA = scoreTeamA + 3;
        displayForTeamA(scoreTeamA);
        //add current score to priorScoreA ArrayList
        //remove oldest score to keep size to 2 values [prior, current]
        priorScoreA.add(scoreTeamA);
        if (priorScoreA.size() > 2) {
            priorScoreA.remove(0);
        }
        currentTeam = true;
    }

    /**
     * This method is called when the 2 Point button is clicked for Team A
     */
    public void addTwoPointA(View view) {
        scoreTeamA = scoreTeamA + 2;
        displayForTeamA(scoreTeamA);

        priorScoreA.add(scoreTeamA);
        if (priorScoreA.size() > 2) {
            priorScoreA.remove(0);
        }
        currentTeam = true;
    }

    /**
     * This method is called when the Free Throw button is clicked for Team A
     */
    public void addFreeThrowA(View view) {
        scoreTeamA = scoreTeamA + 1;
        displayForTeamA(scoreTeamA);

        priorScoreA.add(scoreTeamA);
        if (priorScoreA.size() > 2) {
            priorScoreA.remove(0);
        }
        currentTeam = true;
    }

    /**
     * This method is called when the 3 Point button is clicked for Team B
     */
    public void addThreePointB(View view) {
        scoreTeamB = scoreTeamB + 3;
        displayForTeamB(scoreTeamB);
        //add current score to priorScoreB ArrayList
        //remove oldest score to keep size to 2 values [prior, current]
        priorScoreB.add(scoreTeamB);
        if (priorScoreB.size() > 2) {
            priorScoreB.remove(0);
        }
        currentTeam = false;
    }

    /**
     * This method is called when the 2 Point button is clicked for Team B
     */
    public void addTwoPointB(View view) {
        scoreTeamB = scoreTeamB + 2;
        displayForTeamB(scoreTeamB);

        priorScoreB.add(scoreTeamB);
        if (priorScoreB.size() > 2) {
            priorScoreB.remove(0);
        }
        currentTeam = false;
    }

    /**
     * This method is called when the Free Throw button is clicked for Team B
     */
    public void addFreeThrowB(View view) {
        scoreTeamB = scoreTeamB + 1;
        displayForTeamB(scoreTeamB);

        priorScoreB.add(scoreTeamB);
        if (priorScoreB.size() > 2) {
            priorScoreB.remove(0);
        }
        currentTeam = false;
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreViewA = (TextView) findViewById(R.id.team_a_score);
        scoreViewA.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB(int score) {
        TextView scoreViewB = (TextView) findViewById(R.id.team_b_score);
        scoreViewB.setText(String.valueOf(score));
    }

    /**
     * Resets the scores to 0
     */
    public void resetScores(View view) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA(0);
        displayForTeamB(0);

        //Reset ArrayList to [0,0] if Undo Button was previously hit else [0,0]
        if(priorScoreA.size() == 1 || priorScoreB.size() == 1) {
            priorScoreA.remove(0);
            priorScoreA.add(0, 0);
            priorScoreB.remove(0);
            priorScoreB.add(0,0);

        } else {
            priorScoreA.remove(0);
            priorScoreA.remove(0);
            priorScoreA.add(0, 0);
            priorScoreA.add(1, 0);
            priorScoreB.remove(0);
            priorScoreB.remove(0);
            priorScoreB.add(0, 0);
            priorScoreB.add(1, 0);
        }
    }

    /**
     * Undoes prior score update
     */
    public void undo(View view) {

        if (currentTeam == true) {
            if (priorScoreA.size() > 1) {
                scoreTeamA = priorScoreA.get(0);
                displayForTeamA(scoreTeamA);
                priorScoreA.remove(1);
            } else {
                scoreTeamA = priorScoreA.get(0);
                displayForTeamA(scoreTeamA);
            }
        }
        if (currentTeam == false) {
            if (priorScoreB.size() > 1) {
                scoreTeamB = priorScoreB.get(0);
                displayForTeamB(scoreTeamB);
                priorScoreB.remove(1);
            } else {
                scoreTeamB = priorScoreB.get(0);
                displayForTeamB(scoreTeamB);
            }
        }
    }

}
