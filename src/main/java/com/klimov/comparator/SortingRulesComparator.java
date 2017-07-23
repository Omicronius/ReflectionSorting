package com.klimov.comparator;

import com.klimov.entity.SortingRule;
import com.klimov.entity.SortingType;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class SortingRulesComparator<T> implements Comparator<T> {
    private List<SortingRule> sortingRules;

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
                } else if (clazz == Integer.class) {
                    Integer o1Value = (Integer) f.get(o1);
                    Integer o2Value = (Integer) f.get(o2);
                    if (!o1Value.equals(o2Value)) {
                        return quotient * (o1Value - o2Value);
                    }
                }
            } catch (NoSuchFieldException ignored) {
            } catch (IllegalAccessException e) {
                e.printStackTrace();
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
