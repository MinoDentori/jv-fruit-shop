package model;

import java.util.Map;

public class DailyReport {
    private static final String COLUMN_SEPARATOR =",";
    private static final String COLUM_NAMES = "fruit,quantity";
    Map<String, Integer> fruitQuantity;

    public DailyReport(Map<String, Integer> fruitQuantity) {
        this.fruitQuantity = fruitQuantity;
    }

    public Map<String, Integer> getFruitQuantity() {
        return fruitQuantity;
    }

    public String createDailyReportString(){
        StringBuilder builder = new StringBuilder(COLUM_NAMES)
                .append(System.lineSeparator());
        for (String key : fruitQuantity.keySet()) {
            builder.append(key)
                    .append(COLUMN_SEPARATOR)
                    .append(fruitQuantity.get(key))
                    .append(System.lineSeparator());
        }
        return builder.toString();
    }
}
