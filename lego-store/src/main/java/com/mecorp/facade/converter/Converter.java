package com.mecorp.facade.converter;

import com.mecorp.facade.populator.Populator;

import java.util.ArrayList;
import java.util.List;

public class Converter<SOURCE, TARGET> {

    private Class<TARGET> targetClass;

    private List<Populator<SOURCE, TARGET>> populators = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public Converter(TARGET targetClass) {
        this.targetClass = (Class<TARGET>) targetClass.getClass();
    }

    public TARGET convert(SOURCE source) {
        TARGET target = createFromClass();
        for (Populator<SOURCE, TARGET> populator : populators) {
            populator.populate(source, target);
        }

        return target;
    }

    public List<TARGET> convertAll(List<SOURCE> objectsToConvert) {
        List<TARGET> convertedList = new ArrayList<>();
        for (SOURCE objectToConvert : objectsToConvert) {
            convertedList.add(convert(objectToConvert));
        }
        return convertedList;
    }

    private TARGET createFromClass() {
        //TODO: proper handling of exceptions if needed
        try {
            //why the use of reflection?
            return targetClass.getDeclaredConstructor().newInstance();
            //TODO: never catch Exception
        } catch (Exception e) {
            //TODO: why throw runtime exception?
            throw new RuntimeException(e);
        }
    }

    public Class<TARGET> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<TARGET> targetClass) {
        this.targetClass = targetClass;
    }

    public List<Populator<SOURCE, TARGET>> getPopulators() {
        return populators;
    }

    public void setPopulators(List<Populator<SOURCE, TARGET>> populators) {
        this.populators = populators;
    }
}
