package com.example.testWork.support.enumerations;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class OrderEventTypeMapper {
    private HashMap<String, OrderEventType> stringEnumMap;
    private HashMap<OrderEventType, String> enumStringMap;

    public String mapFromEnum(OrderEventType enumType) {
        return enumStringMap.get(enumType);
    }
    public OrderEventType mapToEnum(String id) {
        return stringEnumMap.get(id);
    }

    public OrderEventTypeMapper() {
        stringEnumMap = new HashMap<>();
        stringEnumMap.put("Registered", OrderEventType.REGISTERED);
        stringEnumMap.put("Cancelled", OrderEventType.CANCELED);
        stringEnumMap.put("Taken", OrderEventType.TAKEN);
        stringEnumMap.put("Prepared", OrderEventType.PREPARED );
        stringEnumMap.put("Given", OrderEventType.GIVEN);

        enumStringMap = new HashMap<>();
        enumStringMap.put(OrderEventType.REGISTERED, "Registered");
        enumStringMap.put(OrderEventType.CANCELED, "Cancelled");
        enumStringMap.put(OrderEventType.TAKEN, "Taken");
        enumStringMap.put(OrderEventType.PREPARED, "Prepared");
        enumStringMap.put(OrderEventType.GIVEN, "Given");
    }

    public HashMap<String, OrderEventType> getStringEnumMap() {
        return stringEnumMap;
    }

    public void setStringEnumMap(HashMap<String, OrderEventType> stringEnumMap) {
        this.stringEnumMap = stringEnumMap;
    }

    public HashMap<OrderEventType, String> getEnumStringMap() {
        return enumStringMap;
    }

    public void setEnumStringMap(HashMap<OrderEventType, String> enumStringMap) {
        this.enumStringMap = enumStringMap;
    }

    public String getEnumTypeName(OrderEventType type){
        return enumStringMap.get(type);
    }
}
