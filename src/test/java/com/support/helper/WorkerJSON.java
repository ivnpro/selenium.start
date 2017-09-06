package com.support.helper;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.support.helper.Linker.stage;


public class WorkerJSON {
    public void writeAgencyToJSON (Agency writingAgency){
//        Agency newAgency = new Agency(writingAgency.getTitle(), writingAgency.getEmail(), writingAgency.getPass());
        String jsonString = JSON.toJSONString(writingAgency);
        new File("C:\\second\\src\\test\\java\\com\\support\\data\\"+writingAgency.getTitle()+"-"+stage).mkdirs();
        File fileAgency = new File(
                "C:\\second\\src\\test\\java\\com\\support\\data\\"+writingAgency.getTitle()+"-"+stage,"data_agency.txt");

        try{
            FileWriter logFileAg = new FileWriter(fileAgency, false);
            logFileAg.write(jsonString);
            logFileAg.flush();
            System.out.println(jsonString);

        } catch(IOException ex){System.out.println(ex.getMessage());}
    }

    public static Agency getAgencyFromJSON(String titleAg){
        Agency agFromJson = new Agency();

        try{
        BufferedReader br = new BufferedReader(new FileReader(
                "C:\\second\\src\\test\\java\\com\\support\\data\\"+titleAg+"-"+stage+"\\data_agency.txt "));

            String jsonAg = br.readLine();
            agFromJson = JSON.parseObject(jsonAg, Agency.class);

        } catch(IOException ex){System.out.println(ex.getMessage());}

        return agFromJson;
    }

    public void writeGirlToJSON(String titleAg, Girl writingGirl) {
        List<Girl> listGirls;
        String jsonGirl;
        StringBuilder sb = new StringBuilder();
        Path fileGirl = Paths.get("C:\\second\\src\\test\\java\\com\\support\\data\\"+titleAg+"-"+stage+"\\agency-girl.txt");

        try {
            if (!Files.exists(fileGirl)){
                Files.createFile(fileGirl);
            }
            BufferedReader bufferJsonGirl = Files.newBufferedReader(fileGirl);
//            String checker = bufferJsonGirl.readLine();

            // Парсинг коллекции из файла
            bufferJsonGirl.lines().forEach(sb::append);
            jsonGirl = sb.toString();
            listGirls = JSON.parseArray(jsonGirl, Girl.class);

            // Проверка на наличие девушки в коллекции
            if (listGirls == null) {
                listGirls = new ArrayList<>();
                listGirls.add(writingGirl);
            } else {
                for (int i = 0; i < listGirls.size(); i++) {
                    // Обновление инфы о девушке
                    if (listGirls.get(i).getIDinInt() == writingGirl.getIDinInt()) {
                        listGirls.get(i).changeGirlInfo(writingGirl);
                        break;
                    }
                    // Добавление новой девушки
                    if (i + 1 == listGirls.size()) {
                        listGirls.add(writingGirl);
                        break;
                    }
                }
            }


//
            // Запись новой коллекции в строку и добавление переносов строки(принципиально до создания FileWriter, а то коллекция занулится)
            String jsonUpdateList = JSON.toJSONString(listGirls);

            FileWriter newEmptyList = new FileWriter("C:\\second\\src\\test\\java\\com\\support\\data\\"+titleAg+"-"+stage+"\\agency-girl.txt", false);
            newEmptyList.write(jsonUpdateList.replaceAll("},","},\n"));
            newEmptyList.flush();

        } catch(IOException ex){System.out.println(ex.getMessage());}
    }

