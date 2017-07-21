package ua.usstandart.avayastatformer.filesystem;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class CsvFileReader {

    @Value( "${filepath}" )
    private String filePath;

    @Value("${separator}")
    private String separator;

    private BufferedReader br = null;

    public synchronized List<String[]> readData(Date date){
        String line;
        List<String[]>result = new ArrayList<>();
        String fullFilePath = modifyFilePath(filePath,date);

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(fullFilePath), Charset.forName("windows-1251")));
            while ((line = br.readLine()) != null) {
                String[]formedLine = line.split(separator);
                result.add(formedLine);
            }
        }

        catch (IOException ex){
            System.out.println(fullFilePath+" can't open file!");
            return null;
        }

        finally {
            if (br!=null){
                try {
                    br.close();
                }
                catch (IOException ex){
                    System.out.println(filePath+" can't close file");
                }
            }
        }

        return result;
    }

    private String modifyFilePath(String initPath, Date date){
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        return initPath+"day(backup"+month+"-"+day+"-"+year+").csv";
    }
}
