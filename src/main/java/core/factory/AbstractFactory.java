package core.factory;

import core.Attributes;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Abstract factory pattern to generate new type of recordFields by implementing them.
 *
 */
public abstract class AbstractFactory {

    /**
     * @param attributes is map of attributes and list of values.
     * @param requiredAttributes is set of required attributes
     * @return <code>true</code> if the attributes contains all required attributes
     */
    public static boolean checkRequiredAttributes(Map<Attributes, List<String>> attributes, HashSet<Attributes> requiredAttributes) {
        return requiredAttributes.
                stream().
                allMatch(attributes::containsKey);
    }

    /**
     * @param attributes is map of attributes and list of values.
     * @param selectableAttributes is list of list of attributes of selectable attributes
     * @return <code>true</code> if the attributes contains all required attributes
     */
    public static boolean checkSelectableAttributes(Map<Attributes, List<String>> attributes, List<List<Attributes>> selectableAttributes) {
        return selectableAttributes.stream()
                .allMatch(e -> e.stream()
                        .anyMatch(attributes::containsKey)
                );
    }


    /**
     * This method delete unnecessary attributes from map
     * @param attributes is map of attributes and list of values.
     * @param acceptableAttributes is set of acceptable attributes
     */
    public static void deleteUnnecessaryAttributes(Map<Attributes, List<String>> attributes, HashSet<Attributes> acceptableAttributes) {
        List<Attributes> unnecessaryAttributes = new LinkedList<>();
        attributes.keySet().forEach(e -> {
            if (!acceptableAttributes.contains(e)) unnecessaryAttributes.add(e);
        });

        unnecessaryAttributes.forEach(attributes::remove);
    }


}

