package matyliano.util;

import org.springframework.stereotype.Component;

@Component
public class CsvUtil {

    private CsvUtil(){

    }

    public static final  String[] taskListHeader = {"creator", "taskDescription", "note","taskStatus","creationDate","modificationDate"};
}
