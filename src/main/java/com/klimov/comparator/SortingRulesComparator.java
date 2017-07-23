package com.klimov.comparator;

import com.klimov.entity.SortingRule;
import com.klimov.entity.SortingType;

import java.lang.reflect.Field;
import java.util.*;

public class SortingRulesComparator<T> implements Comparator<T> {
    private List<SortingRule> sortingRules;

    private Collection<Class> numericClasses = Arrays.asList(
            byte.class, Byte.class,
            short.class, Short.class,
            int.class, Integer.class,
            long.class, Long.class,
            float.class, Float.class,
            double.class, Double.class);

    public SortingRulesComparator(List<SortingRule> sortingRules) {
        this.sortingRules = sortingRules;
    }

    @Override
    public int compare(T o1, T o2) {
        if (sortingRules == null || sortingRules.isEmpty()) {
            return 0;
        }
        Iterator<SortingRule> it = sortingRules.iterator();
        SortingRule sortingRule;
        SortingType sortingType;
        String field;

        while (it.hasNext()) {
            sortingRule = it.next();
            field = sortingRule.getField();
            sortingType = sortingRule.getSortingType();
            int quotient = defineQuotient(sortingType);

            try {
                Field f = o1.getClass().getDeclaredField(field);
                Class clazz = f.getType();
                f.setAccessible(true);
                if (clazz == String.class) {
                    String o1Value = (String) f.get(o1);
                    String o2Value = (String) f.get(o2);
                    if (!o1Value.equals(o2Value)) {
                        return quotient * o1Value.compareTo(o2Value);
                    }
                } else if (numericClasses.contains(clazz)) {
                    Double o1Value = new Double(f.get(o1).toString());
                    Double o2Value = new Double(f.get(o2).toString());
                    if (!o1Value.equals(o2Value)) {
                        int result = o1Value - o2Value > 0 ? 1 : -1;
                        return quotient * (result);
                    }
                } else if (clazz == boolean.class || clazz == Boolean.class) {
                    Boolean o1Value = (Boolean) f.get(o1);
                    Boolean o2Value = (Boolean) f.get(o2);
                    if (!o1Value.equals(o2Value)) {
                        int result = o1Value ? 1 : -1;
                        return quotient * (result);
                    }
                }
            } catch (NoSuchFieldException | IllegalAccessException ignored) {
            }
        }
        return 0;
    }

    private int defineQuotient(SortingType sortingType) {
        int result;
        switch (sortingType) {
            case ASC:
                result = 1;
                break;
            case DESC:
                result = -1;
                break;
            default:
                result = 0;
                break;
        }
        return result;
    }


}
