package online.vidacademica.presentation.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractMapper<T1, T2> {

    public abstract T2 convertTo(T1 value);

    public abstract T1 convertFrom(T2 value);

    public List<T2> convertTo(List<T1> values) {
        if (values == null) return Collections.emptyList();
        List<T2> returnValues = new ArrayList<>(values.size());
        for (T1 value : values) {
            returnValues.add(convertTo(value));
        }
        return returnValues;
    }

    public List<T1> convertFrom(List<T2> values) {
        if (values == null) return Collections.emptyList();
        List<T1> returnValues = new ArrayList<>(values.size());
        for (T2 value : values) {
            returnValues.add(convertFrom(value));
        }
        return returnValues;
    }
}