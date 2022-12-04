package foxandhounds.service.util;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtil {


    public List<Integer> collectValues(List<Integer> characterList) {
        List<Integer> result = new ArrayList<>();

        for (int value : characterList) {
            result.add(value);
        }
        return result;
    }

    public boolean contains(List<Integer> nonzeroList) {
        return nonzeroList.contains(7) || nonzeroList.contains(4) || nonzeroList.contains(0);

    }
}
