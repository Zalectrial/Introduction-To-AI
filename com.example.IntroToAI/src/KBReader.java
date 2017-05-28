/*
 * Copyright (c) 2017. Robyn Van Deventer.
 * Created for Swinburne University of Technology.
 */

import java.io.BufferedReader;
import java.io.FileReader;

class KBReader {

    private String fileName;
    private String tell;
    private String ask;

    KBReader(String fileName) {

        this.fileName = fileName;
        readFile();
    }

    private void readFile() {

        // Read the file and extract the ask and tell
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            int index = 0;

            // read the file while the lines aren't null and the length is less than 4
            while ((line = reader.readLine()) != null && index < 4) {

                if (index == 1) {
                    tell = line;
                }
                else if (index == 3) {
                    ask = line;
                }

                index++;
            }

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    // return the tell
    String getTell() {
        return this.tell;
    }

    // return the ask
    String getAsk() {
        return this.ask;
    }
}
