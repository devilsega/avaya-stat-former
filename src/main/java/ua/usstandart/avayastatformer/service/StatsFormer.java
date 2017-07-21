package ua.usstandart.avayastatformer.service;

import org.springframework.stereotype.Service;
import ua.usstandart.avayastatformer.filesystem.CsvFileReader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StatsFormer {
    private CsvFileReader csvFileReader;

    public StatsFormer(CsvFileReader csvFileReader){
        this.csvFileReader = csvFileReader;
    }

    public List getStats(String number, String heading, Date date){
        List<String[]> rawData = csvFileReader.readData(date);
        List<String[]> finalData = new ArrayList<>();

        try {
            String[]HeadArr = {rawData.get(0)[0],rawData.get(0)[1],rawData.get(0)[3],rawData.get(0)[5]};
            finalData.add(HeadArr);

            switch (heading){
                case "in":{
                    for (String[]item:rawData) {
                        if (item[6].equals(number)){
                            String[]sortedArr = {item[0],item[1],item[3],item[6]};
                            finalData.add(sortedArr);
                        }
                    }
                    break;
                }
                case "out":{
                    for (String[]item:rawData) {
                        if (item[3].equals(number)){
                            String[]sortedArr = {item[0],item[1],item[3],item[5]};
                            finalData.add(sortedArr);
                        }
                    }
                }
                break;
            }
        }
        catch (NullPointerException ex){
            System.out.println("Returning null!");
        }

        return finalData;
    }
}
