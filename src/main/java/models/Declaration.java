package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.submodels.Statistics;

import java.sql.Timestamp;
import java.util.HashMap;

import static data.TestData.CURRENT_TIMEZONE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Declaration {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS ZZZ", timezone = CURRENT_TIMEZONE)
    private Timestamp createdAt;
    private String id;
    private String name;
    private Integer price;
    private Integer sellerId;
    private Statistics statistics;

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
}
