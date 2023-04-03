package pl.pfigura.testing.order;

import lombok.Getter;

import java.io.*;

@Getter
class OrderBackup {
    private Writer writer;

    void createFile() throws FileNotFoundException {
        File file = new File("orderBackup.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        writer = new BufferedWriter(outputStreamWriter);
    }

    void backupOrder(Order order) throws IOException {
        writer.append(order.toString());
    }

    void closeFile() throws IOException {
        writer.close();
    }
}
