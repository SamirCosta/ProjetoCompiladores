package semantico;

import java.util.HashMap;

public class Semantic {
    private final HashMap<String, String> variables = new HashMap<>();

    public boolean addVariable(String id, String type) {
        if (isDeclared(id)) return false;
        variables.put(id, type);
        return true;
    }

    public boolean isDeclared(String id) {
        return variables.containsKey(id);
    }

}