    public static Girl getGirlByNameFromJSON(String titleAg, String nameGirl){
        Girl girlFromJson = new Girl();
        List<Girl> listGirls;
        String jsonGirl;
        StringBuilder sb = new StringBuilder();
        Path fileGirl = Paths.get("C:\\second\\src\\test\\java\\com\\support\\data\\"+titleAg+"-"+stage+"\\agency-girl.txt");

        try {
            BufferedReader bufferJsonGirl = Files.newBufferedReader(fileGirl);
//            String checker = bufferJsonGirl.readLine();

            // Парсинг коллекции из файла
            bufferJsonGirl.lines().forEach(sb::append);
            jsonGirl = sb.toString();
            listGirls = JSON.parseArray(jsonGirl, Girl.class);

            for (Girl finder: listGirls) {
                if (finder.getName().equals(nameGirl)) {
                    girlFromJson=finder;
                }
            }

        } catch(IOException ex){System.out.println(ex.getMessage());}

        return girlFromJson;

    }

//    хз работает или нет
    public static List<Girl> getListGirlsInAgency(String titleAg) {
        List<Girl> listGirls = null;
        String jsonGirl;
        StringBuilder sb = new StringBuilder();
        Path fileGirl = Paths.get("C:\\second\\src\\test\\java\\com\\support\\data\\"+titleAg+"-"+stage+"\\agency-girl.txt");

        try {
            BufferedReader bufferJsonGirl = Files.newBufferedReader(fileGirl);
//            String checker = bufferJsonGirl.readLine();

            // Парсинг коллекции из файла
            bufferJsonGirl.lines().forEach(sb::append);
            jsonGirl = sb.toString();
            listGirls = JSON.parseArray(jsonGirl, Girl.class);

        } catch(IOException ex){System.out.println(ex.getMessage());}

        return listGirls;
    }

    public void writeOperatorToJSON (String titleAg, Operator writingOperator) {
        List<Operator> listOperators;
        String jsonOperator;
        StringBuilder sb = new StringBuilder();
        Path fileOperator = Paths.get("C:\\second\\src\\test\\java\\com\\support\\data\\"+titleAg+"-"+stage+"\\agency-operator.txt");

        try {
            if (!Files.exists(fileOperator)) {
                Files.createFile(fileOperator);
            }
            BufferedReader bufferJsonGirl = Files.newBufferedReader(fileOperator);
//            String checker = bufferJsonGirl.readLine();

            // Парсинг коллекции из файла
            bufferJsonGirl.lines().forEach(sb::append);
            jsonOperator = sb.toString();
            listOperators = JSON.parseArray(jsonOperator, Operator.class);

            // Проверка на наличие девушки в коллекции
            if (listOperators == null) {
                listOperators = new ArrayList<>();
                listOperators.add(writingOperator);
            } else {
                for (int i = 0; i < listOperators.size(); i++) {
                    // Обновление инфы об операторе
                    if (listOperators.get(i).getLogin() == writingOperator.getLogin()) {
                        listOperators.get(i).changeOperatorInfo(writingOperator);
                        break;
                    }
                    // Добавление новой девушки
                    if (i + 1 == listOperators.size()) {
                        listOperators.add(writingOperator);
                        break;
                    }
                }
            }
            // Запись новой коллекции в строку и добавление переносов строки(принципиально до создания FileWriter, а то коллекция занулится)
            String jsonUpdateList = JSON.toJSONString(listOperators);

            FileWriter newEmptyList = new FileWriter("C:\\second\\src\\test\\java\\com\\support\\data\\"+titleAg+"-"+stage+"\\agency-operator.txt", false);
            newEmptyList.write(jsonUpdateList.replaceAll("},", "},\n"));
            newEmptyList.flush();
        } catch(IOException ex){System.out.println(ex.getMessage());}
    }

    public static Operator getOperatorByNameFromJSON(String titleAg, String nameOperator) {
        Operator operFromJson = new Operator();
        List<Operator> listOperator;
        String jsonOperator;
        StringBuilder sb = new StringBuilder();
        Path fileOperator = Paths.get("C:\\second\\src\\test\\java\\com\\support\\data\\"+titleAg+"-"+stage+"\\agency-operator.txt");

        try {
            BufferedReader bufferJsonOperator = Files.newBufferedReader(fileOperator);
//            String checker = bufferJsonGirl.readLine();

            // Парсинг коллекции из файла
            bufferJsonOperator.lines().forEach(sb::append);
            jsonOperator = sb.toString();
            listOperator = JSON.parseArray(jsonOperator, Operator.class);

            for (Operator finder: listOperator) {
                if (finder.getName().equals(nameOperator)) {
                    operFromJson=finder;
                }
            }

        } catch(IOException ex){System.out.println(ex.getMessage());}

        return operFromJson;

    }
}
