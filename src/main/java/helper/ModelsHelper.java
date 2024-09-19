package helper;

import models.Declaration;
import models.submodels.Statistics;

import java.util.LinkedList;

public class ModelsHelper {
    public static LinkedList<Declaration> getListDeclaration(int amount, int sellerId) {
        LinkedList<Declaration> declarations = new LinkedList<>();
        if (amount > 0) {
            for (int i = 0; i < amount; i++) {
                declarations.add(new Declaration("test product" + i, 1000 + i, sellerId,
                        new Statistics(100 + i, 100 + i, 100 + i)));
            }
        }
        return declarations;
    }
}
