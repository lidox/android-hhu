package de.hhu.a39database;

import android.database.Cursor;
import android.os.AsyncTask;

public abstract class CatRaceAdderTask extends AsyncTask<Void, Cursor, Void> {
    // Let the caller implement this
    public abstract CatRacesContract.CatRacesDbHelper getDbHelper();

    @Override
    public Void doInBackground(Void... params) {
        // Get the DB helper
        CatRacesContract.CatRacesDbHelper helper = getDbHelper();

        // Add cats, one by one
        delay();
        helper.addCatRace("Bombay Cat", "Felis silvestris catus bombayensis", 10);
        publish();

        delay();
        helper.addCatRace("Persian Cat", "Felis silvestris catus persica", 6);
        publish();

        delay();
        helper.addCatRace("Siamese Cat", "Felis silvestris catus siamensis", 6);
        publish();

        delay();
        helper.addCatRace("Savannah Cat", "Felis silvestris catus savanniensis", 12);
        publish();

        delay();
        helper.addCatRace("Eurasian Lynx", "Lynx lynx", 30);
        publish();

        delay();
        /* We have two criteria for determining if something is a cat:
            - does it carry "cat" in its name?
            - does it have a nose?
           Both questions may be answered with "Yes", hence it's a cat. */
        helper.addCatRace("Cat-5 Wire", "ANSI-568-A Category 5", 0);
        publish();

        delay();
        helper.addCatRace("Cornish Rex", "Felis silvestris catus corniensis", 8);
        publish();

        delay();
        helper.addCatRace("African Wild Cat", "Felis silvestris libyca", 6);
        publish();

        return null;
    }

    // Do some delay...
    private void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Fetch current State-of-the-Cat and publish progress
    private void publish() {
        publishProgress(getDbHelper().getAllCatRaces());
    }
}
