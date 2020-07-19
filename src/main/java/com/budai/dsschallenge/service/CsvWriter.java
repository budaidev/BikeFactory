package com.budai.dsschallenge.service;

import com.budai.dsschallenge.dto.OrderOutput;
import com.budai.dsschallenge.dto.Program;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvWriter {

    public static final String PATH_TO_ORDER = "./sample_output/megrendelesek.csv";
    public static final String PATH_TO_PROGRAM = "./sample_output/munkarend.csv";

    public void writeOrderOutputToCsv(List<OrderOutput> output) {
        try (
                Writer writer = Files.newBufferedWriter(Paths.get(PATH_TO_ORDER));
        ) {
            writer.append("Megrendelesszam,Profit osszesen,Levont kotber,Munka megkezdese,Keszre jelentes ideje,Megrendeles eredeti hatarideje\n");
            StatefulBeanToCsv<OrderOutput> beanToCsv = new StatefulBeanToCsvBuilder<OrderOutput>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();

            beanToCsv.write(output);
        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException | IOException e) {
            e.printStackTrace();
        }
    }

    public void writeProgram(List<Program> output) {
        try (
                Writer writer = Files.newBufferedWriter(Paths.get(PATH_TO_PROGRAM));
        ) {
            writer.append("Datum,Gep,Kezdo idopont,Zaro idopont,Megrendelesszam\n");
            StatefulBeanToCsv<Program> beanToCsv = new StatefulBeanToCsvBuilder<Program>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();

            beanToCsv.write(output);
        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException | IOException e) {
            e.printStackTrace();
        }
    }


}
