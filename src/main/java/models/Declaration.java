package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import models.submodels.Statistics;

import java.sql.Timestamp;
import java.util.HashMap;

import static data.TestData.CURRENT_TIMEZONE;

@Data
@NoArgsConstructor
public class Declaration {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS ZZZ", timezone = CURRENT_TIMEZONE)
    @EqualsAndHashCode.Exclude()
    private Timestamp createdAt;
    private String id;
    private String name;
    private Integer price;
    private Integer sellerId;
    private Statistics statistics;

    public Declaration(String name, int price, int sellerId, Statistics statistics) {
        this.name = name;
        this.price = price;
        this.sellerId = sellerId;
        this.statistics = statistics;
    }

    public static HashMap<String, Object> getDefaultJsonBodyDeclaration() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", "name");
        hashMap.put("price", 999000);
        hashMap.put("sellerId", 999000);
        hashMap.put("statistics", Statistics.getDefaultJsonBodyStatistics());
        return hashMap;
    }

    public boolean isEqualWithDefaultJsonBodyDeclaration() {
        boolean result = false;
        if (this.name.equals("name")
                && this.price == 999000
                && this.sellerId == 999000
                && this.statistics.isEqualWithDefaultJsonBodyStatistics()
        ) {
            result = true;
        }
        return result;
    }

    public HashMap<String, Object> getJsonBodyDeclaration() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", this.name);
        hashMap.put("price", this.price);
        hashMap.put("sellerId", this.sellerId);
        hashMap.put("statistics", statistics.getJsonBodyStatistics());
        return hashMap;
    }
}
