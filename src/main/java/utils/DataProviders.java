package utils;

import model.Contact;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {

    @DataProvider
    public Iterator<Object[]> newContact() {

        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Oliver", "Kan", "987654321", "kan@gm.com", "Berlin", "goalkiper"});
        list.add(new Object[]{"Oliver1", "Kan", "9876543288", "kan+1@gm.com", "Berlin", "goalkiper"});
        list.add(new Object[]{"Oliver2", "Kan", "9876543299", "kan+1@gm.com", "Berlin", "goalkiper"});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> newContactWithCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
        String line = reader.readLine();

        while (line != null) {
            String[] split = line.split(",");

            list.add(new Object[]{new Contact().setName(split[0])
                    .setSurName(split[1]).setPhone(split[2]).setEmail(split[3])
                    .setAddress(split[4]).setDescription(split[5])});
            line = reader.readLine();
        }

        return list.iterator();
    }
}
