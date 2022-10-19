package com.example.fpl.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.example.fpl.model.Player;
public class CSVHelper {
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "FirstName", "LastName", "Position", "SeasonPoints", "ClubTeam", "Price"};
  public static boolean hasCSVFormat(MultipartFile file) {
    if (!TYPE.equals(file.getContentType())) {
      return false;

    }
    return true;
  }
  public static List<Player> csvToPlayers(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));	
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
      List<Player> players = new ArrayList<>();
      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
      for (CSVRecord csvRecord : csvRecords) {
    	String playerKey = UUID.randomUUID().toString();
        Player player = new Player(
              playerKey,
              csvRecord.get("FirstName"),
              csvRecord.get("LastName"),
              csvRecord.get("Position"),
              Double.parseDouble(csvRecord.get("Price")),
              Integer.parseInt(csvRecord.get("SeasonPoints")),
              csvRecord.get("ClubTeam")
            );
        players.add(player);
      }
      return players;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }
}
