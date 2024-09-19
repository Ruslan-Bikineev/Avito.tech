package models.submodels;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Statistics {
    private Integer contacts;
    private Integer likes;
    private Integer viewCount;

    public static Map<String, Object> getDefaultJsonBodyStatistics() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("contacts", 100);
        hashMap.put("likes", 100);
        hashMap.put("viewCount", 100);
        return hashMap;
    }

    public boolean isEqualWithDefaultJsonBodyStatistics() {
        boolean result = false;
        if (this.contacts == 100
                && this.likes == 100
                && this.viewCount == 100) {
            result = true;
        }
        return result;
    }

    public HashMap<String, Object> getJsonBodyStatistics() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("contacts", this.contacts);
        hashMap.put("likes", this.likes);
        hashMap.put("viewCount", this.viewCount);
        return hashMap;
    }
}
